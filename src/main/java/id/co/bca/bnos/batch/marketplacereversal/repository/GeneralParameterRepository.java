package id.co.bca.bnos.batch.marketplacereversal.repository;

import id.co.bca.bnos.batch.marketplacereversal.model.GeneralParameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneralParameterRepository extends JpaRepository<GeneralParameter, Long>{

    GeneralParameter findByTableAndItem(String tableName, String itemName);

}