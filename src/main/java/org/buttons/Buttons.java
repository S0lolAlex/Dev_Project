package org.buttons;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class Buttons {
    private static final InlineKeyboardButton SETTINGS_BUTTON = new InlineKeyboardButton("Налаштування");
    private static final InlineKeyboardButton GET_INFO_BUTTON = new InlineKeyboardButton("Отримату інфо");
    private static final InlineKeyboardButton COUNT_BUTTON = new InlineKeyboardButton("Кількість знаків після коми");
    private static final InlineKeyboardButton BANKS_BUTTON = new InlineKeyboardButton("Банк");
    private static final InlineKeyboardButton CURRENCY_BUTTON = new InlineKeyboardButton("Валюти");
    private static final InlineKeyboardButton TIME_BUTTON = new InlineKeyboardButton("Час оповіщення");
    private static final InlineKeyboardButton BUTTON_2 = new InlineKeyboardButton("2 знаки");
    private static final InlineKeyboardButton BUTTON_3 = new InlineKeyboardButton("3 знаки");
    private static final InlineKeyboardButton BUTTON_4 = new InlineKeyboardButton("4 знаки");
    private static final InlineKeyboardButton PRIVATE_BUTTON = new InlineKeyboardButton("ПриватБанк");
    private static final InlineKeyboardButton MONO_BUTTON = new InlineKeyboardButton("МоноБанк");
    private static final InlineKeyboardButton NBU_BUTTON = new InlineKeyboardButton("НБУ");
    private static final InlineKeyboardButton EUR_BUTTON = new InlineKeyboardButton("EUR");
    private static final InlineKeyboardButton USD_BUTTON = new InlineKeyboardButton("USD");
    private static final InlineKeyboardButton CURRENCY_CHOOSE_BUTTON = new InlineKeyboardButton("Підтвердити");


    public static void initKeyboard(){
        SETTINGS_BUTTON.setCallbackData("/Settings");
        GET_INFO_BUTTON.setCallbackData("/get");
        COUNT_BUTTON.setCallbackData("/Count");
        BANKS_BUTTON.setCallbackData("/Banks");
        CURRENCY_BUTTON.setCallbackData("/Currency");
        TIME_BUTTON.setCallbackData("/time");
        BUTTON_2.setCallbackData("/2");
        BUTTON_3.setCallbackData("/3");
        BUTTON_4.setCallbackData("/4");
        PRIVATE_BUTTON.setCallbackData("/Private");
        MONO_BUTTON.setCallbackData("/Mono");
        NBU_BUTTON.setCallbackData("/NBU");
        EUR_BUTTON.setCallbackData("/EUR");
        USD_BUTTON.setCallbackData("/USD");
        CURRENCY_CHOOSE_BUTTON.setCallbackData("/Chosen");
    }


    public static InlineKeyboardMarkup startMarkup() {
        List<InlineKeyboardButton> start = List.of(SETTINGS_BUTTON);
        List<InlineKeyboardButton> getInfo = List.of(GET_INFO_BUTTON);
        List<List<InlineKeyboardButton>> rowsInLine = List.of(start,getInfo);

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        markupInline.setKeyboard(rowsInLine);

        return markupInline;
    }
    public static InlineKeyboardMarkup settingMarkup() {
        List<InlineKeyboardButton> count = List.of(COUNT_BUTTON);
        List<InlineKeyboardButton> currency = List.of(CURRENCY_BUTTON);
        List<InlineKeyboardButton> banks = List.of(BANKS_BUTTON);
        List<InlineKeyboardButton> time = List.of(TIME_BUTTON);
        List<List<InlineKeyboardButton>> rowsInLine = List.of(count, currency,banks,time);

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        markupInline.setKeyboard(rowsInLine);

        return markupInline;
    }
    public static InlineKeyboardMarkup setFloatPoint(){
        List<InlineKeyboardButton> two = List.of(BUTTON_2);
        List<InlineKeyboardButton> three = List.of(BUTTON_3);
        List<InlineKeyboardButton> four = List.of(BUTTON_4);
        List<List<InlineKeyboardButton>> rowsInLine = List.of(two,three,four);

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        markupInline.setKeyboard(rowsInLine);

        return markupInline;
    }
    public static InlineKeyboardMarkup banks() {

        List<InlineKeyboardButton> privat = List.of(PRIVATE_BUTTON);
        List<InlineKeyboardButton> monobank = List.of(MONO_BUTTON);
        List<InlineKeyboardButton> nbu = List.of(NBU_BUTTON);
        List<List<InlineKeyboardButton>> rowsInLine = List.of(privat,monobank,nbu);

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        markupInline.setKeyboard(rowsInLine);

        return markupInline;
    }
    public static InlineKeyboardMarkup chooseCurrency() {

        List<InlineKeyboardButton> eur = List.of(EUR_BUTTON);
        List<InlineKeyboardButton> usd = List.of(USD_BUTTON);
        List<InlineKeyboardButton> chosen = List.of(CURRENCY_CHOOSE_BUTTON);
        List<List<InlineKeyboardButton>> rowsInLine = List.of(usd,eur,chosen);

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        markupInline.setKeyboard(rowsInLine);

        return markupInline;
    }
    public static ReplyKeyboardMarkup initTimeKeyboard(){
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();;

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
