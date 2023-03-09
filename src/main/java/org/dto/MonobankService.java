package org.dto;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.banks.Monobank;
import org.functionalInteface.BanksUtil;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.util.Currency;
import java.util.List;
import java.util.stream.Collectors;

public class MonobankService implements BanksUtil {
    private static final String URL = "https://api.monobank.ua/bank/currency";

    @Override
    public String getCurrency(String command, DecimalFormat df) {
        try {
            String response = Jsoup.connect(URL).ignoreContentType(true).get().body().text();
            List<Monobank> responseDtos = convertResponseToList(response);
            return responseDtos
                    .stream()
                    .filter(dto -> dto.getCurrencyCodeA() == Currency.getInstance(command).getNumericCode())
                    .map(dto -> "Monobank: " + Currency.getInstance(command) +
                            " buy = " + df.format(dto.getRateBuy())
                            + " sell "+ df.format(dto.getRateSell()))
                    .collect(Collectors.joining());
        } catch (IOException | IllegalArgumentException e) {
            return "Немає такої валюти";
        }
    }

    private List<Monobank> convertResponseToList(String response) {
        Type type = TypeToken.getParameterized(List.class, Monobank.class).getType();
        Gson gson = new Gson();
        return gson.fromJson(response, type);
    }
}
