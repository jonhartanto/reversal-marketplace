package id.co.bca.bnos.batch.marketplacereversal.repository;

import id.co.bca.bnos.batch.marketplacereversal.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long>{

    Stock findByBranchAndCcyCodeAndConditionAndStatus(String branch, String ccyCode, String condition, String status);
    Stock findByBranchAndCcyCodeAndConditionAndStatusAndBranchDest(String branch, String ccyCode, String condition, String status, String branchDest);
    List<Stock> findByBranchAndCcyCodeAndCondition(String branch, String ccyCode, String condition);

}