package it.companyorganization.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import it.companyorganization.model.Address;
import it.companyorganization.model.Company;
import it.companyorganization.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/api/")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping("/company")
    public ResponseEntity<Company> saveCompany(@RequestBody Company company) {
        return new ResponseEntity<Company>(companyService.saveCompany(company), HttpStatus.CREATED);
    }

    @GetMapping("/companies")
    public List<Company> getAllCompanies() {
        return companyService.getAllCompanies();
    }

    @PutMapping("/company/{id}")
    public ResponseEntity<Company> updateCompany(@RequestBody Company company, @PathVariable("id") long id) {
        return new ResponseEntity<Company>(companyService.updateCompany(company, id), HttpStatus.OK);
    }

    @GetMapping(value = "/company/{id}/address", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Address[]> getCompanyAddress(@PathVariable("id") long id) {
        return new ResponseEntity<Address[]>(companyService.getCompanyAddress(id), HttpStatus.OK);
    }

}
