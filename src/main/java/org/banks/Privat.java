package org.banks;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Currency;

@Setter
@Getter
public class Privat {
    private Currency ccy;
    private Currency base_ccy;
    private BigDecimal buy;
    private BigDecimal sale;

    @Override
    public String toString() {
        return "PrivateBank change " +
                ccy +
                " to " + base_ccy +
                "\n by this course:\n" +
                " buy = " + buy +
                ", sale= " + sale +
                '}';
    }
}
