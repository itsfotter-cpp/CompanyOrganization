package it.companyorganization.repository;

import it.companyorganization.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee findByUsername(String username);
    List<Employee> findByCompanyId(long id);
    Optional<Employee> findByCf(String cf);
    Optional<Employee> findByCfAndIdIsNot(String cf, long id);

    @Query(
            value = "SELECT e.* " +
                    "FROM employees e INNER JOIN Salary s ON e.salary_id = s.salary_id " +
                    "WHERE data_insert <= :data_range",
            nativeQuery = true
    )
    Optional<List<Employee>> findEmployeeFromDataRange(@Param("data_range") Date dataRange);
}
