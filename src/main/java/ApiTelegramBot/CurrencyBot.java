package ApiTelegramBot;

import org.buttons.BotCommands;
import org.buttons.Buttons;
import org.dto.MonobankService;
import org.dto.NBUService;
import org.dto.PrivatService;
import org.functionalInteface.BanksUtil;
import org.scheduler.DailyNotification;
import org.scheduler.NotificationScheduler;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;


public class CurrencyBot extends TelegramLongPollingBot implements BotCommands {
    private static BanksUtil BANK = new PrivatService();
    private static DecimalFormat df = new DecimalFormat();
    private static String time = "18";
    private static Map<Long, NotificationScheduler> schedules = new HashMap<>();

    @Override
    public String getBotUsername() {
        return "botName";
    }

    @Override
    public String getBotToken() {
        return "botToken";
    }

    public CurrencyBot() {
        Buttons.initKeyboard();
        try {
            this.execute(new SetMyCommands(LIST_OF_COMMANDS, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        String reseive;
        long chatId = 0;
        String userName = null;
        if (update.hasMessage()) {
            chatId = update.getMessage().getChatId();
            userName = update.getMessage().getFrom().getFirstName();

            if (update.getMessage().hasText()) {
                reseive = update.getMessage().getText();
                botAnswerUtils(reseive, chatId, userName);
            }
        } else if (update.hasCallbackQuery()) {
            chatId = update.getCallbackQuery().getMessage().getChatId();
            userName = update.getCallbackQuery().getFrom().getFirstName();
            reseive = update.getCallbackQuery().getData();
            botAnswerUtils(reseive, chatId, userName);
        }
    }

    private void botAnswerUtils(String receivedMessage, long chatId, String userName) {
        switch (receivedMessage) {
            case "/start":
                startBot(chatId, userName);
                break;
            case "/help":
                sendHelpText(chatId);
                break;
            case "/Settings":
                setSettings(chatId);
                break;
            case "/Banks":
                chooseBank(chatId);
                break;
            case "/Count":
                countFloatPoint(chatId);
                break;
            case "/Currency":
                chooseCurrency(chatId);
                break;
            case "/time":
                setTime(chatId,time);
                break;
            case "/2":
                returnMenu(chatId, "2 знака");
                df = new DecimalFormat("#0.00");
                break;
            case "/3":
                returnMenu(chatId, "3 знака");
                df = new DecimalFormat("#0.000");
                break;
            case "/4":
                returnMenu(chatId, "4 знака");
                df = new DecimalFormat("#0.0000");
                break;
            case "/Private":
                returnMenu(chatId, "ПриватБанк");
                BANK = new PrivatService();
                break;
            case "/Mono":
                returnMenu(chatId, "Монобанк");
                BANK = new MonobankService();
                break;
            case "/NBU":
                returnMenu(chatId, "Банк НБУ");
                BANK = new NBUService();
                break;
            case "9":
                startSchedule(receivedMessage, chatId, 9);
                break;
            case "10":
                startSchedule(receivedMessage, chatId, 10);
                break;
            case"11":
                startSchedule(receivedMessage, chatId, 11);
                break;
            case "12":
                startSchedule(receivedMessage, chatId, 12);
                break;
            case "13":
                startSchedule(receivedMessage, chatId, 13);
                break;
            case "14":
                startSchedule(receivedMessage, chatId, 14);
                break;
            case"15":
                startSchedule(receivedMessage, chatId, 15);
                break;
            case"16":
                startSchedule(receivedMessage, chatId, 16);
                break;
            case"17":
                startSchedule(receivedMessage, chatId, 17);
                break;
            case "18":
                startSchedule(receivedMessage, chatId, 18);
                break;
            case "Выключить уведомления":
                stopSchedule(chatId);
                time = "Оповещения отключены";
                returnMenu(chatId,time);
                break;
            case "/get":
                String answer = BANK.getCurrency("USD", df);
                getInfo(chatId, answer);
                break;
            default:
                break;
        }
    }

    private void startSchedule(String receivedMessage, long chatId, int hours) {
        stopSchedule(chatId);
        schedules.put(chatId, new NotificationScheduler(hours, () -> {
            String answer = BANK.getCurrency("USD", df);
            getInfo(chatId, answer);
        }));
        schedules.get(chatId).start();
        time = receivedMessage;
        returnMenu(chatId,time);
    }
    
    private void stopSchedule(long chatId) {
        if (schedules.get(chatId) != null) {
            schedules.get(chatId).stop();
            schedules.remove(chatId);
        }
    }

    private void setTime(long chatId, String time){
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Выберите время оповещения. \n " +
                "Cейчас выбрано :" + time);
        message.setReplyMarkup(Buttons.initTimeKeyboard());
        try {
            execute(message);
        }catch (TelegramApiException e){
            e.printStackTrace();
        }
    }

    private void returnMenu(long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Вы выбрали " + text);
        message.setReplyMarkup(Buttons.startMarkup());
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void startBot(long chatId, String userName) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Hi, " + userName + "! I'm a Telegram bot.'");
        message.setReplyMarkup(Buttons.startMarkup());

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void getInfo(long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);
        message.setReplyMarkup(Buttons.startMarkup());

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void sendHelpText(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(HELP_TEXT);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void setSettings(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Settings");
        message.setReplyMarkup(Buttons.settingMarkup());

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void chooseBank(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("choose the bank");
        message.setReplyMarkup(Buttons.banks());

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void countFloatPoint(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("задайте кол-во знаков");
        message.setReplyMarkup(Buttons.setFloatPoint());

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void chooseCurrency(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Какие валюты?");
        message.setReplyMarkup(Buttons.chooseCurrency());

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
