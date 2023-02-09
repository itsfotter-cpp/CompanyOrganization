package it.companyorganization.controller;

import it.companyorganization.model.Salary;
import it.companyorganization.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SalaryController {

    @Autowired
    private SalaryService salaryService;

    @PostMapping("/salary")
    public ResponseEntity<Salary> saveSalary(@RequestBody Salary salary) {
        return new ResponseEntity<Salary>(salaryService.saveSalary(salary), HttpStatus.CREATED);
    }

    @GetMapping("/salaries")
    public ResponseEntity<List<Salary>> getAllSalary() {
        return new ResponseEntity<List<Salary>>(salaryService.getAllSalary(), HttpStatus.OK);
    }

}
