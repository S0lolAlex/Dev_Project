package org.dto;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.banks.NBU;
import org.functionalInteface.BanksUtil;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.util.Currency;
import java.util.List;
import java.util.stream.Collectors;

public class NBUService implements BanksUtil {
    private static final String URL = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json";

    @Override
    public String getCurrency(String command, DecimalFormat df) {
        try {
            String response = Jsoup.connect(URL).ignoreContentType(true).get().body().text();
            List<NBU> responseDtos = convertResponseToList(response);
            return responseDtos
                    .stream()
                    .filter(dto -> dto.getCc().equals(Currency.getInstance(command)))
                    .map(dto -> "NBU: " + Currency.getInstance(command) + "course = "
                            + df.format(dto.getRate()))
                    .collect(Collectors.joining());
        } catch (IOException | IllegalArgumentException e) {
            return "Проблеми на сервері";
        }
    }

    private List<NBU> convertResponseToList(String response) {
        Type type = TypeToken.getParameterized(List.class, NBU.class).getType();
        Gson gson = new Gson();
        return gson.fromJson(response, type);
    }
}
