package id.co.bca.bnos.batch.marketplacereversal.util;

public interface Constants {

    interface Flag {
        String YES = "Y";
        String NO = "N";
    }

    interface RateType {
        String RATE_COUNTER = "C";
        String RATE_KHUSUS = "K";
        String RATE_ONE_TO_ONE = "B";
        String RATE_UPLO_LIMIT = "Y";
    }

    interface DealSlipTrxType {
        String SELL = "J";
        String BUY = "B";
    }

    interface StockStatus {
        String SELL =  "J";
        String BUY = "B";
        String PHYSICAL = "F";
        String POSITION = "P";
    }

    interface DocType {
        String WITHOUT_DOC = "N";
        String WITH_DOC = "Y";
    }

    interface CustomerType {
        String REGULER = "N";
        String HEAD_OFFICE = "C";
    }

    interface DenomType {
        // small denom K1 (1, 2)
        String K1 = "K1";
        // small denom K1 (5, 10, 20)
        String K5 = "K5";
    }

    interface MarginType {
        String operational = "O";
        String eBranch  = "E";
        String atm = "A";
    }

    interface Condition {
        String seri = "S";
        String mulus = "M";
        String cap = "C";
        String kecil = "K";
    }

    interface OpponentType {
        String NASABAH = "N";
        String KP = "C";
    }

    interface TrxStatusCode {
        String TUKER_DENOM = "00";
        String CREATED = "10";
        String OVERLIMIT_OPERATOR = "10O";
        String OVERLIMIT_CUSTOMER = "10C";
        String CETAK_NOTA = "20";
        String BATAL_REQUESTED = "11";
        String BATAL_APPROVED = "1X";
        String PENYERAHAN = "30";
        String PENERIMAAN = "40";
        String TXN_MRR = "01";
        String TXN_KHUSUS = "1P";
    }

    interface CetakType {
        String print = "print";
        String email = "email";
    }

    interface SpecialTx {
        String RATE = "Y";
        String INPUTABLE = "K";
        String NOCHANGES = "N";
    }

    interface StatusSerah {
        //String RESERVED = "RD";
        String PREPARE = "PREPARE";
        String CHECK = "CHECK";
        String CHECKED = "CHECKED";
        String RELEASE = "RELEASE";
        String RECEIVED_OK = "RECEIVED_OK";
    }

    interface ResponseStatus {
        int OK = 1;
        int ERROR = -1;
    }

    interface SettlementType {
        String MANUAL = "MANUAL";
        String OTOMATIS = "OTOMATIS";
        String NOTAP = "NOTA P";
        String AUTOCOVER = "AUTOCOVER";
        String TUKERDENOM = "TUKAR DENOM";

        String AUTOCOVER_EBRANCH = "AUTOCOVER EBRANCH";
        String NOTAP_EBRANCH = "NOTA P EBRANCH";
        String REVERSAL_EBRANCH = "REVERSAL EBRANCH";
        String AUTOCOVER_ATM = "AUTOCOVER ATM";
        String AUTOCOVER_ATM_SYSTEM = "AUTOCOVER BYSISTEM";
    }

    interface PenerimaanType {
        String TO_BRANCH = "TO_BRANCH";
        String TO_KASIR = "TO_KASIR";
    }

    interface PenerimaanStatusBalance {
        String MATCH = "MATCH";
        String UNMATCH = "UNMATCH";
    }

    interface PenerimaanKasirStatus {
        String PREPARE = "PREPARE";
        String DONE = "DONE";
    }

//    interface StatusSerahDetails {
//        String PREPARE_FULL = "P";
//        String PREPARE_HALF = "H";
//    }

    String KP_CODE = "0998";

    interface VaInvocationResponseCode{
        String SUCCESS = "00";
        String REJECTED = "01";
        String TIMEOUT = "02";
    }
    interface ChannelMailType{
        String MARKETPLACE_PAID = "MARKETPLACE_PAID";
        String MARKETPLACE_FAILED = "MARKETPLACE_FAILED";
        String PAID_SUBJECT = "TRANSAKSI MATA UANG ASING BCA MARKETPLACE";
    }


    interface TrxMpStatusCode {
        String CREATED = "10W";
        String PAID = "10";
        String CETAK_NOTA = "20";
        String EXPIRED = "1X";
        String FAILED = "1X";
    }
    interface TrxMPStatusCodeLabel {
        String CREATED = "CREATED";
        String PAID = "PAID";
        String RECEIVED = "RECEIVED";
        String CETAK_NOTA = "CETAK NOTA";
        String BATAL_REQUESTED = "BATAL REQUESTED";
        String BATAL_APPROVED = "BATAL APPROVED";
        String PENYERAHAN = "PENYERAHAN";
        String PENERIMAAN = "PENERIMAAN";
    }
}
