package id.co.bca.bnos.batch.marketplacereversal.repository;

import id.co.bca.bnos.batch.marketplacereversal.model.Customer;
import id.co.bca.bnos.batch.marketplacereversal.model.CustomerAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerAccountRepository  extends JpaRepository<CustomerAccount, Long> {

    CustomerAccount findByAcctNumber(String AcctNumber);

}
