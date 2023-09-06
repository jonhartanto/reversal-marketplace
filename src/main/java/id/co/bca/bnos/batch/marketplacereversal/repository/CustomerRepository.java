package id.co.bca.bnos.batch.marketplacereversal.repository;

import id.co.bca.bnos.batch.marketplacereversal.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{

    Customer findByCodeAndIdNumber(String code, String idNumber);

    Customer findByCode(String code);

    //ADDED 061622
    //Returns customer with exactly the same code.
    @Query(value = "select * from customer " +
            "       where code = ?1", nativeQuery = true)
    Customer findByCodeExplicit(String code);

    Customer findByIdNumber(String idNumber);

}
