package it.companyorganization.service;

import it.companyorganization.exception.ResourceNotFoundException;
import it.companyorganization.model.Company;
import it.companyorganization.model.Employee;
import it.companyorganization.repository.CompanyRepository;
import it.companyorganization.repository.EmployeeRepository;
import it.companyorganization.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.IOException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EmployeeServiceTest {

    @InjectMocks
    private EmployeeServiceImpl employeeService;
    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private CompanyRepository companyRepository;
    @Mock
    BCryptPasswordEncoder passwordEncoder;

    private Employee employee;

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
    @DisplayName("Should save the employee object to the db")
    void save() {
        Company company = new Company();
        company.setId(15);
        company.setCompanyName("Prova SRL");
        company.setAddress("Via ABC 15");

        employee.setCompany(company);

        /*
         * In questo modo è come se stessimo bloccando il metodo save() in EmployeeRepository,
         * questo perchè il Service ha all'interno i metodi del repository ma noi vogliamo
         * fare un test con dati fittizi.
         */
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
        when(companyRepository.findById(15L)).thenReturn(Optional.of(company));
        when(companyRepository.findByCompanyName("Prova SRL")).thenReturn(company);

        Employee newEmployee = employeeService.saveEmployee(employee);

        assertNotNull(newEmployee);
        assertThat(newEmployee.getEmail()).isEqualTo("ldonof@gmail.com");
    }

    @Test
    void getEmployeeById() {
        when(employeeRepository.findById(anyLong())).thenReturn(Optional.of(employee));

        Employee existingEmployee = employeeService.getEmployeeById(300L);
        assertNotNull(existingEmployee);
        assertThat(existingEmployee.getId()).isEqualTo(300L);
    }

    @Test
    void getEmployeeByIdForException() {
        when(employeeRepository.findById(300L)).thenReturn(Optional.ofNullable(employee));

        assertThrows(RuntimeException.class, () -> {
            employeeService.getEmployeeById(20L);
        });
    }

    @Test
    void updateEmployee() {
        when(employeeRepository.findById(anyLong())).thenReturn(Optional.ofNullable(employee));
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        employee.setUsername("ldonox");
        employee.setEmail("ldonox@hotmail.it");

        Employee newEmployee = employeeService.updateEmployee(employee, 300L);

        assertNotNull(newEmployee);
        assertEquals("ldonox", newEmployee.getUsername());
        assertEquals("ldonox@hotmail.it", newEmployee.getEmail());

        /*
         * Controllo per vedere che effettivamente il codice fiscale non è stato aggiornato
         */
        assertEquals("55910-301", newEmployee.getCf());
    }

    @Test
    void deleteEmployee() {
        when(employeeRepository.findById(anyLong())).thenReturn(Optional.of(employee));
        doNothing().when(employeeRepository).deleteById(300L);

        employeeService.deleteEmployee(300L);

        verify(employeeRepository, times(1)).deleteById(300L);
    }

}
