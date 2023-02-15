package it.companyorganization.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.companyorganization.model.Company;
import it.companyorganization.model.Employee;
import it.companyorganization.service.CompanyService;
import it.companyorganization.service.EmployeeService;
import it.companyorganization.service.ImageService;
import it.companyorganization.service.SalaryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private EmployeeService employeeService;
    @MockBean
    private CompanyService companyService;
    @MockBean
    private ImageService imageService;
    @MockBean
    private SalaryService salaryService;
    @Autowired
    private ObjectMapper objectMapper;
    private Employee employee;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void init() {
        employee = new Employee();
        employee.setId(300);
        employee.setFirstName("Luigi");
        employee.setLastName("D'Onofrio");
        employee.setEmail("ldonof@gmail.com");
        employee.setUsername("ldonofrio");
        employee.setCf("55910-301");
        employee.setPassword(passwordEncoder.encode("hello1"));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void shouldCreateNewEmployee() throws Exception {

        Company company = new Company(50, "Prova SRL", "Viale ABC 15", null);
        employee.setCompany(company);

        System.out.println(objectMapper.writeValueAsString(employee));

        when(employeeService.saveEmployee(any(Employee.class))).thenReturn(employee);
        this.mockMvc.perform(post("/api/employee/registration")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employee))
                .with(csrf()))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.username", is(employee.getUsername())))
                .andExpect(jsonPath("$.email", is(employee.getEmail())));

    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void shouldFetchOneEmployeeById() throws Exception {
        when(employeeService.getEmployeeById(anyLong())).thenReturn(employee);
        this.mockMvc.perform(get("/api/employee/{id}", 300L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username", is(employee.getUsername())))
                .andExpect(jsonPath("$.email", is(employee.getEmail())));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void shouldFetchAllEmployees() throws Exception {
        Employee employee2 = new Employee();
        employee2.setId(300);
        employee2.setFirstName("Alfredo");
        employee2.setLastName("Filandri");
        employee2.setEmail("alfila@gmail.com");
        employee2.setUsername("alfredfil");
        employee2.setCf("55910-302");
        employee2.setPassword(passwordEncoder.encode("hello2"));

        List<Employee> list = new ArrayList<>();
        list.add(employee);
        list.add(employee2);

        Page<Employee> pageList = new PageImpl<>(list);

        Pageable pageable = PageRequest.of(0, 10, Sort.by("id"));

        when(employeeService.getAllEmployees(pageable)).thenReturn(pageList);
        this.mockMvc.perform(get("/api/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size", is(list.size())));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void shouldDeleteEmployee() throws Exception {
        doNothing().when(employeeService).deleteEmployee(anyLong());
        this.mockMvc.perform(delete("/api/employee/{id}", 300L).with(csrf()))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void shouldUpdateEmployee() throws Exception {
        when(employeeService.updateEmployee(any(Employee.class), anyLong())).thenReturn(employee);
        this.mockMvc.perform(put("/api/employee/{id}", 300L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employee))
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username", is(employee.getUsername())))
                .andExpect(jsonPath("$.firstName", is(employee.getFirstName())));
    }

}
