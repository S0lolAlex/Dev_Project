package org.dto;

import org.banks.NBU;
import org.functionalInteface.BanksUtil;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Currency;
import java.util.List;
import java.util.stream.Collectors;

public class NBUService extends BankService implements BanksUtil {
    private static final String URL = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json";

    @Override
    public String getCurrency(String command, DecimalFormat df) {
        try {
            String response = Jsoup.connect(URL).ignoreContentType(true).get().body().text();
            List<NBU> responseDtos = convertResponseToList(response, NBU.class);
            return responseDtos
                    .stream()
                    .filter(dto -> dto.getCc().equals(Currency.getInstance(command)))
                    .map(dto -> "NBU: " + Currency.getInstance(command) + " course = "
                            + df.format(dto.getRate()))
                    .collect(Collectors.joining());
        } catch (IOException e) {
            return "Проблеми на сервері";
        } catch (IllegalArgumentException e) {
            return "такої валюти немає";
        }
    }
}
