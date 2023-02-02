package it.companyorganization.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import it.companyorganization.model.Company;

import java.util.List;

public interface CompanyService {

    Company saveCompany(Company company);
    List<Company> getAllCompanies();
    Company updateCompany(Company company, long id);

    Object getCompanyAddress(long id);

}
