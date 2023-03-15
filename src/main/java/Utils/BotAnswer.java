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
    private static Map<String, NotificationScheduler> schedules = new HashMap<>();
    private static Map<String, UserPreferences> preferences = new HashMap<>();

    public void botAnswerUtils(String receivedMessage, SendMessage message, MessageSender sender) {
        switch (receivedMessage) {
            case "/start":
                MessageUtil.startBot(message);
                if (preferences.get(message.getChatId()) == null) {
                    preferences.put(message.getChatId(), new UserPreferences());
                }
                break;
            case "/help":
                MessageUtil.sendHelpText(message);
                break;
            case "/Settings":
                MessageUtil.setSettings(message);
                break;
            case "/Banks":
                MessageUtil.chooseBank(message,preferences.get(message.getChatId()));
                break;
            case "/Count":
                MessageUtil.countFloatPoint(message,preferences.get(message.getChatId()));
                break;
            case "/Currency":
                MessageUtil.chooseCurrency(message,preferences.get(message.getChatId()));
                break;
            case "/time":
                MessageUtil.setTime(message, preferences.get(message.getChatId()).getTime());
                break;
            case "/2":
                MessageUtil.returnMenu(message, "2 знаки");
                preferences.get(message.getChatId()).setDf(new DecimalFormat("#0.00"));
                break;
            case "/3":
                MessageUtil.returnMenu(message, "3 знаки");
                preferences.get(message.getChatId()).setDf(new DecimalFormat("#0.000"));
                break;
            case "/4":
                MessageUtil.returnMenu(message, "4 знаки");
                preferences.get(message.getChatId()).setDf(new DecimalFormat("#0.0000"));
                break;
            case "/Private":
                MessageUtil.returnMenu(message, "ПриватБанк");
                preferences.get(message.getChatId()).setBank(new PrivatService());
                break;
            case "/Mono":
                MessageUtil.returnMenu(message, "Монобанк");
                preferences.get(message.getChatId()).setBank(new MonobankService());
                break;
            case "/NBU":
                MessageUtil.returnMenu(message, "Банк НБУ");
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
                    MessageUtil.returnMenu(message, "За замовчуванням: USD");
                } else if (preferences.get(message.getChatId()).isUsd() && preferences.get(message.getChatId()).isEur()) {
                    preferences.get(message.getChatId()).setOne(false);
                    MessageUtil.returnMenu(message, "Обрано: USD і EUR");
                } else if (preferences.get(message.getChatId()).isUsd()) {
                    preferences.get(message.getChatId()).setOne(true);
                    preferences.get(message.getChatId()).setCurrency("USD");
                    MessageUtil.returnMenu(message,
                            "Поточна валюта: " + preferences.get(message.getChatId()).getCurrency());
                } else {
                    preferences.get(message.getChatId()).setOne(true);
                    preferences.get(message.getChatId()).setCurrency("EUR");
                    MessageUtil.returnMenu(message,
                            "Поточна валюта: " + preferences.get(message.getChatId()).getCurrency());
                }
                break;
            case "9":
                MessageUtil.startSchedule(message.getChatId(),
                        messageAlert(message.getChatId()), getAnswer(message.getChatId()), 9, sender);
                preferences.get(message.getChatId()).setTime("9");
                MessageUtil.returnMenu(message, "Обраний час: " + preferences.get(message.getChatId()).getTime());
                break;
            case "10":
                MessageUtil.startSchedule(message.getChatId(),
                        messageAlert(message.getChatId()), getAnswer(message.getChatId()), 10, sender);
                preferences.get(message.getChatId()).setTime("10");
                MessageUtil.returnMenu(message, "Обраний час: " + preferences.get(message.getChatId()).getTime());
                break;
            case "11":
                MessageUtil.startSchedule(message.getChatId(),
                        messageAlert(message.getChatId()), getAnswer(message.getChatId()), 11, sender);
                preferences.get(message.getChatId()).setTime("11");
                MessageUtil.returnMenu(message, "Обраний час: " + preferences.get(message.getChatId()).getTime());
                break;
            case "12":
                MessageUtil.startSchedule(message.getChatId(),
                        messageAlert(message.getChatId()), getAnswer(message.getChatId()), 12, sender);
                preferences.get(message.getChatId()).setTime("12");
                MessageUtil.returnMenu(message, "Обраний час: " + preferences.get(message.getChatId()).getTime());
                break;
            case "13":
                MessageUtil.startSchedule(message.getChatId(),
                        messageAlert(message.getChatId()), getAnswer(message.getChatId()), 13, sender);
                preferences.get(message.getChatId()).setTime("13");
                MessageUtil.returnMenu(message, "Обраний час: " + preferences.get(message.getChatId()).getTime());
                break;
            case "14":
                MessageUtil.startSchedule(message.getChatId(),
                        messageAlert(message.getChatId()), getAnswer(message.getChatId()), 14, sender);
                preferences.get(message.getChatId()).setTime("14");
                MessageUtil.returnMenu(message, "Обраний час: " + preferences.get(message.getChatId()).getTime());
                break;
            case "15":
                MessageUtil.startSchedule(message.getChatId(),
                        messageAlert(message.getChatId()), getAnswer(message.getChatId()), 15, sender);
                preferences.get(message.getChatId()).setTime("15");
                MessageUtil.returnMenu(message, "Обраний час: " + preferences.get(message.getChatId()).getTime());
                break;
            case "16":
                MessageUtil.startSchedule(message.getChatId(),
                        messageAlert(message.getChatId()), getAnswer(message.getChatId()), 16, sender);
                preferences.get(message.getChatId()).setTime("16");
                MessageUtil.returnMenu(message, "Обраний час: " + preferences.get(message.getChatId()).getTime());
                break;
            case "17":
                MessageUtil.startSchedule(message.getChatId(),
                        messageAlert(message.getChatId()), getAnswer(message.getChatId()), 17, sender);
                preferences.get(message.getChatId()).setTime("17");
                MessageUtil.returnMenu(message, "Обраний час: " + preferences.get(message.getChatId()).getTime());
                break;
            case "18":
                MessageUtil.startSchedule(message.getChatId(),
                        messageAlert(message.getChatId()), getAnswer(message.getChatId()), 18, sender);
                preferences.get(message.getChatId()).setTime("18");
                MessageUtil.returnMenu(message, "Обраний час: " + preferences.get(message.getChatId()).getTime());
                break;
            case "Вимкнути оповіщення":
                schedules.get(message.getChatId()).stop();
                preferences.get(message.getChatId()).setTime("вимкнено");
                MessageUtil.returnMenu(message, "Оповіщення вимкнені");
                break;
            case "/get":
                MessageUtil.getInfo(message, getAnswer(message.getChatId()));
                break;
            default:
                break;
        }
    }
    //return current currency by current user properties
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
    //create message to alert
    private SendMessage messageAlert(String chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(getAnswer(chatId));
        return message;
    }
}
