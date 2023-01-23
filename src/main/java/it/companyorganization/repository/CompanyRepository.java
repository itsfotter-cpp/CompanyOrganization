package it.companyorganization.repository;

import it.companyorganization.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    /*
     * Sto dichiarando un metodo in Spring già implementato, cioè Spring riconosce che
     * CompanyName è un attributo di Company per cui basta dichiarare il metodo e la query è pronta.
     */
    Company findByCompanyName(String companyName);

}
