package it.companyorganization.service;

import it.companyorganization.model.Salary;

import java.util.List;

public interface SalaryService {
    Salary saveSalary(Salary salary);

    List<Salary> getAllSalary();
}
