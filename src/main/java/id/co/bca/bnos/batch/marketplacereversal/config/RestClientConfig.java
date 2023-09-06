package id.co.bca.bnos.batch.marketplacereversal.config;

import id.co.bca.bnos.batch.marketplacereversal.logging.RestLoggingInterceptor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

@Configuration
//@PropertySource("file:C:\\BatchMYE\\kumpulan JAR\\CustomerMaintenance\\application.properties")
//@PropertySource("file:C:\\Workspace\\BCAGITLAB\\bca-batch-daily-itrf-1\\bnos-batch-marketplace-reversal\\src\\main\\resources\\application.properties")
//@PropertySource("file:D:\\Project\\BNOS\\BNOS x Marketplace\\Program\\Batch\\bnos-batch-marketplace-reversal\\src\\main\\resources\\application.properties")
public class RestClientConfig {
    @Bean
	public RestTemplate restTemplate() throws Exception {
    	disableSSLValidation();
        SimpleClientHttpRequestFactory simpleFactory = new SimpleClientHttpRequestFactory();
        //simpleFactory.setConnectTimeout(5000);
        ClientHttpRequestFactory factory = new BufferingClientHttpRequestFactory(simpleFactory);
    
        RestTemplate restTemplate = new RestTemplate(factory);

        List<ClientHttpRequestInterceptor> interceptors =
            restTemplate.getInterceptors();
        if(CollectionUtils.isEmpty(interceptors)) {
            interceptors = new ArrayList<>();
        } 
        interceptors.add(new RestLoggingInterceptor());
        restTemplate.setInterceptors(interceptors);
        
		return restTemplate;
    }
    
    public Boolean disableSSLValidation() throws Exception {
        final SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, new TrustManager[]{new X509TrustManager(){
            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        
            @Override
            public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
            }
        
            @Override
            public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
            }
        }}, null);

        HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
        HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier(){
            @Override
            public boolean verify(String arg0, SSLSession arg1) {
                return true;
            }
        });

        return true;
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper;
    }
}