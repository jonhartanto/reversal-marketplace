package id.co.bca.bnos.batch.marketplacereversal.integration.ids;

import id.co.bca.bnos.batch.marketplacereversal.exception.ExceptionResponse;
import id.co.bca.bnos.batch.marketplacereversal.exception.InterfacingException;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;

@RestController
@Tag(name = "IDSTransfer", description = "IDS Account API")
@RequestMapping("/api/v1/payment/ids-transfer")
public class IDSTransferController {
    private static final Logger LOG = LoggerFactory.getLogger(IDSTransferController.class);

    private final IDSService idsService;

    public IDSTransferController(IDSService idsService) {
        this.idsService = idsService;
    }

    @RequestMapping(method = RequestMethod.POST, path = "")
    public @ResponseBody ResponseEntity<?> transfer(@RequestBody IDSTransferRequest transferRequest, HttpServletRequest request) {
        IDSTransferResponse transferResponse = null;
        try {
            transferResponse = idsService.transfer(transferRequest);
        } catch (InterfacingException e) {
            String path = ((ServletWebRequest)request).getRequest().getRequestURI();
            ExceptionResponse exceptionResponse = new ExceptionResponse(
            HttpStatus.INTERNAL_SERVER_ERROR.value(), "Interface Error", e.getMessage(), path);
            return new ResponseEntity<Object>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(transferResponse, HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.POST, path = "/debit")
    public  @ResponseBody ResponseEntity<?>  debit(@RequestBody IDSTrfDebitRequest trfDebitRequest, HttpServletRequest request) {
        IDSTransferResponse transferResponse = null;
        try {
            transferResponse = idsService.debit(trfDebitRequest);
        } catch (InterfacingException e) {
            String path = ((ServletWebRequest)request).getRequest().getRequestURI();
            ExceptionResponse exceptionResponse = new ExceptionResponse(
            HttpStatus.INTERNAL_SERVER_ERROR.value(), "Interface Error", e.getMessage(), path);
            return new ResponseEntity<Object>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(transferResponse, HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.POST, path = "/credit")
    public  @ResponseBody ResponseEntity<?>  credit(@RequestBody IDSTrfCreditRequest trfCreditRequest, HttpServletRequest request) {
        IDSTransferResponse transferResponse = null;
        try {
            transferResponse = idsService.credit(trfCreditRequest);
        } catch (InterfacingException e) {
            String path = ((ServletWebRequest)request).getRequest().getRequestURI();
            ExceptionResponse exceptionResponse = new ExceptionResponse(
            HttpStatus.INTERNAL_SERVER_ERROR.value(), "Interface Error", e.getMessage(), path);
            return new ResponseEntity<Object>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(transferResponse, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/credit-vostro")
    public  @ResponseBody ResponseEntity<?>  creditVostro(@RequestBody IDSTrfCreditVostroRequest trfCreditVostroRequest, HttpServletRequest request) {
        IDSTransferResponse transferResponse = null;
        try {
            transferResponse = idsService.creditVostro(trfCreditVostroRequest);
        } catch (InterfacingException e) {
            String path = ((ServletWebRequest)request).getRequest().getRequestURI();
            ExceptionResponse exceptionResponse = new ExceptionResponse(
            HttpStatus.INTERNAL_SERVER_ERROR.value(), "Interface Error", e.getMessage(), path);
            return new ResponseEntity<Object>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(transferResponse, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/dain")
    public  @ResponseBody ResponseEntity<?> dainTransaction(@RequestBody DAINInboundMessage trfCreditRequest, HttpServletRequest request) {
        DAINTransferResponse transferResponse = null;
        try {
            transferResponse = idsService.transferbyDAIN(trfCreditRequest);
        } catch (InterfacingException e) {
            String path = ((ServletWebRequest)request).getRequest().getRequestURI();
            ExceptionResponse exceptionResponse = new ExceptionResponse(
            HttpStatus.INTERNAL_SERVER_ERROR.value(), "Interface Error", e.getMessage(), path);
            return new ResponseEntity<Object>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(transferResponse, HttpStatus.OK);
    }

}
