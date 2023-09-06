package id.co.bca.bnos.batch.marketplacereversal.integration.ids;

import id.co.bca.bnos.batch.marketplacereversal.exception.ExceptionResponse;
import id.co.bca.bnos.batch.marketplacereversal.exception.InterfacingException;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;



@RestController
@Tag(name = "IDSAccount", description = "IDS Account API")
@RequestMapping("/api/v1/payment/ids-account")
public class IDSAccountController {
    
    private static final Logger LOG = LoggerFactory.getLogger(IDSAccountController.class);

    private final IDSService idsService;
    
    public IDSAccountController(IDSService idsService) {
        this.idsService = idsService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{account_no}/summary")
    public @ResponseBody ResponseEntity<?> getAccountSummary(@PathVariable("account_no") String accountNumber, HttpServletRequest request) {
        IDSAccountSummary accountSummary = null;

        try{
            System.out.println(accountNumber + "IDS SERVICE 1");
            accountSummary = idsService.getAccountSummary(accountNumber);
            System.out.println(accountNumber + "IDS CONTROLLER 2");
        } catch(InterfacingException e) {
            System.out.println(accountNumber + "IDS SERVICE 3");
            String path = request.getRequestURI();
            ExceptionResponse exceptionResponse = new ExceptionResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(), "Interface Error", e.getMessage(), path);
                System.out.println(accountNumber + "IDS SERVICE 4");
            return new ResponseEntity<Object>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        System.out.println(accountNumber + "IDS SERVICE 5");
        return new ResponseEntity<>(accountSummary, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{account_no}/status")
    public @ResponseBody ResponseEntity<?> getAccountStatus(@PathVariable("account_no") String accountNumber,
            HttpServletRequest request) {
        IDSAccountStatus accountStatus = null;

        try {
            accountStatus = idsService.getAccountStatus(accountNumber);
        } catch (InterfacingException e) {
            String path = request.getRequestURI();
            ExceptionResponse exceptionResponse = new ExceptionResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(), "Interface Error", e.getMessage(), path);
            return new ResponseEntity<Object>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(accountStatus, HttpStatus.OK);
    }
}
