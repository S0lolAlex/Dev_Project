package org.banks;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class Monobank {
    private int currencyCodeA;
    private int currencyCodeB;
    private long date;
    private BigDecimal rateSell;
    private BigDecimal rateBuy;
    private BigDecimal rateCross;

    @Override
    public String toString() {
        return "Code currency = " + currencyCodeA
                + " Change to " + currencyCodeB
                + " time = " + new Date(date * 1000);
    }
}
