package org.dto;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class BankService {
    protected <T> List<T> convertResponseToList(String response, Class<T> bankClass) {
        Type type = TypeToken.getParameterized(List.class, bankClass).getType();
        Gson gson = new Gson();
        return gson.fromJson(response, type);
    }
}
