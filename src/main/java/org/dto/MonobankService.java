package org.dto;

import org.banks.Monobank;
import org.functionalInteface.BanksUtil;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Currency;
import java.util.List;
import java.util.stream.Collectors;

public class MonobankService extends BankService implements BanksUtil {
    private static final int ISO_CODE_UAH = 980;
    long start = 0;
    String response;
    private static final String URL = "https://api.monobank.ua/bank/currency";

    @Override
    public String getCurrency(String command, DecimalFormat df) {
        try {
            if ((start == 0) || (start + 60000 < System.currentTimeMillis())) {
                response = Jsoup.connect(URL).ignoreContentType(true).get().body().text();
                start = System.currentTimeMillis();
            }
            List<Monobank> responseDtos = convertResponseToList(response, Monobank.class);
            return responseDtos
                    .stream()
                    .filter(dto -> dto.getCurrencyCodeA() == Currency.getInstance(command)
                            .getNumericCode() && dto.getCurrencyCodeB() == ISO_CODE_UAH)
                    .map(dto -> "МОНОБАНК курси обміну для " + Currency.getInstance(command) + "\n"
                            + "Купівля = " + df.format(dto.getRateBuy()) + " UAH, "
                            + "Продаж = " + df.format(dto.getRateSell()) + " UAH")
                    .collect(Collectors.joining());
        } catch (IllegalArgumentException e) {
            return "такої валюти немає";
        } catch (IOException e) {
            return "Проблеми на сервері";
        }
    }
}
