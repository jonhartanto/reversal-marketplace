package id.co.bca.bnos.batch.marketplacereversal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity
@Table(name = "TXN_MARKETPLACE_PROMOTION")
@Audited
public class TxnMarketplacePromotion {
    @Id
    Long id;

    @Column(name = "PROMOTION_CODE")
    String promotionCode;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_trxmp", referencedColumnName = "id")
    TxnMarketplace txnMarketplace;

    public String getPromotionCode() {
        return promotionCode;
    }

    public void setPromotionCode(String promotionCode) {
        this.promotionCode = promotionCode;
    }

    public TxnMarketplace getTxnMarketplace() {
        return txnMarketplace;
    }

    public void setTxnMarketplace(TxnMarketplace txnMarketplace) {
        this.txnMarketplace = txnMarketplace;
    }
}
