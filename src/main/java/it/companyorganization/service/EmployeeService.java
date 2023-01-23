package it.companyorganization.service;

import it.companyorganization.dto.EmployeeDetailsDTO;
import it.companyorganization.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeService {
    Employee saveEmployee(Employee employee);
    Employee addRoleToEmployee(String username, String roleName);
    Page<Employee> getAllEmployees(Pageable pageable);
    Employee getEmployeeById(long id);
    Employee updateEmployee(Employee employee, long id);
    EmployeeDetailsDTO getDetailedEmployee(long id);

    List<EmployeeDetailsDTO> getEmployeeByCompanyId(long companyId);

    void deleteEmployee(long id);
}
