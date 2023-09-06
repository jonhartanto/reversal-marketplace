package id.co.bca.bnos.batch.marketplacereversal.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

public class RestLoggingInterceptor implements ClientHttpRequestInterceptor {
    private static final Logger LOG = LoggerFactory.getLogger(RestLoggingInterceptor.class);

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
            throws IOException {
//        logRequest(request, body);
        ClientHttpResponse response = execution.execute(request, body);
//        logResponse(response);

        return response;
    }

    private void logRequest(HttpRequest request, byte[] body) throws UnsupportedEncodingException {
        if(LOG.isDebugEnabled()) {
            LOG.debug("===================request begin=====================");
            LOG.debug("URI          : {}", request.getURI());
            LOG.debug("Method       : {}", request.getMethod());
            LOG.debug("Headers      : {}", request.getHeaders());
            LOG.debug("Request Body : {}", new String(body, "UTF-8"));
            LOG.debug("====================request end=====================");
        }
    }

    private void logResponse(ClientHttpResponse response) throws IOException {
        if(LOG.isDebugEnabled()) {
            LOG.debug("===================response begin=====================");
            LOG.debug("Status Code   : {}", response.getStatusCode());
            LOG.debug("Status Text   : {}", response.getStatusText());
            LOG.debug("Headers       : {}", response.getHeaders());
            LOG.debug("Response Body : {}", StreamUtils.copyToString(response.getBody(), Charset.defaultCharset()));
            LOG.debug("====================response end=====================");
        }
    }

}