package org.banks;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.Date;
@Getter
@Setter
public class NBU {
    private int r030;
    private String txt;
    private BigDecimal rate;
    private Currency cc;
    private String exchangedate;

    @Override
    public String toString() {
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("dd.MM.yyyy");
        Date docDate;
        try {
            docDate = format.parse(exchangedate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return
               cc +
                " rate=" + rate +
                ", exchange date=" + docDate +
                '}';
    }
}
