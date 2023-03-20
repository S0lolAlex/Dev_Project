package org.buttons;

import Utils.UserPreferences;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class Buttons {
    private static final String EMOJI = "✅";
    private static final InlineKeyboardButton SETTINGS_BUTTON = new InlineKeyboardButton("Налаштування");
    private static final InlineKeyboardButton GET_INFO_BUTTON = new InlineKeyboardButton("Отримату інфо");
    private static final InlineKeyboardButton COUNT_BUTTON = new InlineKeyboardButton("Кількість знаків після коми");
    private static final InlineKeyboardButton BANKS_BUTTON = new InlineKeyboardButton("Банк");
    private static final InlineKeyboardButton CURRENCY_BUTTON = new InlineKeyboardButton("Валюти");
    private static final InlineKeyboardButton TIME_BUTTON = new InlineKeyboardButton("Час оповіщення");
    private static final InlineKeyboardButton CURRENCY_CHOOSE_BUTTON = new InlineKeyboardButton("Підтвердити");

    //Init the callBack to Buttons
    public static void initKeyboard() {
        SETTINGS_BUTTON.setCallbackData("/Settings");
        GET_INFO_BUTTON.setCallbackData("/get");
        COUNT_BUTTON.setCallbackData("/Count");
        BANKS_BUTTON.setCallbackData("/Banks");
        CURRENCY_BUTTON.setCallbackData("/Currency");
        TIME_BUTTON.setCallbackData("/time");
        CURRENCY_CHOOSE_BUTTON.setCallbackData("/Chosen");
    }

    //Start menu keyboard
    public static InlineKeyboardMarkup startMarkup() {
        List<InlineKeyboardButton> start = List.of(SETTINGS_BUTTON);
        List<InlineKeyboardButton> getInfo = List.of(GET_INFO_BUTTON);
        List<List<InlineKeyboardButton>> rowsInLine = List.of(start, getInfo);

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        markupInline.setKeyboard(rowsInLine);

        return markupInline;
    }

    //Setting menu keyboard
    public static InlineKeyboardMarkup settingMarkup() {
        List<InlineKeyboardButton> count = List.of(COUNT_BUTTON);
        List<InlineKeyboardButton> currency = List.of(CURRENCY_BUTTON);
        List<InlineKeyboardButton> banks = List.of(BANKS_BUTTON);
        List<InlineKeyboardButton> time = List.of(TIME_BUTTON);
        List<List<InlineKeyboardButton>> rowsInLine = List.of(count, currency, banks, time);

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        markupInline.setKeyboard(rowsInLine);

        return markupInline;
    }

    //keyboard of settings of count numbers after point
    public static InlineKeyboardMarkup setFloatPoint(UserPreferences user) {
        InlineKeyboardButton BUTTON_2 = new InlineKeyboardButton((user.isTwo() ? EMOJI : "") + "2 знаки");
        InlineKeyboardButton BUTTON_3 = new InlineKeyboardButton((user.isThree() ? EMOJI : "") + "3 знаки");
        InlineKeyboardButton BUTTON_4 = new InlineKeyboardButton((user.isFour() ? EMOJI : "") + "4 знаки");
        BUTTON_2.setCallbackData(user.isTwo() ? "/2_CHECKED" : "/2_CHANGING");
        BUTTON_3.setCallbackData(user.isThree() ? "/3_CHECKED" : "/3_CHANGING");
        BUTTON_4.setCallbackData(user.isFour() ? "/4_CHECKED" : "/4_CHANGING");
        List<InlineKeyboardButton> two = List.of(BUTTON_2);
        List<InlineKeyboardButton> three = List.of(BUTTON_3);
        List<InlineKeyboardButton> four = List.of(BUTTON_4);
        List<List<InlineKeyboardButton>> rowsInLine = List.of(two, three, four);

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        markupInline.setKeyboard(rowsInLine);

        return markupInline;
    }

    //changing keyboard of settings of count numbers after point
    public static InlineKeyboardMarkup setFloatPointChanging(UserPreferences user) {
        InlineKeyboardButton BUTTON_2 = new InlineKeyboardButton();
        InlineKeyboardButton BUTTON_3 = new InlineKeyboardButton();
        InlineKeyboardButton BUTTON_4 = new InlineKeyboardButton();

        if (user.isTwo()) {
            BUTTON_2.setText(EMOJI + "2 знаки");
            BUTTON_3.setText("3 знаки");
            BUTTON_4.setText("4 знаки");
            BUTTON_2.setCallbackData("/2_CHECKED");
            BUTTON_3.setCallbackData("/3_CHANGING");
            BUTTON_4.setCallbackData("/4_CHANGING");
        } else if (user.isThree()) {
            BUTTON_2.setText("2 знаки");
            BUTTON_3.setText(EMOJI + "3 знаки");
            BUTTON_4.setText("4 знаки");
            BUTTON_2.setCallbackData("/2_CHANGING");
            BUTTON_3.setCallbackData("/3_CHECKED");
            BUTTON_4.setCallbackData("/4_CHANGING");
        } else if (user.isFour()) {
            BUTTON_2.setText("2 знаки");
            BUTTON_3.setText("3 знаки");
            BUTTON_4.setText(EMOJI + "4 знаки");
            BUTTON_2.setCallbackData("/2_CHANGING");
            BUTTON_3.setCallbackData("/3_CHANGING");
            BUTTON_4.setCallbackData("/4_CHECKED");
        }

        List<InlineKeyboardButton> two = List.of(BUTTON_2);
        List<InlineKeyboardButton> three = List.of(BUTTON_3);
        List<InlineKeyboardButton> four = List.of(BUTTON_4);
        List<List<InlineKeyboardButton>> rowsInLine = List.of(two, three, four);

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        markupInline.setKeyboard(rowsInLine);

        return markupInline;
    }

    // keyboard of banks list
    public static InlineKeyboardMarkup banks(UserPreferences user) {
        InlineKeyboardButton PRIVATE_BUTTON = new InlineKeyboardButton((user.isPrivate() ? EMOJI : "") + "ПриватБанк");
        InlineKeyboardButton MONO_BUTTON = new InlineKeyboardButton((user.isMono() ? EMOJI : "") + "МоноБанк");
        InlineKeyboardButton NBU_BUTTON = new InlineKeyboardButton((user.isNBU() ? EMOJI : "") + "НБУ");
        PRIVATE_BUTTON.setCallbackData(user.isPrivate() ? "/Private_CHECKED" : "/Private_CHANGING");
        MONO_BUTTON.setCallbackData(user.isMono() ? "/Mono_CHECKED" : "/Mono_CHANGING");
        NBU_BUTTON.setCallbackData(user.isNBU() ? "/NBU_CHECKED" : "/NBU_CHANGING");

        List<InlineKeyboardButton> privat = List.of(PRIVATE_BUTTON);
        List<InlineKeyboardButton> monobank = List.of(MONO_BUTTON);
        List<InlineKeyboardButton> nbu = List.of(NBU_BUTTON);
        List<List<InlineKeyboardButton>> rowsInLine = List.of(privat, monobank, nbu);

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        markupInline.setKeyboard(rowsInLine);

        return markupInline;
    }

    // changing keyboard of banks list
    public static InlineKeyboardMarkup banksChanging(UserPreferences user) {
        InlineKeyboardButton PRIVATE_BUTTON = new InlineKeyboardButton();
        InlineKeyboardButton MONO_BUTTON = new InlineKeyboardButton();
        InlineKeyboardButton NBU_BUTTON = new InlineKeyboardButton();

        if (user.isPrivate()) {
            PRIVATE_BUTTON.setText(EMOJI + "ПриватБанк");
            MONO_BUTTON.setText("МоноБанк");
            NBU_BUTTON.setText("НБУ");
            PRIVATE_BUTTON.setCallbackData("/Private_CHECKED");
            MONO_BUTTON.setCallbackData("/Mono_CHANGING");
            NBU_BUTTON.setCallbackData("/NBU_CHANGING");
        } else if (user.isMono()) {
            PRIVATE_BUTTON.setText("ПриватБанк");
            MONO_BUTTON.setText(EMOJI + "МоноБанк");
            NBU_BUTTON.setText("НБУ");
            PRIVATE_BUTTON.setCallbackData("/Private_CHANGING");
            MONO_BUTTON.setCallbackData("/Mono_CHECKED");
            NBU_BUTTON.setCallbackData("/NBU_CHANGING");
        } else if (user.isNBU()) {
            PRIVATE_BUTTON.setText("ПриватБанк");
            MONO_BUTTON.setText("МоноБанк");
            NBU_BUTTON.setText(EMOJI + "НБУ");
            PRIVATE_BUTTON.setCallbackData("/Private_CHANGING");
            MONO_BUTTON.setCallbackData("/Mono_CHANGING");
            NBU_BUTTON.setCallbackData("/NBU_CHECKED");
        }
        List<InlineKeyboardButton> privat = List.of(PRIVATE_BUTTON);
        List<InlineKeyboardButton> monobank = List.of(MONO_BUTTON);
        List<InlineKeyboardButton> nbu = List.of(NBU_BUTTON);
        List<List<InlineKeyboardButton>> rowsInLine = List.of(privat, monobank, nbu);

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        markupInline.setKeyboard(rowsInLine);

        return markupInline;
    }


    // keyboard of currency settings
    public static InlineKeyboardMarkup chooseCurrency(UserPreferences user) {
        InlineKeyboardButton EUR_BUTTON = new InlineKeyboardButton((user.isEur() ? EMOJI : "") + "EUR");
        InlineKeyboardButton USD_BUTTON = new InlineKeyboardButton((user.isUsd() ? EMOJI : "") + "USD");
        EUR_BUTTON.setCallbackData(user.isEur() ? "/EUR_CHECKED" : "/EUR_CHANGING");
        USD_BUTTON.setCallbackData(user.isUsd() ? "/USD_CHECKED" : "/USD_CHANGING");

        List<InlineKeyboardButton> eur = List.of(EUR_BUTTON);
        List<InlineKeyboardButton> usd = List.of(USD_BUTTON);
        List<InlineKeyboardButton> chosen = List.of(CURRENCY_CHOOSE_BUTTON);
        List<List<InlineKeyboardButton>> rowsInLine = List.of(usd, eur, chosen);

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        markupInline.setKeyboard(rowsInLine);

        return markupInline;
    }

    // changing keyboard of currency settings
    public static InlineKeyboardMarkup chooseCurrencyChanging(UserPreferences user) {
        InlineKeyboardButton EUR_BUTTON = new InlineKeyboardButton();
        InlineKeyboardButton USD_BUTTON = new InlineKeyboardButton();
        if (!user.isUsd() && !user.isEur()) {
            EUR_BUTTON.setText("EUR");
            USD_BUTTON.setText("USD");
            EUR_BUTTON.setCallbackData("/EUR_CHANGING");
            USD_BUTTON.setCallbackData("/USD_CHANGING");
        } else if (user.isUsd() && user.isEur()) {
            EUR_BUTTON.setText(EMOJI + "EUR");
            USD_BUTTON.setText(EMOJI + "USD");
            EUR_BUTTON.setCallbackData("/EUR_CHECKED");
            USD_BUTTON.setCallbackData("/USD_CHECKED");
        } else if (user.isUsd()) {
            EUR_BUTTON.setText("EUR");
            USD_BUTTON.setText(EMOJI + "USD");
            EUR_BUTTON.setCallbackData("/EUR_CHANGING");
            USD_BUTTON.setCallbackData("/USD_CHECKED");
        } else {
            EUR_BUTTON.setText(EMOJI + "EUR");
            USD_BUTTON.setText("USD");
            EUR_BUTTON.setCallbackData("/EUR_CHECKED");
            USD_BUTTON.setCallbackData("/USD_CHANGING");
        }

        List<InlineKeyboardButton> eur = List.of(EUR_BUTTON);
        List<InlineKeyboardButton> usd = List.of(USD_BUTTON);
        List<InlineKeyboardButton> chosen = List.of(CURRENCY_CHOOSE_BUTTON);
        List<List<InlineKeyboardButton>> rowsInLine = List.of(usd, eur, chosen);

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        markupInline.setKeyboard(rowsInLine);

        return markupInline;
    }

    // settings of alert time keyboard
    public static ReplyKeyboardMarkup initTimeKeyboard() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();

        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);

        ArrayList<KeyboardRow> keyboardRows = new ArrayList<>();

        KeyboardRow firstRow = new KeyboardRow();
        KeyboardRow secondRow = new KeyboardRow();
        KeyboardRow thirdRow = new KeyboardRow();
        KeyboardRow lastRow = new KeyboardRow();
        keyboardRows.add(firstRow);
        keyboardRows.add(secondRow);
        keyboardRows.add(thirdRow);
        keyboardRows.add(lastRow);

        firstRow.add(new KeyboardButton("9"));
        firstRow.add(new KeyboardButton("10"));
        firstRow.add(new KeyboardButton("11"));
        secondRow.add(new KeyboardButton("12"));
        secondRow.add(new KeyboardButton("13"));
        secondRow.add(new KeyboardButton("14"));
        thirdRow.add(new KeyboardButton("15"));
        thirdRow.add(new KeyboardButton("16"));
        thirdRow.add(new KeyboardButton("17"));
        lastRow.add(new KeyboardButton("18"));
        lastRow.add(new KeyboardButton("Вимкнути оповіщення"));

        replyKeyboardMarkup.setKeyboard(keyboardRows);
        return replyKeyboardMarkup;
    }
}
