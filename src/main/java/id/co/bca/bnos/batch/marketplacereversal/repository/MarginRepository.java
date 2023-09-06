package id.co.bca.bnos.batch.marketplacereversal.repository;

import com.querydsl.core.types.dsl.StringPath;
import id.co.bca.bnos.batch.marketplacereversal.model.Margin;
import id.co.bca.bnos.batch.marketplacereversal.model.QMargin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

@Repository
public interface MarginRepository extends JpaRepository<Margin, Long>{

    Margin findByCcyCodeAndConditionAndMarginTypeAndMarginLevel(String ccyCode, String condition, String marginType, String marginLevel);
    Margin findByCcyCodeAndConditionAndMarginType(String ccyCode, String condition, String marginType);
    Margin findByCcyCode(String ccyCode);

}