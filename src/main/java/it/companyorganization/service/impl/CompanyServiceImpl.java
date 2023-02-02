package it.companyorganization.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.companyorganization.exception.ResourceNotFoundException;
import it.companyorganization.model.Company;
import it.companyorganization.repository.CompanyRepository;
import it.companyorganization.service.CompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Slf4j
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

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public Object getCompanyAddress(long id) {

        Company company = companyRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Company", "id", id)
        );

        String url = "https://nominatim.openstreetmap.org/search?q=" + company.getAddress() + "&format=json";
        RestTemplate restTemplate = new RestTemplate();
        Object address = restTemplate.getForObject(url, Object.class);

        return address;
    }

}
