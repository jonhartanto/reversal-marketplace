package id.co.bca.bnos.batch.marketplacereversal.util;

import id.co.bca.bnos.batch.marketplacereversal.exception.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class Tools {

    //public static SimpleDateFormat sdf = new SimpleDateFormat("ddMMyy");
    static DateTimeFormatter df = DateTimeFormatter.ofPattern("ddMMyy");

    public static String generateNotaNumberC(String code, long counter, LocalDate localDate) {
        return generateNotaNumber("C", code, counter, localDate);
    }

    public static String generateNotaNumberP(String code, long counter, LocalDate localDate) {
        return generateNotaNumber("P", code, counter, localDate);
    }

    public static String generateNotaNumberK(String code, long counter, LocalDate localDate) {
        return generateNotaNumber("K", code, counter, localDate);
    }

    public static String generatePenyerahan(String code, long counter, LocalDate localDate) {
        return generateNotaNumber("S", code, counter, localDate);
    }

    public static String generatePenerimaan(String code, long counter, LocalDate localDate) {
        return generateNotaNumber("P", code, counter, localDate);
    }

    public static String generateNotaNumber(String prefix, String code, long counter, LocalDate localDate) {

        counter = counter % 99999;

        String noNota = prefix + code
                //+ sdf.format(new Date())
                + localDate.format(df)
                + String.format("%05d", counter);
        return noNota;
    }

    public static ResponseEntity responseFailExpectation(String msg){
        return buildResponse(HttpStatus.EXPECTATION_FAILED, msg);
    }

    public static ResponseEntity responseFailBadRequest(String msg){
        return buildResponse(HttpStatus.BAD_REQUEST, msg);
    }

    public static ResponseEntity responseFailForbidden(String msg){
        return buildResponse(HttpStatus.FORBIDDEN, msg);
    }

    public static ResponseEntity responseFailNotFound(String msg){
        return buildResponse(HttpStatus.NOT_FOUND, msg);
    }

    public static ResponseEntity responseFailPreconditon(String msg){
        return buildResponse(HttpStatus.PRECONDITION_FAILED, msg);
    }

    public static ResponseEntity responseOk(String msg) {
        HashMap<String,Object> map = new HashMap<>();
        map.put("message", msg);
        map.put("status", "OK");
        return ResponseEntity.ok(map);
    }

    public static ResponseEntity buildResponse(HttpStatus httpStatus, String msg){
        ExceptionResponse exceptionResponse = new ExceptionResponse(httpStatus.value(),
        "Validation Error", msg, "/service");

        
        return ResponseEntity
                .status(httpStatus)
                .body(exceptionResponse);
    }

    public static BigDecimal getBigDecimal(int value) {
        return new BigDecimal(value);
    }

    public static BigDecimal getBigDecimal(long value) {
        return new BigDecimal(value);
    }

    public static BigDecimal getBigDecimal(double value) {
        return new BigDecimal(value);
    }

    public static String formatTime(LocalDateTime localDateTime) {
        return localDateTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }

    public static String formatDate(LocalDateTime localDateTime) {
        return localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public static String formatDateTime(LocalDateTime localDateTime) {
        return localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
