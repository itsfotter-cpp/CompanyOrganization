package it.companyorganization.service.impl;

import it.companyorganization.model.Salary;
import it.companyorganization.repository.EmployeeRepository;
import it.companyorganization.repository.SalaryRepository;
import it.companyorganization.service.SalaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SalaryServiceImpl implements SalaryService {

    @Autowired
    private SalaryRepository salaryRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Transactional
    @Override
    public Salary saveSalary(@Valid Salary salary) {
        salary.setDataInsert(new Date());
        salary.setTotalHour(salary.calculateTotalHour());
        salary.setTotalReward(salary.calculateTotalReward());
        return salaryRepository.save(salary);
    }

    @Override
    public List<Salary> getAllSalary() {
        List<Salary> allSalaries = salaryRepository.findAll();

        for(Salary salary : allSalaries) {
            salary.setTotalHour(salary.calculateTotalHour());
            salary.setTotalReward(salary.calculateTotalReward());
        }

        return allSalaries;
    }
}
