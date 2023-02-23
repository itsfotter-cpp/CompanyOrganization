package it.companyorganization.repository;

import it.companyorganization.model.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    /*
     * Sto dichiarando un metodo in Spring già implementato, cioè Spring riconosce che
     * CompanyName è un attributo di Company per cui basta dichiarare il metodo e la query è pronta.
     */
    Company findByCompanyName(String companyName);

    @Query(
            value = "SELECT * FROM Company ORDER BY company_name",
            countQuery = "SELECT count(*) FROM Company",
            nativeQuery = true
    )
    Page<Company> findAllCompanyWithPagination(Pageable pageable);

    @Query(
            value = "SELECT * FROM Company WHERE company_name LIKE %:name%",
            nativeQuery = true
    )
    List<Company> filterCompanyByName(@Param("name") String name);

}
