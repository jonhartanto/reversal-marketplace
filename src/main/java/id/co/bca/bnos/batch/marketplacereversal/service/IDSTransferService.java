package id.co.bca.bnos.batch.marketplacereversal.service;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import id.co.bca.bnos.batch.marketplacereversal.integration.ids.DAINInboundMessage;
import id.co.bca.bnos.batch.marketplacereversal.integration.ids.IDSTransferRequest;
import id.co.bca.bnos.batch.marketplacereversal.model.IDSTransfer;
import id.co.bca.bnos.batch.marketplacereversal.model.QIDSTransfer;
import id.co.bca.bnos.batch.marketplacereversal.repository.IDSTransferRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class IDSTransferService {
    private static final Logger LOG = LoggerFactory.getLogger(IDSTransferService.class);

    private final IDSTransferRepository idsTransferRepo;

    public IDSTransferService(final IDSTransferRepository idsTransferRepo) {
        this.idsTransferRepo = idsTransferRepo;
    }

    public Page<IDSTransfer> getAcknowledged(Predicate predicate, final Pageable pageable){
        if(predicate == null)
            predicate = QIDSTransfer.iDSTransfer.id.isNotNull();

        final BooleanExpression isRelease = QIDSTransfer.iDSTransfer.approved.eq(true);
        final BooleanExpression notDeleted = QIDSTransfer.iDSTransfer.action.in("A", "E");
        predicate = isRelease.and(predicate).and(notDeleted);

        final Page<IDSTransfer> idsTransferPage =  idsTransferRepo.findAll(predicate, pageable);
        return idsTransferPage;
    }

    // public Page<IDSTransfer> getWaiting(Predicate predicate, final Pageable pageable){
    //     if(predicate == null)
    //         predicate = QIDSTransfer.idsTransfer.id.isNotNull();

    //     final BooleanExpression isWaiting = QIDSTransfer.idsTransfer.releaseFlag.isNull();
    //     predicate = isWaiting.and(predicate);

    //     final Page<IDSTransfer> idsTransferPage =  idsTransferRepo.findAll(predicate, pageable);
    //     return idsTransferPage;
    // }

    // check duplicate
    public IDSTransfer getIDSTransfer(IDSTransferRequest request){

        String acctNo = request.getAcctNo();
        String amount = request.getAmtChar();
        String refNo = request.getRefNo();
        String currency = request.getCurrCode();
        String busnDate = request.getBusnDate();

        final BooleanExpression predicate = QIDSTransfer.iDSTransfer.acctNo.eq(acctNo)
            .and(QIDSTransfer.iDSTransfer.amtChar.eq(amount))
            .and(QIDSTransfer.iDSTransfer.refNo.eq(refNo))
            .and(QIDSTransfer.iDSTransfer.currCode.eq(currency))
            .and(QIDSTransfer.iDSTransfer.busnChar.eq(busnDate));

        IDSTransfer idsTransfer =  idsTransferRepo.findOne(predicate).orElse(null);
        return idsTransfer;
    }

    public IDSTransfer getIDSTransferDAIN(DAINInboundMessage inbound){

        String acctNo = inbound.getAcctNo();
        String amount = inbound.getAmtChar();
        String refNo = inbound.getRefNo();
        String currency = inbound.getCurrCode();
        String busnDate = inbound.getBusnDate();

        final BooleanExpression predicate = QIDSTransfer.iDSTransfer.acctNo.eq(acctNo)
            .and(QIDSTransfer.iDSTransfer.amtChar.eq(amount))
            .and(QIDSTransfer.iDSTransfer.refNo.eq(refNo))
            .and(QIDSTransfer.iDSTransfer.currCode.eq(currency))
            .and(QIDSTransfer.iDSTransfer.busnChar.eq(busnDate))
            .and(QIDSTransfer.iDSTransfer.logType.eq("L"));

        IDSTransfer idsTransfer =  idsTransferRepo.findOne(predicate).orElse(null);
        return idsTransfer;
    }

    public IDSTransfer findIDSTransfer(String acctNo, String refNo, String currency, BigDecimal amount, LocalDate busnDate) {
        final BooleanExpression predicate = QIDSTransfer.iDSTransfer.acctNo.eq(acctNo)
                .and(QIDSTransfer.iDSTransfer.amtNum.eq(amount))
                .and(QIDSTransfer.iDSTransfer.refNo.eq(refNo))
                .and(QIDSTransfer.iDSTransfer.currCode.eq(currency))
                .and(QIDSTransfer.iDSTransfer.busnDate.eq(busnDate));

        IDSTransfer idsTransfer =  idsTransferRepo.findOne(predicate).orElse(null);
        return idsTransfer;
    }


    public IDSTransfer create(final IDSTransfer idsTransfer) {
        // LOG.debug("IDSTransfer = " + idsTransfer.toString());

        // idsTransfer.setOperatorDate(LocalDate.now());
        // idsTransfer.setOperatorTime(LocalTime.now());
        idsTransfer.setApproved(Boolean.FALSE);
        idsTransfer.setAction("A");
        idsTransfer.setActive(true);
        // idsTransfer.setReleaserId(null);
        // idsTransfer.setReleaseDate(null);
        // idsTransfer.setReleaseTime(null);

        final IDSTransfer savedIDSTransfer = idsTransferRepo.save(idsTransfer);
        return savedIDSTransfer;
    }

    public  IDSTransfer update(final Long id, final IDSTransfer idsTransfer){
        final IDSTransfer idsTransferDb = idsTransferRepo.findById(id).orElse(null);
        if (idsTransferDb != null) {
            idsTransfer.setId(idsTransferDb.getId());
        } else {
            throw new EntityNotFoundException("Entity with id = " + id + " not found.");
        }
        
        // idsTransfer.setOperatorDate(LocalDate.now());
        // idsTransfer.setOperatorTime(LocalTime.now());
        idsTransfer.setApproved(Boolean.FALSE);
        idsTransfer.setAction("E");

        // idsTransfer.setReleaserId(null);
        // idsTransfer.setReleaseDate(null);
        // idsTransfer.setReleaseTime(null);

        final IDSTransfer updatedIDSTransfer = idsTransferRepo.save(idsTransfer);
        return updatedIDSTransfer;
    }

    public IDSTransfer acknowledge(final Long id) {
        final IDSTransfer idsTransferDb = idsTransferRepo.findById(id).orElse(null);
        if (idsTransferDb == null) {
            throw new EntityNotFoundException("Entity with id = " + id + " not found.");
        }

        idsTransferDb.setActive(true);
        // idsTransferDb.setReleaseDate(LocalDate.now());
        // idsTransferDb.setReleaseTime(LocalTime.now());

        IDSTransfer idsTransferRelesed = idsTransferRepo.save(idsTransferDb);

        return idsTransferRelesed;
    }

    public IDSTransfer reset(final Long id) {
        final IDSTransfer idsTransferDb = idsTransferRepo.findById(id).orElse(null);
        if (idsTransferDb == null) {
            throw new EntityNotFoundException("Entity with id = " + id + " not found.");
        }

        idsTransferDb.setActive(false);
        // idsTransferDb.setReleaseDate(null);
        // idsTransferDb.setReleaseTime(null);

        IDSTransfer idsTransferRelesed = idsTransferRepo.save(idsTransferDb);

        return idsTransferRelesed;
    }

}
