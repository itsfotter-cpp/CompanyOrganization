package it.companyorganization.service.impl;

import it.companyorganization.exception.ResourceNotFoundException;
import it.companyorganization.model.Company;
import it.companyorganization.repository.CompanyRepository;
import it.companyorganization.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public Company saveCompany(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Company updateCompany(Company company, long id) {

        Company existingCompany = companyRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Company", "ID", id)
        );

        existingCompany.setCompanyName(company.getCompanyName());
        existingCompany.setAddress(company.getAddress());

        companyRepository.save(existingCompany);

        return existingCompany;

    }

}
