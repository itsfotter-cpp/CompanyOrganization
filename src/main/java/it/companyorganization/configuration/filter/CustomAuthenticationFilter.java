package it.companyorganization.configuration.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.companyorganization.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

/*
 * Questo filtro viene utilizzato nella fase di login. Richiama automaticamente
 * UserDetailsService.loadUserByUsername, e se l'utente esiste, crea e restituisce
 * due token JWT: uno è l'access token, utilizzato per autorizzare l'utente,
 * l'altro è il refresh token, utilizzato dal client per acquisire un nuovo access
 * token senza dover effettuare nuovamente il login.
 */
@Slf4j
@RequiredArgsConstructor
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private static final String BAD_CREDENTIAL_MESSAGE = "Authentication failed for username: %s and password: %s";

    private final AuthenticationManager authenticationManager;

    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        String username = null;
        String password = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, String> map = objectMapper.readValue(request.getInputStream(), Map.class);
            username = map.get("username");
            password = map.get("password");
            log.debug("Login with username: {}", username);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        }
        catch (AuthenticationException e) {
            log.error(String.format(BAD_CREDENTIAL_MESSAGE, username, password), e);
            throw e;
        }
        catch (Exception e) {
            response.setStatus(INTERNAL_SERVER_ERROR.value());
            Map<String, String> error = new HashMap<>();
            error.put("errorMessage", e.getMessage());
            response.setContentType(APPLICATION_JSON_VALUE);
            new ObjectMapper().writeValue(response.getOutputStream(), error);
            throw new RuntimeException(String.format("Error in attemptAuthentication with username %s and password %s", username, password), e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authentication) throws IOException, ServletException {
        User user = (User)authentication.getPrincipal();
        String accessToken = JwtUtil.createAccessToken(user.getUsername(), request.getRequestURL().toString(),
                user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(toList()));
        String refreshToken = JwtUtil.createRefreshToken(user.getUsername());

        /*
         *Put the Access Token and the Refresh Token into the body of the response
         */
        ObjectMapper mapper = new ObjectMapper();
        Map<Object, Object> info = new HashMap<>();
        info.put("access_token", accessToken);
        info.put("refresh_token", refreshToken);
        info.put("username", user.getUsername());
        info.put("roles", user.getAuthorities().stream()
                                            .map(a -> ((GrantedAuthority) a).getAuthority())
                                            .collect(toList()));

        response.setContentType(APPLICATION_JSON_VALUE);

        response.addHeader("access_token", accessToken);
        response.addHeader("refresh_token", refreshToken);

        mapper.writeValue(response.getOutputStream(), info);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> error = new HashMap<>();
        error.put("errorMessage", "Bad credentials");
        response.setContentType(APPLICATION_JSON_VALUE);
        mapper.writeValue(response.getOutputStream(), error);
    }
}

/*
 * 1) il metodo attemptAuthentication viene invocato nella fase di login, prende username e
 * password dalla RequestBody e richiama authenticationManager.authenticate,
 * che a sua volta chiama UserDetailService per controllare che lo user sia presente nel database,
 * e poi controlla che la password decodificata dell'istanza User (creata da UserDetailService)
 * corrisponda a quella data in input. Se i check sono superati, viene richiamato il metodo
 * successfulAuthentication, altrimenti unsuccessfulAuthentication.
 *
 * 2) Il metodo successfulAuthentication crea l'access token e il refresh token e li aggiunge all'header
 * di risposta della chiamata /login.
 *
 * 3) Il metodo unsuccessfulAuthentication viene invocato quando attemptAuthentication
 * lancia una eccezione di tipo AuthenticationException.
 * La sovrascrittura di questo metodo, per i nostri scopi, è opzionale.
 * Noi lo utilizziamo per restituire 401 e un messaggio di errore nella Response Body.
 */