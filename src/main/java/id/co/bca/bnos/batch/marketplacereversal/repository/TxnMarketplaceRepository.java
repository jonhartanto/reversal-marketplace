package id.co.bca.bnos.batch.marketplacereversal.repository;

import id.co.bca.bnos.batch.marketplacereversal.model.TxnMarketplace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TxnMarketplaceRepository extends JpaRepository<TxnMarketplace, Long>{

   @Query(value="SELECT * FROM TXN_MARKETPLACE WHERE RECORD_STATUS = '10' AND FLAG_REVERSE = '1' AND EXPIRED_DATE < SYSDATE",nativeQuery = true)
   List<TxnMarketplace> getTxnMarketplace();

    // @Query(value="SELECT * FROM TXN_MARKETPLACE WHERE BILL_NO IN ('C009521062300253','C009521062300254')  AND VA_NO = '8808813009521062300253'",nativeQuery = true)
    // List<TxnMarketplace> getTxnMarketplace();

    TxnMarketplace findByVaNo(String vaNo);
}
