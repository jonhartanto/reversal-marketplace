package id.co.bca.bnos.batch.marketplacereversal;

import id.co.bca.bnos.batch.marketplacereversal.exception.InterfacingException;
import id.co.bca.bnos.batch.marketplacereversal.service.TxnMarketplaceReversalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@SpringBootApplication
@ComponentScan("id.co.bca.bnos.batch.marketplacereversal.*")
@EntityScan("id.co.bca.bnos.batch.marketplacereversal.model")
@EnableJpaRepositories("id.co.bca.bnos.batch.marketplacereversal.repository")
public class BnosBatchMarketplaceReversalApplication {


	private final Logger log = LoggerFactory.getLogger(BnosBatchMarketplaceReversalApplication.class);
	@Autowired
    TxnMarketplaceReversalService txnMarketplaceReversalService;

	public static void main(String[] args) throws IOException {
		new SpringApplicationBuilder(BnosBatchMarketplaceReversalApplication.class).web(WebApplicationType.NONE).run(args);
	}

	@PostConstruct
	public void run() throws InterfacingException, Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date now = new Date();
		String strDate = sdf.format(now);
		sdf.applyPattern("HHmm");
		String strTime = sdf.format(now);
		int timeNow = Integer.parseInt(strTime);
		LocalDate local = LocalDate.now();

		txnMarketplaceReversalService.reversal();

		log.info("[scheduler] InactiveCustAcc Scheduler is Running in date=" +  strDate + " and time=" + strTime + "(" + timeNow + ") ");
	}
}
