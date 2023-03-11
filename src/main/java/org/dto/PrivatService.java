package org.dto;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.banks.Privat;
import org.functionalInteface.BanksUtil;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.util.Currency;
import java.util.List;
import java.util.stream.Collectors;

public class PrivatService implements BanksUtil {
    private static final String URL = "https://api.privatbank.ua/p24api/pubinfo?exchange&coursid=5";

    @Override
    public String getCurrency(String command, DecimalFormat df) {
            try {
                String response = Jsoup.connect(URL).ignoreContentType(true).get().body().text();
                List<Privat> responseDtos = convertResponseToList(response);
                return responseDtos
                        .stream()
                        .filter(dto -> dto.getCcy().equals(Currency.getInstance(command)))
                        .map(dto -> "PrivateBank: " + Currency.getInstance(command)
                        +" sell = " + df.format(dto.getSale())
                        +" buy = " + df.format(dto.getBuy()))
                        .collect(Collectors.joining(""));
            } catch (IOException ignored) {
                return "Проблеми на сервері";
            }
    }

    private List<Privat> convertResponseToList(String response) {
        Type type = TypeToken.getParameterized(List.class, Privat.class).getType();
        Gson gson = new Gson();
        return gson.fromJson(response, type);
    }
}
