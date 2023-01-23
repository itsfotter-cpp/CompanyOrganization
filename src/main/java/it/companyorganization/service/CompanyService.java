package it.companyorganization.service;

import it.companyorganization.model.Company;

import java.util.List;

public interface CompanyService {

    Company saveCompany(Company company);
    List<Company> getAllCompanies();

    Company updateCompany(Company company, long id);

}
