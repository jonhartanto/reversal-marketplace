package id.co.bca.bnos.batch.marketplacereversal.repository;

import com.querydsl.core.types.dsl.StringPath;
import id.co.bca.bnos.batch.marketplacereversal.model.Denom;
import id.co.bca.bnos.batch.marketplacereversal.model.QDenom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DenomRepository extends JpaRepository<Denom, Long>{

    Denom findByCcyCode(String ccyCode);

    Denom findByCcyCodeAndDenom(String ccyCode, Integer denom);
    Denom findByCcyCodeAndDenomAndDenomType(String ccyCode, Integer denom, String denomType);
    Denom findByCcyCodeAndDenomAndDenomTypeAndSku(String ccyCode, Integer denom, String denomType, String sku);

    @Query(value = "select DENOM from denom where ccy_code = ?1 " +
            " GROUP BY DENOM " +
            " ORDER BY DENOM DESC",
            nativeQuery = true)
    List<Integer> getAllDenomDistinct(String ccyCode);
}