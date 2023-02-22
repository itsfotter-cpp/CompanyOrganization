package it.companyorganization.repository;

import it.companyorganization.model.Company;
import it.companyorganization.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private CompanyRepository companyRepository;

    private Employee employee;

    @BeforeEach
    void init() {
        //Arrange -> Setting dei dati richiesti
        employee = new Employee();
        employee.setFirstName("Luigi");
        employee.setLastName("D'Onofrio");
        employee.setEmail("ldonof@gmail.com");
        employee.setUsername("ldonofrio");
        employee.setCf("11527-062");
        employee.setPassword("hello1");
        employee.setCompany(companyRepository.findById(4L).get());
    }

    @Test
    @DisplayName("It shows the saved employee to the database") //Custom Name
    void save() {
        //Act -> chiamate un metodo o una unità da testare
        Employee newEmployee = employeeRepository.save(employee);
        //Assert -> verificare se il risultato richiesto è corretto o meno
        assertNotNull(newEmployee);
        assertThat(newEmployee.getId()).isNotEqualTo(null);
    }

    @Test
    @DisplayName("It should return all the employees")
    void getAllEmployees() {
        /*
         * Credo due employee fittizi che non vengono salvati nel db ma solo in questo test,
         * il db già contiene dei valori per cui voglio vedere se aggiungendo
         * questi due employee il valore aspettato sale.
         * Aggiungo gli employee e poi chiamo il metodo findAll(), dopodiché faccio i test.
         */
        employeeRepository.save(employee);

        Employee employee2 = new Employee();
        employee2.setFirstName("Alfredo");
        employee2.setLastName("Frido");
        employee2.setEmail("afrid@gmail.com");
        employee2.setUsername("afrido");
        employee2.setCf("89065-6634");
        employee2.setPassword("hello2");
        employeeRepository.save(employee2);

        List<Employee> list = employeeRepository.findAll();

        assertNotNull(list);
        assertThat(list).isNotNull();

        assertEquals(201, list.size());

    }

    @Test
    void getEmployeeById() {
        employeeRepository.save(employee);
        Employee employeeFounded = employeeRepository.findById(employee.getId()).get();

        assertNotNull(employeeFounded);
        assertEquals("ldonofrio", employee.getUsername());
        assertEquals("55910-301", employee.getCf());
    }

    @Test
    void updateEmployee() {
        employeeRepository.save(employee);

        Employee employeeFounded = employeeRepository.findById(employee.getId()).get();

        employeeFounded.setCf("55910-341");
        employeeFounded.setPassword("hello2");
        Employee newEmployee = employeeRepository.save(employeeFounded);

        assertEquals("55910-341", newEmployee.getCf());
        assertEquals("hello2", newEmployee.getPassword());
    }

    @Test
    void deleteEmployee() {
        employeeRepository.save(employee);
        Long id = employee.getId();
        employeeRepository.delete(employee);

        Optional<Employee> existingEmployee = employeeRepository.findById(id);

        List<Employee> list = employeeRepository.findAll();

        assertEquals(199, list.size());
        assertThat(existingEmployee).isEmpty();
    }

    @Test
    void getEmployeesByCompanyId() {
        employeeRepository.save(employee);

        List<Employee> list = employeeRepository.findByCompanyId(4L);

        assertNotNull(list);
        assertThat(list.size()).isEqualTo(5);

    }


}
