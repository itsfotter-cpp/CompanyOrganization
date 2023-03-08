package it.companyorganization.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import it.companyorganization.dto.EmployeeDetailsDTO;
import it.companyorganization.model.Employee;
import it.companyorganization.model.Image;
import it.companyorganization.model.RoleEntity;
import it.companyorganization.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/*
 * @RestController is a convenient annotation that combines @Controller and @ResponseBody,
 * which eliminates the need to annotate every request handling method
 * of the controller class with the @ResponseBody annotation.
 */

@RestController
@RequestMapping("/api/")
@Slf4j
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    //Build Create Employee REST API
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Save an Employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Employee saved"),
            @ApiResponse(responseCode = "409", description = "Employee fiscal code already saved"),
            @ApiResponse(responseCode = "404", description = "Company or Salary not found")
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("employee/registration")
    public ResponseEntity<?> saveEmployee(@Valid @RequestBody Employee employee, Errors errors) {
        //System.out.println("Employee: " + employee);
        if(errors.hasErrors()) {
            return new ResponseEntity<String>(String.valueOf(errors.getFieldError().getDefaultMessage()), HttpStatus.BAD_REQUEST);
        }
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
            @RequestBody Employee employee) throws IOException {

        return new ResponseEntity<Employee>(employeeService.updateEmployee(employee, id), HttpStatus.OK);
    }

    //Build Delete Employee REST API
    @DeleteMapping("employee/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") long id) {

        employeeService.deleteEmployee(id);
        return new ResponseEntity<String>("Employee delete successfully!", HttpStatus.OK);

    }

    @PostMapping("employee/{username}/addRoleToUser")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Employee> addRoleToUser(@PathVariable String username, @RequestBody RoleEntity roleEntity) {
        Employee employee = employeeService.addRoleToEmployee(username, roleEntity.getName());
        return new ResponseEntity<Employee>(employee, HttpStatus.CREATED);
    }

    @PostMapping("employee-image/{id}")
    public ResponseEntity<Employee> addPhotoToEmployee(@RequestBody Image image, @PathVariable("id") long id) {
        Employee employee = employeeService.addPhotoToEmployee(image, id);
        return new ResponseEntity<Employee>(employee, HttpStatus.OK);
    }

    @GetMapping("employee/{id}/photo")
    public ResponseEntity<Optional<Image>> getPhotoFromEmployee(@PathVariable("id") long id) {
        Optional<Image> image = employeeService.getPhotoFromEmployee(id);
        return new ResponseEntity<Optional<Image>>(image, HttpStatus.OK);
    }

    @GetMapping("employee/data")
    public ResponseEntity<List<Employee>> getEmployeeFromDataRange(
            @RequestParam("data_range") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dataRange) {
        return new ResponseEntity<List<Employee>>(employeeService.getEmployeeFromDataRange(dataRange).get(), HttpStatus.OK);
    }

    @GetMapping("employee/relation/{id}")
    public ResponseEntity<List<Employee>> getRelationEmployeeCompany(@PathVariable("id") long id) {
        List<Employee> listEmployee = employeeService.getRelationEmployeeCompany(id);

        return new ResponseEntity<List<Employee>>(listEmployee, HttpStatus.OK);
    }

}
