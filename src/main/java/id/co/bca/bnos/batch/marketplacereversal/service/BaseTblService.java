package id.co.bca.bnos.batch.marketplacereversal.service;

import id.co.bca.bnos.batch.marketplacereversal.exception.ValidationException;
import id.co.bca.bnos.batch.marketplacereversal.model.GeneralParameter;
import id.co.bca.bnos.batch.marketplacereversal.model.GeneralParameterDetail;
import id.co.bca.bnos.batch.marketplacereversal.repository.GeneralParameterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Iterator;

@Component
public class BaseTblService {

    @Autowired
    protected GeneralParameterRepository generalParameterRepository;

    public LocalDateTime getDate() throws Exception {

        boolean found = false;
        LocalDateTime datetimeNow = LocalDateTime.now();
        GeneralParameter generalParamDate = generalParameterRepository.findByTableAndItem("APPDATE", "TGL_APLIKASI");
        // set value date
        for (Iterator<GeneralParameterDetail> it = generalParamDate.getDetails().iterator(); it.hasNext(); ) {
            GeneralParameterDetail d = it.next();
            if (d.getKeyName().equalsIgnoreCase("TODAY_DATE")
                    && d.getValue() != null && d.getValue().trim().length() > 0 ) {

                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                String tempTime = sdf.format(new Date());

                DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                datetimeNow = LocalDateTime.parse(d.getValue() + " " + tempTime, df);
                found = true;
            }
        }

        if ( !found ) {
            throw new ValidationException("PERIKSA TGL. JATUH TEMPO");
        }

        return datetimeNow;
    }
    
}
