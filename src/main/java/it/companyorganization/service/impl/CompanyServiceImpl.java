package it.companyorganization.service.impl;

import it.companyorganization.exception.ResourceNotFoundException;
import it.companyorganization.model.Address;
import it.companyorganization.model.Company;
import it.companyorganization.repository.CompanyRepository;
import it.companyorganization.service.CompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
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

    @Override
    public Address[] getCompanyAddress(long id) {

        Company company = companyRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Company", "id", id)
        );

        RestTemplate restTemplate = new RestTemplate();

        String url = "https://nominatim.openstreetmap.org/search?q=" + company.getAddress() + "&format=json";

        ResponseEntity<Address[]> addressResponseEntity = restTemplate.getForEntity(url, Address[].class);

        //System.out.println("Response status code is: " + addressResponseEntity.getStatusCodeValue());

        Address[] addresses = addressResponseEntity.getBody();

        /*
         * Potrebbero esserci più indirizzi, in questo modo stampo il placeId di ognuno
         */
        for(Address address: addresses) {
            System.out.println(address.getPlaceId());
        }

        return addresses;
    }

    @Override
    public Page<Company> findAllCompanyWithPagination(Pageable pageable) {
        return companyRepository.findAllCompanyWithPagination(pageable);
    }

    @Override
    public List<Company> filterCompanyByName(String name) {
        return companyRepository.filterCompanyByName(name);
    }


}
