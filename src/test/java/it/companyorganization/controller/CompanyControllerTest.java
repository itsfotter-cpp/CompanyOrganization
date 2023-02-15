package it.companyorganization.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.companyorganization.model.Company;
import it.companyorganization.service.CompanyService;
import it.companyorganization.service.EmployeeService;
import it.companyorganization.service.ImageService;
import it.companyorganization.service.SalaryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class CompanyControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CompanyService companyService;
    @MockBean
    private EmployeeService employeeService;
    @MockBean
    private ImageService imageService;
    @MockBean
    private SalaryService salaryService;
    @Autowired
    ObjectMapper objectMapper;

    private Company company;

    @BeforeEach
    void init() {
        company = new Company(100, "Prova Srl", "Via ABC 10", null);
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void shouldCreateNewCompany() throws Exception {
        when(companyService.saveCompany(any(Company.class))).thenReturn(company);
        this.mockMvc.perform(post("/company")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(company))
                        .with(csrf()))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.companyName", is(company.getCompanyName())));
    }

}
