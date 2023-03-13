package Utils;

import org.functionalInteface.MessageSender;
import lombok.Getter;
import org.dto.MonobankService;
import org.dto.NBUService;
import org.dto.PrivatService;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class BotAnswer {
    @Getter
    private static final MessageUtil MESSAGE_MENU = new MessageUtil();
    @Getter
    private static Map<String, NotificationScheduler> schedules = new HashMap<>();
    private static Map<String, UserPreferences> preferences = new HashMap<>();

    public void botAnswerUtils(String receivedMessage, SendMessage message, MessageSender sender) {
        switch (receivedMessage) {
            case "/start":
                MESSAGE_MENU.startBot(message);
                if (preferences.get(message.getChatId()) == null) {
                    preferences.put(message.getChatId(), new UserPreferences());
                }
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
                MESSAGE_MENU.setTime(message, preferences.get(message.getChatId()).getTime());
                break;
            case "/2":
                MESSAGE_MENU.returnMenu(message, "2 знаки");
                preferences.get(message.getChatId()).setDf(new DecimalFormat("#0.00"));
                break;
            case "/3":
                MESSAGE_MENU.returnMenu(message, "3 знаки");
                preferences.get(message.getChatId()).setDf(new DecimalFormat("#0.000"));
                break;
            case "/4":
                MESSAGE_MENU.returnMenu(message, "4 знаки");
                preferences.get(message.getChatId()).setDf(new DecimalFormat("#0.0000"));
                break;
            case "/Private":
                MESSAGE_MENU.returnMenu(message, "ПриватБанк");
                preferences.get(message.getChatId()).setBank(new PrivatService());
                break;
            case "/Mono":
                MESSAGE_MENU.returnMenu(message, "Монобанк");
                preferences.get(message.getChatId()).setBank(new MonobankService());
                break;
            case "/NBU":
                MESSAGE_MENU.returnMenu(message, "Банк НБУ");
                preferences.get(message.getChatId()).setBank(new NBUService());
                break;
            case "/USD":
                preferences.get(message.getChatId()).setUsd(!preferences.get(message.getChatId()).isUsd());
                break;
            case "/EUR":
                preferences.get(message.getChatId()).setEur(!preferences.get(message.getChatId()).isEur());
                break;
            case "/Chosen":
                if (!preferences.get(message.getChatId()).isUsd() && !preferences.get(message.getChatId()).isEur()) {
                    preferences.get(message.getChatId()).setOne(true);
                    preferences.get(message.getChatId()).setCurrency("USD");
                    MESSAGE_MENU.returnMenu(message, "За замовчуванням: USD");
                } else if (preferences.get(message.getChatId()).isUsd() && preferences.get(message.getChatId()).isEur()) {
                    preferences.get(message.getChatId()).setOne(false);
                    MESSAGE_MENU.returnMenu(message, "Обрано: USD і EUR");
                } else if (preferences.get(message.getChatId()).isUsd()) {
                    preferences.get(message.getChatId()).setOne(true);
                    preferences.get(message.getChatId()).setCurrency("USD");
                    MESSAGE_MENU.returnMenu(message,
                            "Поточна валюта: " + preferences.get(message.getChatId()).getCurrency());
                } else {
                    preferences.get(message.getChatId()).setOne(true);
                    preferences.get(message.getChatId()).setCurrency("EUR");
                    MESSAGE_MENU.returnMenu(message,
                            "Поточна валюта: " + preferences.get(message.getChatId()).getCurrency());
                }
                break;
            case "9":
                MESSAGE_MENU.startSchedule(message.getChatId(),
                        messageAlert(message.getChatId()), getAnswer(message.getChatId()), 9, sender);
                preferences.get(message.getChatId()).setTime("9");
                MESSAGE_MENU.returnMenu(message, "Обраний час: " + preferences.get(message.getChatId()).getTime());
                break;
            case "10":
                MESSAGE_MENU.startSchedule(message.getChatId(),
                        messageAlert(message.getChatId()), getAnswer(message.getChatId()), 10, sender);
                preferences.get(message.getChatId()).setTime("10");
                MESSAGE_MENU.returnMenu(message, "Обраний час: " + preferences.get(message.getChatId()).getTime());
                break;
            case "11":
                MESSAGE_MENU.startSchedule(message.getChatId(),
                        messageAlert(message.getChatId()), getAnswer(message.getChatId()), 11, sender);
                preferences.get(message.getChatId()).setTime("11");
                MESSAGE_MENU.returnMenu(message, "Обраний час: " + preferences.get(message.getChatId()).getTime());
                break;
            case "12":
                MESSAGE_MENU.startSchedule(message.getChatId(),
                        messageAlert(message.getChatId()), getAnswer(message.getChatId()), 12, sender);
                preferences.get(message.getChatId()).setTime("12");
                MESSAGE_MENU.returnMenu(message, "Обраний час: " + preferences.get(message.getChatId()).getTime());
                break;
            case "13":
                MESSAGE_MENU.startSchedule(message.getChatId(),
                        messageAlert(message.getChatId()), getAnswer(message.getChatId()), 13, sender);
                preferences.get(message.getChatId()).setTime("13");
                MESSAGE_MENU.returnMenu(message, "Обраний час: " + preferences.get(message.getChatId()).getTime());
                break;
            case "14":
                MESSAGE_MENU.startSchedule(message.getChatId(),
                        messageAlert(message.getChatId()), getAnswer(message.getChatId()), 14, sender);
                preferences.get(message.getChatId()).setTime("14");
                MESSAGE_MENU.returnMenu(message, "Обраний час: " + preferences.get(message.getChatId()).getTime());
                break;
            case "15":
                MESSAGE_MENU.startSchedule(message.getChatId(),
                        messageAlert(message.getChatId()), getAnswer(message.getChatId()), 15, sender);
                preferences.get(message.getChatId()).setTime("15");
                MESSAGE_MENU.returnMenu(message, "Обраний час: " + preferences.get(message.getChatId()).getTime());
                break;
            case "16":
                MESSAGE_MENU.startSchedule(message.getChatId(),
                        messageAlert(message.getChatId()), getAnswer(message.getChatId()), 16, sender);
                preferences.get(message.getChatId()).setTime("16");
                MESSAGE_MENU.returnMenu(message, "Обраний час: " + preferences.get(message.getChatId()).getTime());
                break;
            case "17":
                MESSAGE_MENU.startSchedule(message.getChatId(),
                        messageAlert(message.getChatId()), getAnswer(message.getChatId()), 17, sender);
                preferences.get(message.getChatId()).setTime("17");
                MESSAGE_MENU.returnMenu(message, "Обраний час: " + preferences.get(message.getChatId()).getTime());
                break;
            case "18":
                MESSAGE_MENU.startSchedule(message.getChatId(),
                        messageAlert(message.getChatId()), getAnswer(message.getChatId()), 18, sender);
                preferences.get(message.getChatId()).setTime("18");
                MESSAGE_MENU.returnMenu(message, "Обраний час: " + preferences.get(message.getChatId()).getTime());
                break;
            case "Вимкнути оповіщення":
                schedules.get(message.getChatId()).stop();
                preferences.get(message.getChatId()).setTime("вимкнено");
                MESSAGE_MENU.returnMenu(message, "Оповіщення вимкнені");
                break;
            case "/get":
                MESSAGE_MENU.getInfo(message, getAnswer(message.getChatId()));
                break;
            default:
                break;
        }
    }

    private String getAnswer(String chatId) {
        String answer;
        if (preferences.get(chatId).isOne()) {
            answer = preferences.get(chatId).getBank().getCurrency(preferences.get(chatId).getCurrency(),
                    preferences.get(chatId).getDf());
        } else {
            answer = preferences.get(chatId).getBank().getCurrency("USD", preferences.get(chatId).getDf()) +
                    "\n" + preferences.get(chatId).getBank().getCurrency("EUR", preferences.get(chatId).getDf());
        }
        return answer;
    }

    private SendMessage messageAlert(String chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(getAnswer(chatId));
        return message;
    }
}
