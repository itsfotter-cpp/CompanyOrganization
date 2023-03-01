package it.companyorganization.service;

import it.companyorganization.model.Address;
import it.companyorganization.model.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PostFilter;

import java.util.List;

public interface CompanyService {

    Company saveCompany(Company company);
    List<Company> getAllCompanies();
    Company updateCompany(Company company, long id);

    Address[] getCompanyAddress(long id);

    Page<Company> findAllCompanyWithPagination(Pageable pageable);

    List<Company> filterCompanyByName(String name);

}
