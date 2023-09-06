package id.co.bca.bnos.batch.marketplacereversal.repository;

import com.querydsl.core.types.Predicate;
import id.co.bca.bnos.batch.marketplacereversal.model.StockDenom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockDenomRepository extends JpaRepository<StockDenom, Long>{

    StockDenom findByBranchAndCcyCodeAndConditionAndStatusAndDenom(String branch, String ccyCode, String condition, String status, Integer denom);

    StockDenom findByBranchAndCcyCodeAndConditionAndStatusAndDenomAndBranchDest(String branch, String ccyCode, String condition, String status, Integer denom, String branchDest);

    List<StockDenom> findByBranchAndCcyCodeAndConditionAndStatus(String branch, String ccyCode, String condition, String status);
            
    List<StockDenom> findByBranchAndCcyCodeAndConditionAndStatusAndBranchDest(String branch, String ccyCode, String condition, String status, String branchDest);

    List<StockDenom> findByCcyCodeAndStatusAndBranch(String ccyCode, String status, String branch);

//    List<StockDenom> findAll(Predicate predicate);
    
}