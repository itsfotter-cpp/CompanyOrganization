package it.companyorganization.service;

import it.companyorganization.dto.EmployeeDetailsDTO;
import it.companyorganization.model.Employee;
import it.companyorganization.model.Image;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    Employee saveEmployee(Employee employee);
    Employee addRoleToEmployee(String username, String roleName);
    Page<Employee> getAllEmployees(Pageable pageable);
    Employee getEmployeeById(long id);
    Employee updateEmployee(Employee employee, long id) throws IOException;
    EmployeeDetailsDTO getDetailedEmployee(long id);
    List<EmployeeDetailsDTO> getEmployeeByCompanyId(long companyId);
    Employee addPhotoToEmployee(Image image, long id);
    Optional<Image> getPhotoFromEmployee(long id);
    Optional<List<Employee>> getEmployeeFromDataRange(Date dataRange);
    void deleteEmployee(long id);

}
