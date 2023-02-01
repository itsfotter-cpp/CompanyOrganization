package it.companyorganization.service;

import it.companyorganization.model.Company;
import it.companyorganization.repository.CompanyRepository;
import it.companyorganization.service.impl.CompanyServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CompanyServiceTest {

    @InjectMocks
    private CompanyServiceImpl companyService;
    @Mock
    private CompanyRepository companyRepository;
    @Test
    void save() {
        assertNotNull(companyService);

        Company company = new Company();
        company.setId(50);
        company.setCompanyName("Prova SRL");
        company.setAddress("Via ABC 15");

        when(companyRepository.save(any(Company.class))).thenReturn(company);

        Company newCompany = companyService.saveCompany(company);

        assertNotNull(newCompany);
        assertThat(newCompany.getCompanyName()).isEqualTo("Prova SRL");
    }

}
