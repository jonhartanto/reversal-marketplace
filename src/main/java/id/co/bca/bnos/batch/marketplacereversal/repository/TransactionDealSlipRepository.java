package id.co.bca.bnos.batch.marketplacereversal.repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.StringPath;
import id.co.bca.bnos.batch.marketplacereversal.model.QTransactionDealSlip;
import id.co.bca.bnos.batch.marketplacereversal.model.TransactionDealSlip;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface TransactionDealSlipRepository extends JpaRepository<TransactionDealSlip, Long>,
        QuerydslPredicateExecutor<TransactionDealSlip>,
        QuerydslBinderCustomizer<QTransactionDealSlip> {

    List<TransactionDealSlip> findAll(Predicate predicate);

    List<TransactionDealSlip> findByOpponentType(String opponentType);

    List<TransactionDealSlip> findBywsidNo(String wsidNo);

    Page<TransactionDealSlip> findAllByRecordStatus(String recordStatus, Pageable var2);

    Page<TransactionDealSlip> findAllByBranchAndRecordStatusIn(String branch, List<String >recordStatus, Pageable var2);

    List<TransactionDealSlip> findByTxnId(String txnId);

    TransactionDealSlip findByBillNo(String billNo);

//    @OrderBy("id ASC")
//    List<TransactionDealSlip> findAllByRecordStatusAndTxnTypeAndOpponentType(String recordStatus, String txnType, String opponentType);
//    @Query(value = "Select ID, \"ACTION\", ACTIVE, APPROVED, APPRV_BY, APPRV_DATE, CREATE_BY, CREATE_DATE, MODIF_BY, "
//    + "MODIF_DATE, FLAG, AMOUNT, BILL_NO, BILL_NO_POTONG, CANCEL_NOTE, CCY_CODE, CCY_CONDITION, CETAK_COUNTER, "
//    + "CHECK_DATE, CHECK_PIC, DEALERID, DESCRIPTION, DOC_ADDS, DOC_CODE, DOC_REQUIRED, EXCHANGE_RATE, "
//    + "IS_CORRECTION, IS_TUKER_DENOM, IS_TXN_KCP, KCP_CODE, MARKET_RATE_USD, OPPONENT_TYPE, "
//    + "PREPARE_DATE, PREPARE_PIC, PROVISI, PURPOSE_DOC, RATE_COUNTER, RATE_TYPE, RECEIVE_DATE, "
//    + "RECEIVE_PIC, RECORD_STATUS, RELEASE_DATE, RELEASE_PIC, SERAH_ORIGIN, SPECIAL_TXN, STATUS_SERAH, TOTAL_DENOM, "
//    + "TRANSACTION_DATE, TXN_ID, TYPE_CETAK, USE_STOCK_PPC, VALUE_DATE, WITH_PROVISI, OPPONENT_ACC_NUMBER, "
//    + "JRNL_FLAG, JRNL_DATE, SETTLEMENT_TYPE, DOC_NAME, ID_PENYERAHAN, TOTAL_DENOM_PREV, MARGIN_MODAL, TR88_RATE, VA_NO , "
//           + "CASE "
//             + "WHEN  tds.OPPONENT_CODE = :cabang THEN "
//               + "("
//                  + "SELECT tds2.OPPONENT_CODE "
//                  + "from TXN_DEAL_SLIP tds2 "
//                  + "where tds2.BILL_NO = tds.BILL_NO "
//               + ")"
//            + "ELSE "
//            + "("
//                        + "SELECT tds2.BRANCH "
//                        + " from TXN_DEAL_SLIP tds2 "
//                        + " where tds2.BILL_NO = tds.BILL_NO "
//                        + ")"
//                    + "END BRANCH ,"
//            + "CASE "
//            + "WHEN  tds.OPPONENT_CODE = :cabang THEN "
//             + "("
//                + "SELECT tds2.BRANCH "
//                  + "from TXN_DEAL_SLIP tds2 "
//                  + "where tds2.BILL_NO = tds.BILL_NO"
//                  + ")"
//               + "ELSE "
//            + "("
//                        + " SELECT tds2.OPPONENT_CODE "
//                            + "from TXN_DEAL_SLIP tds2 "
//                        + " where tds2.BILL_NO = tds.BILL_NO"
//                        + ")"
//                    + "END OPPONENT_CODE ,"
//            + "CASE "
//            + "WHEN  tds.OPPONENT_CODE = :cabang THEN "
//             + "("
//                + "SELECT b2.NAME "
//                  + "from BRANCH b2 "
//                  + "where b2.CODE = tds.BRANCH "
//                  + ")"
//               + "ELSE "
//            + "("
//                        + "SELECT tds2.OPPONENT_NAME "
//                        + "from TXN_DEAL_SLIP tds2 "
//                        + "where tds2.BILL_NO = tds.BILL_NO"
//                        + ")"
//                    + "END OPPONENT_NAME ,"
//            + "CASE "
//            + "WHEN  tds.OPPONENT_CODE = :cabang THEN "
//             + "("
//                + "SELECT "
//                        + "CASE "
//                        + "WHEN tds2.TXN_TYPE = 'J' THEN 'B' ELSE 'J' "
//                        + "END "
//                        + "from TXN_DEAL_SLIP tds2 where tds2.BILL_NO = tds.BILL_NO"
//                  + ")"
//               + "ELSE "
//            + "("
//                        + "SELECT tds2.TXN_TYPE "
//                        + "from TXN_DEAL_SLIP tds2 "
//                        + "where tds2.BILL_NO = tds.BILL_NO"
//                    + ")"
//            + "END TXN_TYPE "
//    + "from TXN_DEAL_SLIP tds WHERE tds.BRANCH = :cabang OR tds.OPPONENT_CODE = :cabang", nativeQuery = true
//    )
//    List<TransactionDealSlip> findAllDealSlipData(@Param("cabang") String cabang);

    @Query(value = "SELECT * FROM txn_deal_slip " +
            "   WHERE record_status='10O' " +
            "       and branch = ?1 " +
            "       and value_date = to_date(?2, 'yyyy-MM-dd') " +
            "       ORDER BY BILL_NO ASC", nativeQuery = true)
    List<TransactionDealSlip> findAllByRecordStatusAndBranch(String branch, String valueDate);

    @Query(value = "SELECT * FROM txn_deal_slip " +
            "WHERE value_date = to_date(?1, 'yyyy-MM-dd') " +
            "AND OPPONENT_TYPE = 'C'", nativeQuery = true)
    List<TransactionDealSlip> findCabangKpByValueDate(String valueDate);

    @Query(value = "SELECT * FROM txn_deal_slip " +
            "WHERE value_date = to_date(?1, 'yyyy-MM-dd') " +
            "AND OPPONENT_TYPE = 'N'", nativeQuery = true)
    List<TransactionDealSlip> findCabangNasabahByValueDate(String valueDate);

    @Query(value = "SELECT * FROM txn_deal_slip " +
            "WHERE RECORD_STATUS IN('10','20','30') AND "+
            "BILL_NO LIKE '%K%'", nativeQuery = true)
    List<TransactionDealSlip> findAllDashboard();

    @Query(value = "SELECT SUM(AMOUNT) FROM TXN_DEAL_SLIP WHERE TXN_TYPE = 'J' " +
            "AND OPPONENT_TYPE = 'N' AND value_date = to_date(?1, 'yyyy-MM-dd')", nativeQuery = true)
    BigDecimal findJualCabangNasabah(String valueDate);

    @Query(value = "SELECT SUM(AMOUNT) FROM TXN_DEAL_SLIP WHERE TXN_TYPE = 'B' " +
            "AND OPPONENT_TYPE = 'N' AND value_date = to_date(?1, 'yyyy-MM-dd')", nativeQuery = true)
    BigDecimal findBeliCabangNasabah(String valueDate);

    @Query(value = "SELECT SUM(AMOUNT) FROM TXN_DEAL_SLIP WHERE TXN_TYPE = 'J' " +
            "AND OPPONENT_TYPE = 'C' AND value_date = to_date(?1, 'yyyy-MM-dd')", nativeQuery = true)
    BigDecimal findJualCabangKp(String valueDate);

    @Query(value = "SELECT SUM(AMOUNT) FROM TXN_DEAL_SLIP WHERE TXN_TYPE = 'B' " +
            "AND OPPONENT_TYPE = 'C' AND value_date = to_date(?1, 'yyyy-MM-dd')", nativeQuery = true)
    BigDecimal findBeliCabangKp(String valueDate);

    @Query(value = "SELECT * FROM txn_deal_slip " +
            "   WHERE record_status=?1 " +
            "       and txn_type=?2 " +
            "       and opponent_type=?3 " +
            "       and bill_no like 'C%' " +
            "       and total_denom > 0 " +
            "       and ccy_code = ?4 " +
            "       and branch = ?5 " +
            "       and ccy_condition = ?6 " +
            "       ORDER BY VALUE_DATE, BILL_NO ASC", nativeQuery = true)
    List<TransactionDealSlip> findAllByRecordStatusAndTxnTypeAndOpponentType(String recordStatus,
                                                                             String txnType,
                                                                             String opponentType,
                                                                             String ccyCode,
                                                                             String branch,
                                                                             String ccyCondition);

    // ***** new flow
    @Query(value = "SELECT * FROM txn_deal_slip " +
            "   WHERE branch = :branchCode" +
            "       and record_status = :recordStatus " +
            "       and txn_type = :txnType " +
            "       and opponent_code = :opponentCode " +
            "       and ccy_code = :ccyCode" +
            "       and ccy_condition = :ccyCondition " +
            "       and bill_no like 'C%' " +
            "       and total_denom >= 0 " +
            "       ORDER BY VALUE_DATE, BILL_NO ASC ", nativeQuery = true)
    List<TransactionDealSlip> findForPenyerahan(@Param("branchCode") String branchCode,
                                                @Param("recordStatus") String recordStatus,
                                                @Param("txnType") String txnType,
                                                @Param("opponentCode") String opponentCode,
                                                @Param("ccyCode") String ccyCode,
                                                @Param("ccyCondition") String ccyCondition);


//    @Query(value = "SELECT t FROM TransactionDealSlip t" +
//            "   WHERE " +
//            "       t.recordStatus = :recordStatus " +
//            "       and t.txnType = :txnType " +
//            "       and t.opponentCode = :opponentCode " +
//            "       and t.billNo like 'K%' " +
//            "       and t.totalDenom > 0 " +
//            "       and (t.statusSerah IS NULL OR t.statusSerah = '') ")
//    Page<TransactionDealSlip> findForPenyerahanKP(@Param("recordStatus") String recordStatus,
//                                                @Param("txnType") String txnType,
//                                                @Param("opponentCode") String opponentCode, Pageable pageable);
//
//    @Query(value = "SELECT t FROM TransactionDealSlip t" +
//            "   WHERE " +
//            "       t.recordStatus = :recordStatus " +
//            "       and t.txnType = :txnType " +
//            "       and t.opponentCode = :opponentCode " +
//            "       and t.billNo like 'K%' " +
//            "       and t.totalDenom > 0 " +
//            "       and (t.statusSerah = 'PREPARE' OR t.serahOrigin='to_branch' ) ")
//    Page<TransactionDealSlip> findNotaPrepared(@Param("recordStatus") String recordStatus,
//                                               @Param("txnType") String txnType,
//                                               @Param("opponentCode") String opponentCode, Pageable pageable);

    @Query(value = "SELECT t FROM TransactionDealSlip t WHERE " +
            "t.recordStatus='20' " +
            "AND t.txnType = 'B' " +
            "AND t.opponentCode = '0998' " +
            "AND t.billNo LIKE  CONCAT(:prefixNota,'%') " +
            "AND t.totalDenom > 0 " +
            "AND t.id IN :ids " +
            "AND (t.statusSerah IS NULL OR t.statusSerah = '') ")
    List<TransactionDealSlip> findForNotaPrepare(@Param("prefixNota")String prefixNota,
                                                 @Param("ids")List<Long> ids);

//    @Query(value = "SELECT t FROM TransactionDealSlip t WHERE t.branch = :branchCode " +
//            "AND t.recordStatus='20' " +
//            "AND t.totalDenom > 0 " +
//            "AND t.id IN :ids " +
//            "AND (t.statusSerah IS NULL OR t.statusSerah = '') ")
//    List<TransactionDealSlip> findForNotaForPrepareCabang(@Param("branchCode") String branchCode,
//                                                          @Param("ids")List<Long> ids);
//
//    @Query(value = "SELECT t FROM TransactionDealSlip t WHERE t.branch = :branchCode " +
//            "AND t.recordStatus='30' " +
//            "AND t.txnType = 'B' " +
//            "AND t.opponentCode = '0998' " +
//            "AND t.billNo like 'K%' " +
//            "AND t.totalDenom > 0 " +
//            "AND t.statusSerah = 'RD' ")
//    List<TransactionDealSlip> findForSummary(@Param("branchCode") String branchCode);
//
//    @Query(value = "SELECT t FROM TransactionDealSlip t WHERE t.branch = :branchCode " +
//            "AND t.recordStatus='30' " +
//            "AND t.txnType = 'B' " +
//            "AND t.opponentCode = '0998' " +
//            "AND t.billNo like 'K%' " +
//            "AND t.totalDenom > 0 " +
//            "AND t.id IN :ids ")
//    List<TransactionDealSlip> findForNotaRelease(@Param("branchCode") String branchCode,
//                                                 @Param("ids")List<Long> ids);


    // ADDED 06/06/2022 JUV
    @Query(value = "SELECT * " +
            "       FROM txn_deal_slip " +
            "       WHERE opponent_code = ?1 and txn_type = 'B' " +
            "               and to_char(value_date, 'yyyy-mm-dd') = " +
            "                (select key_value from general_parameter_detail where key_name = 'TODAY_DATE')", nativeQuery = true)
//    @Query(value = "SELECT * " +
//            "       FROM txn_deal_slip " +
//            "       WHERE opponent_code = ?1", nativeQuery = true)
    Page<TransactionDealSlip> findAllByOpponentCodeAndAppDate(String opponentCode, Pageable pageable);

    //     ADDED 06/06/2022 JUV
    @Query(value = "SELECT * " +
            "       FROM txn_deal_slip " +
            "       WHERE opponent_code = ?1 and txn_type = 'B' " +
            "               and to_char(value_date, 'yyyy-mm-dd') = " +
            "                (select key_value from general_parameter_detail where key_name = 'TODAY_DATE')", nativeQuery = true)
//    @Query(value = "SELECT * " +
//            "       FROM txn_deal_slip " +
//            "       WHERE opponent_code = ?1", nativeQuery = true)
    List<TransactionDealSlip> getListByOpponentCodeAndAppDate(String opponentCode);

    @Query(value = "SELECT a.branch, b.name, a.bill_no, a.txn_type, a.opponent_type, a.opponent_code, (select name from branch where code = opponent_code) as opponent_name, a.record_status, a.ccy_code, a.exchange_rate, a.margin_modal, a.tr88_rate, a.rate_type, a.ccy_condition, a.total_denom, a.amount, a.value_date, a.value_time, a.create_by, a.approval_pic, a.batal_request_pic, a.batal_pic, a.dealerid, a.cetak_counter, a.cetak_pic, a.settlement_type, a.special_txn, a.use_stock_ppc, a.is_txn_kcp, a.release_pic, a.release_date, a.receive_pic, a.receive_date FROM txn_deal_slip a JOIN branch b ON a.branch = b.code WHERE a.opponent_type = 'C' AND a.value_date = to_date(?1, 'yyyy-MM-dd') AND a.branch LIKE ?2", nativeQuery = true)
    List<Object> getListCabKpDownload(String valueDate, String branchCode);

    @Query(value = "SELECT a.branch, b.name, a.bill_no, a.txn_type, a.opponent_type, a.opponent_code, a.opponent_name, a.record_status, a.ccy_code, a.exchange_rate, a.margin_modal, a.tr88_rate, a.rate_type, a.ccy_condition, a.total_denom, a.amount, a.value_date, a.value_time, a.create_by, a.approval_pic, a.batal_request_pic, a.batal_pic, a.dealerid, a.cetak_counter, a.cetak_pic, a.settlement_type, a.special_txn, a.use_stock_ppc, a.is_txn_kcp, a.kcp_code FROM txn_deal_slip a JOIN branch b ON a.branch = b.code WHERE a.opponent_type = 'N' AND a.value_date = to_date(?1, 'yyyy-MM-dd') AND a.branch LIKE ?2", nativeQuery = true)
    List<Object> getListCabNasDownload(String valueDate, String branchCode);

    @Override
    default void customize(QuerydslBindings bindings, QTransactionDealSlip trxslip) {

        bindings.bind(String.class).first((StringPath path, String value) -> path.containsIgnoreCase(value));

        bindings.bind(trxslip.valueDate)
                .all((path, value) -> {
                    List<? extends LocalDate> dates = new ArrayList<>(value);
                    if (dates.size() == 1) {
                        return Optional.of(path.eq(dates.get(0)));
                    } else {
                        LocalDate from = dates.get(0);
                        LocalDate to = dates.get(1);
                        return Optional.of(path.between(from, to));
                    }
                });

        bindings.bind(trxslip.recordStatus)
                .all((path, value) -> {
                    List<? extends String > statusList = new ArrayList<>(value);
                    if (statusList.size() == 1) {
                        return Optional.of(path.containsIgnoreCase(statusList.get(0)));
                    } else {
                        return Optional.of(path.in(statusList));
                    }
                });
    }

}
