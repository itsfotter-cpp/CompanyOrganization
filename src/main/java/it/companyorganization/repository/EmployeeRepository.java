package it.companyorganization.repository;

import it.companyorganization.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee findByUsername(String username);
    List<Employee> findByCompanyId(long id);
    Optional<Employee> findByCf(String cf);
    Optional<Employee> findByCfAndIdIsNot(String cf, long id);

}
