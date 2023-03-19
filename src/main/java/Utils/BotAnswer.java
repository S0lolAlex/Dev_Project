package Utils;

import org.functionalInteface.MessageSender;
import lombok.Getter;
import org.dto.MonobankService;
import org.dto.NBUService;
import org.dto.PrivatService;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class BotAnswer {
    @Getter
    private static Map<String, NotificationScheduler> schedules = new HashMap<>();
    public static Map<String, UserPreferences> preferences = new HashMap<>();

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
                MessageUtil.chooseBank(message, preferences.get(message.getChatId()));
                break;
            case "/Count":
                MessageUtil.countFloatPoint(message, preferences.get(message.getChatId()));
                break;
            case "/Currency":
                MessageUtil.chooseCurrency(message, preferences.get(message.getChatId()));
                break;
            case "/time":
                MessageUtil.setTime(message, preferences.get(message.getChatId()).getTime());
                break;
            case "/2_CHANGING":
                preferences.get(message.getChatId()).isTwo();
                preferences.get(message.getChatId()).setDf(new DecimalFormat("#0.00"));
                MessageUtil.returnMenu(message, "2 знаки");
                break;
            case "/3_CHANGING":
                preferences.get(message.getChatId()).isThree();
                preferences.get(message.getChatId()).setDf(new DecimalFormat("#0.000"));
                MessageUtil.returnMenu(message, "3 знаки");
                break;
            case "/4_CHANGING":
                preferences.get(message.getChatId()).isFour();
                preferences.get(message.getChatId()).setDf(new DecimalFormat("#0.0000"));
                MessageUtil.returnMenu(message, "4 знаки");
                break;
            case "/Private_CHANGING":
                preferences.get(message.getChatId()).isPrivate();
                preferences.get(message.getChatId()).setBank(new PrivatService());
                MessageUtil.returnMenu(message, "ПриватБанк");
                break;
            case "/Mono_CHANGING":
                preferences.get(message.getChatId()).isMono();
                preferences.get(message.getChatId()).setBank(new MonobankService());
                MessageUtil.returnMenu(message, "Монобанк");
                break;
            case "/NBU_CHANGING":
                preferences.get(message.getChatId()).isNBU();
                preferences.get(message.getChatId()).setBank(new NBUService());
                MessageUtil.returnMenu(message, "Банк НБУ");

                break;
//            case "/USD":
//                preferences.get(message.getChatId()).setUsd(!preferences.get(message.getChatId()).isUsd());
//                break;
//            case "/EUR":
//                preferences.get(message.getChatId()).setEur(!preferences.get(message.getChatId()).isEur());
//                break;
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
            case "10":
            case "11":
            case "12":
            case "13":
            case "14":
            case "15":
            case "16":
            case "17":
            case "18":
                scheduleNotification(message, sender, receivedMessage);
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

    public void botChangingAnswerUtils(String receivedMessage, EditMessageReplyMarkup message, MessageSender sender) {
        switch (receivedMessage) {
            case "/USD_CHANGING":
            case "/USD_CHECKED":
                preferences.get(message.getChatId()).setUsd(!preferences.get(message.getChatId()).isUsd());
                MessageUtil.chooseCurrencyChanging(message, preferences.get(message.getChatId()));
                break;
            case "/EUR_CHANGING":
            case "/EUR_CHECKED":
                preferences.get(message.getChatId()).setEur(!preferences.get(message.getChatId()).isEur());
                MessageUtil.chooseCurrencyChanging(message, preferences.get(message.getChatId()));
                break;
            case "/Private_CHANGING":
            case "/Private_CHECKED":
                preferences.get(message.getChatId()).setPrivate(!preferences.get(message.getChatId()).isPrivate());
                MessageUtil.chooseBankChanging(message, preferences.get(message.getChatId()));
                break;
            case "/Mono_CHANGING":
                case "/Mono_CHECKED":
                    preferences.get(message.getChatId()).setMono(!preferences.get(message.getChatId()).isMono());
                MessageUtil.chooseBankChanging(message, preferences.get(message.getChatId()));
                break;
            case "/NBU_CHANGING":
            case "/NBU_CHECKED":
                preferences.get(message.getChatId()).setNBU(!preferences.get(message.getChatId()).isNBU());
                MessageUtil.chooseBankChanging(message, preferences.get(message.getChatId()));
                break;
            case "/2_CHANGING":
            case "/2_CHECKED":
                preferences.get(message.getChatId()).setTwo(!preferences.get(message.getChatId()).isTwo());
                MessageUtil.countFloatPointChanging(message, preferences.get(message.getChatId()));
                break;
            case "/3_CHANGING":
            case "/3_CHECKED":
                preferences.get(message.getChatId()).setThree(!preferences.get(message.getChatId()).isThree());
                MessageUtil.countFloatPointChanging(message, preferences.get(message.getChatId()));
                break;
            case "/4_CHANGING":
            case "/4_CHECKED":
                preferences.get(message.getChatId()).setFour(!preferences.get(message.getChatId()).isFour());
                MessageUtil.countFloatPointChanging(message, preferences.get(message.getChatId()));
                break;
                default:
                break;
        }
    }

    private void scheduleNotification(SendMessage message, MessageSender sender, String hour) {
        MessageUtil.startSchedule(message.getChatId(),
                messageAlert(message.getChatId()), getAnswer(message.getChatId()), Integer.parseInt(hour), sender);
        preferences.get(message.getChatId()).setTime(hour);
        MessageUtil.returnMenu(message, "Обраний час: " + preferences.get(message.getChatId()).getTime());
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
