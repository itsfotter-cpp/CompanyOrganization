package it.companyorganization.controller;

import it.companyorganization.dto.EmployeeDetailsDTO;
import it.companyorganization.model.Employee;
import it.companyorganization.model.RoleEntity;
import it.companyorganization.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * @RestController is a convenient annotation that combines @Controller and @ResponseBody,
 * which eliminates the need to annotate every request handling method
 * of the controller class with the @ResponseBody annotation.
 */

@RestController
@RequestMapping("/api/")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    //Build Create Employee REST API
    @PostMapping("employee/registration")
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
        //System.out.println("Employee: " + employee);
        return new ResponseEntity<Employee>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
    }

    //Build Get All Employees REST API
    @GetMapping("employees")
    public Page<Employee> getAllEmployees(
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id"));
        return employeeService.getAllEmployees(pageable);
    }

    //Build Get Employee By ID REST API
    @GetMapping("employee/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long employeeId) {
        return new ResponseEntity<Employee>(employeeService.getEmployeeById(employeeId), HttpStatus.OK);
    }

    /*
     * Ritorna gli employee che appartengono alla stessa Company, dove l'ID della Company viene
     * passato dall'utente.
     * Dopodiché converte la lista degli Employee in un DTO in modo che vengono ritornati solo i campi
     * necessari.
     */
    @GetMapping("employee-by-company/{id}")
    public ResponseEntity<List<EmployeeDetailsDTO>> getEmployeeByCompanyId(@PathVariable("id") long companyId) {
        return new ResponseEntity<List<EmployeeDetailsDTO>>(employeeService.getEmployeeByCompanyId(companyId), HttpStatus.OK);
    }

    @GetMapping("employee/dto/{id}")
    public ResponseEntity<EmployeeDetailsDTO> getDetailedEmployee(@PathVariable("id") long employeeId) {
        return new ResponseEntity<EmployeeDetailsDTO>(employeeService.getDetailedEmployee(employeeId), HttpStatus.OK);
    }

    //Build Update Employee REST API
    @PutMapping("employee/{id}")
    public ResponseEntity<Employee> updateEmployee(
            @PathVariable("id") long id,
            @RequestBody Employee employee) {

        return new ResponseEntity<Employee>(employeeService.updateEmployee(employee, id), HttpStatus.OK);
    }

    //Build Delete Employee REST API
    @DeleteMapping("employee/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") long id) {

        employeeService.deleteEmployee(id);
        return new ResponseEntity<String>("Employee delete successfully!", HttpStatus.OK);

    }

    @PostMapping("employee/{username}/addRoleToUser")
    public ResponseEntity<Employee> addRoleToUser(@PathVariable String username, @RequestBody RoleEntity roleEntity) {
        Employee employee = employeeService.addRoleToEmployee(username, roleEntity.getName());
        return new ResponseEntity<Employee>(employee, HttpStatus.CREATED);
    }

}
