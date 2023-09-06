package id.co.bca.bnos.batch.marketplacereversal.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;

public class MoneySerializer extends JsonSerializer<BigDecimal> {
    final DecimalFormat format = new DecimalFormat("##0.00");

    @Override
    public void serialize(BigDecimal value, JsonGenerator gen, SerializerProvider serializers) 
        throws IOException {
            // value.setScale(2, RoundingMode.HALF_UP);
            final String stringValue = format.format(value);
            gen.writeString(stringValue);
    }
}