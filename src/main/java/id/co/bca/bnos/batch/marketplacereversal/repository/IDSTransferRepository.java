package id.co.bca.bnos.batch.marketplacereversal.repository;

import com.querydsl.core.types.dsl.StringPath;
import id.co.bca.bnos.batch.marketplacereversal.model.IDSTransfer;
import id.co.bca.bnos.batch.marketplacereversal.model.QIDSTransfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;


@Repository
public interface IDSTransferRepository extends JpaRepository<IDSTransfer, Long>,
        QuerydslPredicateExecutor<IDSTransfer>, QuerydslBinderCustomizer<QIDSTransfer> {

    @Override
    default void customize(QuerydslBindings bindings, QIDSTransfer iDSTransfer) {
        bindings.bind(String.class).first((StringPath path, String value) -> path.containsIgnoreCase(value));
    }
}