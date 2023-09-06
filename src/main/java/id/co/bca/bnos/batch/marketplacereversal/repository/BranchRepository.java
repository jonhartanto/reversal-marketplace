package id.co.bca.bnos.batch.marketplacereversal.repository;

import id.co.bca.bnos.batch.marketplacereversal.model.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Long>{

    Branch findByCode(String branchCode);

}
