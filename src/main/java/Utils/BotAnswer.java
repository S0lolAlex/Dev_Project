package Utils;

import org.dto.MonobankService;
import org.dto.NBUService;
import org.dto.PrivatService;
import org.functionalInteface.BanksUtil;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.text.DecimalFormat;
public class BotAnswer {
    private static final MessageUtil MESSAGE_MENU = new MessageUtil();
    private static BanksUtil BANK = new PrivatService();
    private static DecimalFormat df = new DecimalFormat();
    private static boolean isUsd = false;
    private static boolean isEur = false;
    private static boolean isOne = true;
    private static String currency = "USD";
    public void botAnswerUtils(String receivedMessage, SendMessage message, String userName) {
        switch (receivedMessage) {
            case "/start":
                MESSAGE_MENU.startBot(message, userName);
                break;
            case "/help":
                MESSAGE_MENU.sendHelpText(message);
                break;
            case "/Settings":
                MESSAGE_MENU.setSettings(message);
                break;
            case "/Banks":
                MESSAGE_MENU.chooseBank(message);
                break;
            case "/Count":
                MESSAGE_MENU.countFloatPoint(message);
                break;
            case "/Currency":
                MESSAGE_MENU.chooseCurrency(message);
                break;
            case "/time":
                MESSAGE_MENU.setTime(message,"Задайте час оповіщень");
                break;
            case "/2":
                MESSAGE_MENU.returnMenu(message, "2 знаки");
                df = new DecimalFormat("#0.00");
                break;
            case "/3":
                MESSAGE_MENU.returnMenu(message, "3 знаки");
                df = new DecimalFormat("#0.000");
                break;
            case "/4":
                MESSAGE_MENU.returnMenu(message, "4 знаки");
                df = new DecimalFormat("#0.0000");
                break;
            case "/Private":
                MESSAGE_MENU.returnMenu(message, "ПриватБанк");
                BANK = new PrivatService();
                break;
            case "/Mono":
                MESSAGE_MENU.returnMenu(message, "Монобанк");
                BANK = new MonobankService();
                break;
            case "/NBU":
                MESSAGE_MENU.returnMenu(message, "Банк НБУ");
                BANK = new NBUService();
                break;
            case "/USD":
                isUsd = !isUsd;
                break;
            case "/EUR":
                isEur = !isEur;
                break;
            case "/Chosen":
                if (!isUsd && !isEur) {
                    isOne = true;
                    currency = "USD";
                    MESSAGE_MENU.returnMenu(message, "За замовчуванням: USD");
                } else if (isUsd && isEur) {
                    isOne = false;
                    MESSAGE_MENU.returnMenu(message, "обрано: USD и EUR");
                } else if (isUsd) {
                    isOne = true;
                    currency = "USD";
                    MESSAGE_MENU.returnMenu(message, "Поточна валюта: " + currency);
                } else {
                    isOne = true;
                    currency = "EUR";
                    MESSAGE_MENU.returnMenu(message, "Поточна валюта: " + currency);
                }
                break;
            case "09":
            case "10":
            case"11":
            case"12":
            case"13":
            case"14":
            case"15":
            case"16":
            case"17":
            case"18":
                MESSAGE_MENU.returnMenu(message,receivedMessage);
                break;
            case "Вимкнути оповіщення":
                MESSAGE_MENU.returnMenu(message,receivedMessage);
                break;

            case "/get":
                String answer;
                if (isOne) {
                    answer = BANK.getCurrency(currency, df);
                } else {
                    answer = BANK.getCurrency("USD", df) + "\n" + BANK.getCurrency("EUR", df);
                }
                MESSAGE_MENU.getInfo(message, answer);
                break;
            default:
                break;
        }
    }
}
