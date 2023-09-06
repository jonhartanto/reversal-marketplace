package id.co.bca.bnos.batch.marketplacereversal.service;

import id.co.bca.bnos.batch.marketplacereversal.model.TxnMarketplace;
import id.co.bca.bnos.batch.marketplacereversal.model.TxnMarketplaceDetails;
import id.co.bca.bnos.batch.marketplacereversal.model.Denom;
import id.co.bca.bnos.batch.marketplacereversal.model.Stock;
import id.co.bca.bnos.batch.marketplacereversal.model.StockDenom;
import id.co.bca.bnos.batch.marketplacereversal.repository.DenomRepository;
import id.co.bca.bnos.batch.marketplacereversal.repository.StockDenomRepository;
import id.co.bca.bnos.batch.marketplacereversal.repository.StockRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class StockMarketplaceService {
    
    private static final Logger LOG = LoggerFactory.getLogger(StockMarketplaceService.class);

    private final StockRepository stockRepo;
    private final StockDenomRepository stockDenomRepo;
//    private final UserDetailsServiceImpl userService;
    private final DenomRepository denomRepo;

    public StockMarketplaceService(final StockRepository stockRepo, final StockDenomRepository stockDenomRepo, final DenomRepository denomRepo) {
        this.stockRepo = stockRepo;
        this.stockDenomRepo = stockDenomRepo;
//        this.userService = userService;
        this.denomRepo = denomRepo;
    }

    public List<Stock> checkStock(TxnMarketplace trx){

        List<Stock> listResult = new ArrayList<Stock>();

        if(trx.getRecordStatus().toString().equals("10"))
        {
            Stock stockDataPosisi = stockRepo.findByBranchAndCcyCodeAndConditionAndStatus(trx.getBranch(), trx.getCcyCode(), trx.getCcyCond(), "P");

            if(stockDataPosisi != null)
            {
               if(stockDataPosisi.getBalance().compareTo(trx.getTotalDenom()) < 0){
                    throw new RuntimeException("Stock Posisi Tidak Cukup!");
                }
                else
                {
                    for (TxnMarketplaceDetails trxDetail : trx.getTxnMarketplaceDetailsList()) {
                        StockDenom stockDenomPosisi = stockDenomRepo.findByBranchAndCcyCodeAndConditionAndStatusAndDenom(trx.getBranch(), trx.getCcyCode(), trx.getCcyCond(), "P", trxDetail.getDenom().intValue());
                        if(stockDenomPosisi != null)
                        {
                          if(stockDenomPosisi.getBalance().compareTo(trxDetail.getTotalDenom()) < 0){
                                throw new RuntimeException("Stock Posisi Denom " + stockDenomPosisi.getDenom() + " Tidak Cukup!");
                            }
                        }
                        else if(stockDenomPosisi == null)
                        {
                            stockDenomPosisi = initiateStockDenom("P", trx.getCcyCode(), trx.getCcyCond(), trx.getBranch(), trx.getBranch(), trxDetail.getDenom().intValue());
                            stockDenomRepo.save(stockDenomPosisi);
                            
                        }
                                
                    }
                }
    
            }
           else if (stockDataPosisi == null)
            {
                stockDataPosisi = initiateStock("P", trx.getCcyCode(), trx.getCcyCond(), trx.getBranch(), trx.getBranch());
                
                stockRepo.save(stockDataPosisi);
            }
            listResult.add(stockDataPosisi);
        }
        else if(trx.getRecordStatus().toString().equals("20") && trx.getTxnType().equalsIgnoreCase("J"))
        {
            Stock stockDataFisik = stockRepo.findByBranchAndCcyCodeAndConditionAndStatus(trx.getBranch(), trx.getCcyCode(), trx.getCcyCond(), "F");
            if(stockDataFisik != null)
            {
                if(stockDataFisik.getBalance().compareTo(trx.getTotalDenom()) < 0){
                     throw new RuntimeException("Stock Fisik Tidak Cukup!");
                }
                else
                {
                    for (TxnMarketplaceDetails trxDetail : trx.getTxnMarketplaceDetailsList()) {
    
                        StockDenom stockDenomFisik = stockDenomRepo.findByBranchAndCcyCodeAndConditionAndStatusAndDenom(trx.getBranch(), trx.getCcyCode(), trx.getCcyCond(), "F", trxDetail.getDenom().intValue());

                        if(stockDenomFisik != null)
                        {
                            if(stockDenomFisik.getBalance().compareTo(trxDetail.getTotalDenom()) < 0){
                                throw new RuntimeException("Stock Fisik Denom " + stockDenomFisik.getDenom() + " Tidak Cukup!");
                            }
                        }
                        else if(stockDataFisik == null)
                        {
                            stockDenomFisik = initiateStockDenom("F", trx.getCcyCode(), trx.getCcyCond(), trx.getBranch(), trx.getBranch(), trxDetail.getDenom().intValue());
                            stockDenomRepo.save(stockDenomFisik);
                        }
                    }
                }
            }
            else if (stockDataFisik == null)
            {
                stockDataFisik = initiateStock("F", trx.getCcyCode(), trx.getCcyCond(), trx.getBranch(), trx.getBranch());
                
                stockRepo.save(stockDataFisik);
                            
            }
            listResult.add(stockDataFisik);
        }
        else if(trx.getRecordStatus().toString().equals("1X"))
        {
            Stock stockDbMarketplace = stockRepo.findByBranchAndCcyCodeAndConditionAndStatus(trx.getBranch(), trx.getCcyCode(), trx.getCcyCond(), "M");
            if(stockDbMarketplace != null)
            {
                if(stockDbMarketplace.getBalance().compareTo(trx.getTotalDenom()) < 0){
                    throw new RuntimeException("Stock Marketpace Tidak Cukup!");
                }
                else
                {
                    for (TxnMarketplaceDetails trxDetail : trx.getTxnMarketplaceDetailsList()) {
    
                        StockDenom stockDenomMarketplace = stockDenomRepo.findByBranchAndCcyCodeAndConditionAndStatusAndDenom(trx.getBranch(), trx.getCcyCode(), trx.getCcyCond(), "M", trxDetail.getDenom().intValue());
                                
                        if(stockDenomMarketplace != null)
                        {
                            if(stockDenomMarketplace.getBalance().compareTo(trxDetail.getTotalDenom()) < 0){
                                throw new RuntimeException("Stock Marketplace Denom " + stockDenomMarketplace.getDenom() + " Tidak Cukup!");
                            }

                        }
                        else if(stockDenomMarketplace == null)
                        {
                            stockDenomMarketplace = initiateStockDenom("M", trx.getCcyCode(), trx.getCcyCond(), trx.getBranch(), trx.getBranch(), trxDetail.getDenom().intValue());
                            stockDenomRepo.save(stockDenomMarketplace);
                            
                        }
                                
                    }
                }
    
            }
            else if (stockDbMarketplace == null)
            {
                stockDbMarketplace = initiateStock("M", trx.getCcyCode(), trx.getCcyCond(), trx.getBranch(), trx.getBranch());
                stockRepo.save(stockDbMarketplace);
            }
            listResult.add(stockDbMarketplace);
        }
        return listResult;
    }
    
    public List<Stock> updateStock(TxnMarketplace trx){
        List<Stock> listDataUpdate = new ArrayList<Stock>();
        List<StockDenom> listDataUpdateDenom = new ArrayList<StockDenom>();

        if(trx.getRecordStatus().toString().equals("10"))
        {
            Stock stockDbMarketplace = stockRepo.findByBranchAndCcyCodeAndConditionAndStatus(trx.getBranch(), trx.getCcyCode(), trx.getCcyCond(), "M");
            Stock stockDbPosisi = stockRepo.findByBranchAndCcyCodeAndConditionAndStatus(trx.getBranch(), trx.getCcyCode(), trx.getCcyCond(), "P");

            if(stockDbMarketplace == null)
            {
                stockDbMarketplace = initiateStock("M", trx.getCcyCode(), trx.getCcyCond(),
                trx.getBranch(), trx.getBranch());
                
                stockRepo.save(stockDbMarketplace);
            }
                    
            if( stockDbPosisi == null)
            {
                stockDbPosisi = initiateStock("P", trx.getCcyCode(), trx.getCcyCond(),
                trx.getBranch(), trx.getBranch());
                        
                stockRepo.save(stockDbPosisi);
            }
                    
            if (stockDbMarketplace != null && stockDbPosisi != null) {

                BigDecimal val1 = stockDbMarketplace.getBalance();
                BigDecimal val2 = stockDbPosisi.getBalance();

                for (TxnMarketplaceDetails trxDetail : trx.getTxnMarketplaceDetailsList()) {
                                    
                    StockDenom stockDenomMarketplace = stockDenomRepo.findByBranchAndCcyCodeAndConditionAndStatusAndDenom(trx.getBranch(), trx.getCcyCode(), trx.getCcyCond(), "M", trxDetail.getDenom().intValue());
                    StockDenom stockDenomPosisi = stockDenomRepo.findByBranchAndCcyCodeAndConditionAndStatusAndDenom(trx.getBranch(), trx.getCcyCode(), trx.getCcyCond(), "P", trxDetail.getDenom().intValue());

                    if(stockDenomMarketplace == null)
                    {
                        stockDenomMarketplace = initiateStockDenom("M", trx.getCcyCode(),
                        trx.getCcyCond(), trx.getBranch(), trx.getBranch(),
                        trxDetail.getDenom().intValue());
                                    
                        stockDenomRepo.save(stockDenomMarketplace);
                    }
                                
                    if(stockDenomPosisi == null)
                    {
                        stockDenomPosisi = initiateStockDenom("P", trx.getCcyCode(), 
                        trx.getCcyCond(), trx.getBranch(), trx.getBranch(),
                        trxDetail.getDenom().intValue());
                                 
                        stockDenomRepo.save(stockDenomPosisi);
                    }

                    if(stockDenomMarketplace != null && stockDenomPosisi != null)
                    {
                        BigDecimal balanceMarketplace = stockDenomMarketplace.getBalance();
                        BigDecimal balancePosisi = stockDenomPosisi.getBalance();

                        stockDenomMarketplace.setBalance(balanceMarketplace.add(trxDetail.getTotalDenom()));
                        stockDenomPosisi.setBalance(balancePosisi.subtract(trxDetail.getTotalDenom()));

                        stockDenomMarketplace.setBalance(val1.add(trxDetail.getTotalDenom()));
                        stockDbPosisi.setBalance(val2.subtract(trxDetail.getTotalDenom()));

                        listDataUpdateDenom.add(stockDenomMarketplace);
                        listDataUpdateDenom.add(stockDenomPosisi);

                        val1 = stockDenomMarketplace.getBalance();
                        val2 = stockDbPosisi.getBalance();
                    }
                }
                stockDbMarketplace.setAction("E");
                stockDbPosisi.setAction("E");
            }
                    
            listDataUpdate.add(stockDbMarketplace);
            listDataUpdate.add(stockDbPosisi);
        }
        else if(trx.getRecordStatus().toString().equals("20") && trx.getTxnType().equalsIgnoreCase("J"))
        {
            Stock stockDbMarketplace = stockRepo.findByBranchAndCcyCodeAndConditionAndStatus(trx.getBranch(), trx.getCcyCode(), trx.getCcyCond(), "M");
            Stock stockDbFisik = stockRepo.findByBranchAndCcyCodeAndConditionAndStatus(trx.getBranch(), trx.getCcyCode(), trx.getCcyCond(), "F");

            if(stockDbMarketplace == null)
            {
                stockDbMarketplace = initiateStock("M", trx.getCcyCode(), trx.getCcyCond(),
                trx.getBranch(), trx.getBranch());
            
                stockRepo.save(stockDbMarketplace);
            }
                    
            if( stockDbFisik == null)
            {
                stockDbFisik = initiateStock("F", trx.getCcyCode(), trx.getCcyCond(),
                trx.getBranch(), trx.getBranch());
                        
                stockRepo.save(stockDbFisik);
            }
                    
            if (stockDbMarketplace != null && stockDbFisik != null) {

                BigDecimal val1 = stockDbMarketplace.getBalance();
                BigDecimal val2 = stockDbFisik.getBalance();

                for (TxnMarketplaceDetails trxDetail : trx.getTxnMarketplaceDetailsList()) {
                                    
                    StockDenom stockDenomMarketplace = stockDenomRepo.findByBranchAndCcyCodeAndConditionAndStatusAndDenom(trx.getBranch(), trx.getCcyCode(), trx.getCcyCond(), "M", trxDetail.getDenom().intValue());
                    StockDenom stockDenomFisik = stockDenomRepo.findByBranchAndCcyCodeAndConditionAndStatusAndDenom(trx.getBranch(), trx.getCcyCode(), trx.getCcyCond(), "F", trxDetail.getDenom().intValue());

                    if(stockDenomMarketplace == null)
                    {
                        stockDenomMarketplace = initiateStockDenom("M", trx.getCcyCode(), 
                        trx.getCcyCond(), trx.getBranch(), trx.getBranch(),
                        trxDetail.getDenom().intValue());
                                    
                        stockDenomRepo.save(stockDenomMarketplace);
                    }
                                
                    if(stockDenomFisik == null)
                    {
                        stockDenomFisik = initiateStockDenom("F", trx.getCcyCode(), 
                        trx.getCcyCond(), trx.getBranch(), trx.getBranch(),
                        trxDetail.getDenom().intValue());
                                 
                        stockDenomRepo.save(stockDenomFisik);
                    }

                    if(stockDenomMarketplace != null && stockDenomFisik != null)
                    {
                        BigDecimal balanceMarketplace = stockDenomMarketplace.getBalance();
                        BigDecimal balancePosisi = stockDenomFisik.getBalance();

                        stockDenomMarketplace.setBalance(balanceMarketplace.subtract(trxDetail.getTotalDenom()));
                        stockDenomFisik.setBalance(balancePosisi.subtract(trxDetail.getTotalDenom()));

                        stockDenomMarketplace.setBalance(val1.subtract(trxDetail.getTotalDenom()));
                        stockDbFisik.setBalance(val2.subtract(trxDetail.getTotalDenom()));

                        listDataUpdateDenom.add(stockDenomMarketplace);
                        listDataUpdateDenom.add(stockDenomFisik);

                        val1 = stockDenomMarketplace.getBalance();
                        val2 = stockDbFisik.getBalance();
                    }
                }
                stockDbMarketplace.setAction("E");
                stockDbFisik.setAction("E");
            }
            listDataUpdate.add(stockDbMarketplace);
            listDataUpdate.add(stockDbFisik);
        }
        else if(trx.getRecordStatus().toString().equalsIgnoreCase("1X"))
        {
            Stock stockDbMarketplace = stockRepo.findByBranchAndCcyCodeAndConditionAndStatus(trx.getBranch(), trx.getCcyCode(), trx.getCcyCond(), "M");
            Stock stockDbPosisi = stockRepo.findByBranchAndCcyCodeAndConditionAndStatus(trx.getBranch(), trx.getCcyCode(), trx.getCcyCond(), "P");

            if(stockDbMarketplace == null)
            {
                stockDbMarketplace = initiateStock("M", trx.getCcyCode(), trx.getCcyCond(),
                trx.getBranch(), trx.getBranch());
                
                stockRepo.save(stockDbMarketplace);
            }
                    
            if( stockDbPosisi == null)
            {
                stockDbPosisi = initiateStock("P", trx.getCcyCode(), trx.getCcyCond(),
                trx.getBranch(), trx.getBranch());
                        
                stockRepo.save(stockDbPosisi);
            }
                    
            if (stockDbMarketplace != null && stockDbPosisi != null) {

                BigDecimal val1 = stockDbMarketplace.getBalance();
                BigDecimal val2 = stockDbPosisi.getBalance();

                for (TxnMarketplaceDetails trxDetail : trx.getTxnMarketplaceDetailsList()) {
                                    
                    StockDenom stockDenomMarketplace = stockDenomRepo.findByBranchAndCcyCodeAndConditionAndStatusAndDenom(trx.getBranch(), trx.getCcyCode(), trx.getCcyCond(), "M", trxDetail.getDenom().intValue());
                    StockDenom stockDenomPosisi = stockDenomRepo.findByBranchAndCcyCodeAndConditionAndStatusAndDenom(trx.getBranch(), trx.getCcyCode(), trx.getCcyCond(), "P", trxDetail.getDenom().intValue());

                    if(stockDenomMarketplace == null)
                    {
                        stockDenomMarketplace = initiateStockDenom("M", trx.getCcyCode(), 
                        trx.getCcyCond(), trx.getBranch(), trx.getBranch(),
                        trxDetail.getDenom().intValue());
                                    
                        stockDenomRepo.save(stockDenomMarketplace);
                    }
                                
                    if(stockDenomPosisi == null)
                    {
                        stockDenomPosisi = initiateStockDenom("P", trx.getCcyCode(), 
                        trx.getCcyCond(), trx.getBranch(), trx.getBranch(),
                        trxDetail.getDenom().intValue());
                                 
                        stockDenomRepo.save(stockDenomPosisi);
                    }

                    if(stockDenomMarketplace != null && stockDenomPosisi != null)
                    {
                        BigDecimal balanceMarketplace = stockDenomMarketplace.getBalance();
                        BigDecimal balancePosisi = stockDenomPosisi.getBalance();

                        stockDenomMarketplace.setBalance(balanceMarketplace.subtract(trxDetail.getTotalDenom()));
                        stockDenomPosisi.setBalance(balancePosisi.add(trxDetail.getTotalDenom()));

                        stockDbMarketplace.setBalance(val1.subtract(trxDetail.getTotalDenom()));
                        stockDbPosisi.setBalance(val2.add(trxDetail.getTotalDenom()));

                        listDataUpdateDenom.add(stockDenomMarketplace);
                        listDataUpdateDenom.add(stockDenomPosisi);

                        val1 = stockDbMarketplace.getBalance();
                        val2 = stockDbPosisi.getBalance();
                    }
                                
                }

                stockDbMarketplace.setAction("E");
                stockDbPosisi.setAction("E");
            }
                    
            listDataUpdate.add(stockDbMarketplace);
            listDataUpdate.add(stockDbPosisi);
        }

        return listDataUpdate;
    }

    private Stock initiateStock(String status, String currency, String condition,
    String Branch, String BranchDest)
    {
        final Stock stock = new Stock();
        stock.setCcyCode(currency);
        stock.setCondition(condition);
        stock.setStatus(status);
        stock.setBranch(Branch);
        stock.setBranchDest(BranchDest);
        stock.setBalance(new BigDecimal(0));
        stock.setOpenBalance(new BigDecimal(0));
        stock.setAction("E");

        return stock;
    }

    private StockDenom initiateStockDenom(String status, String currency, String condition,
    String Branch, String BranchDest, Integer denom)
    {
        final Denom denomData = denomRepo.findByCcyCodeAndDenomAndDenomType(currency, denom, condition);

        String sku = denomData.getDenominasi() + currency + "0" + condition;
        String keterangan = currency + " " + denomData.getKondisi() + " " + denom.toString();

        final StockDenom stockDenom = new StockDenom();
        stockDenom.setCcyCode(currency);
        stockDenom.setCondition(condition);
        stockDenom.setStatus(status);
        stockDenom.setBranch(Branch);
        stockDenom.setBranchDest(BranchDest);
        stockDenom.setBalance(new BigDecimal(0));
        stockDenom.setOpenBalance(new BigDecimal(0));
        stockDenom.setDenom(denom);
        stockDenom.setAction("E");
        stockDenom.setSku(sku);
        stockDenom.setKeterangan(keterangan);

        return stockDenom;
    }

}
