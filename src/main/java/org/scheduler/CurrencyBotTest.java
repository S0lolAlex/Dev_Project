package org.scheduler;

import org.buttons.BotCommands;
import org.buttons.Buttons;
import org.dto.MonobankService;
import org.dto.NBUService;
import org.dto.PrivatService;
import org.functionalInteface.BanksUtil;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.text.DecimalFormat;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class CurrencyBotTest extends TelegramLongPollingBot implements BotCommands {
    public static BanksUtil BANK = new PrivatService();
    public static DecimalFormat df = new DecimalFormat();
    public static String time = "18";
    Map<Long, TestThread> threads = new HashMap<>();
    Map<Long, Timer> timers = new HashMap<>();

    @Override
    public String getBotUsername() {
        return "TryButtons_bot";
    }

    @Override
    public String getBotToken() {
        return "6288500881:AAHXD11jLHI3OrTIVd3ynfwLYc3R9lGN9qo";
    }

    public CurrencyBotTest() {
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
                //Thread newThread = new TestThread(chatId, 5);
                threads.put(chatId, new TestThread(chatId, 5));
                TestThread threadToStart = threads.get(chatId);
                threadToStart.start();
            case "10":
                timers.put(chatId, new Timer(2, 22));
                Timer timerToStart = timers.get(chatId);
                timerToStart.start(chatId);

            case"11":
                        case"12":
                            case"13":
                                case"14":
                                    case"15":
                                        case"16":
                                            case"17":
                                                case"18":time = receivedMessage;
                                                    returnMenu(chatId,time);
                                                    break;
                                                    case "Выключить уведомления":
                                                        //threads.get(chatId).setRunning(false);
                                                        timers.get(chatId).stop();
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
    class MyRunnableTask implements Runnable {
        private long chatId;

        public MyRunnableTask(long chatId) {
            this.chatId = chatId;
        }

        @Override
        public void run() {
            String answer = BANK.getCurrency("USD", df);
            getInfo(chatId, answer);
        }
    }
    class Timer {
        private int hours;
        private int minutes;

        private ScheduledFuture<?> scheduledFuture;

        public Timer(int hours, int minutes) {
            this.hours = hours;
            this.minutes = minutes;
        }

        public void start(long chatId) {
            ZonedDateTime now = ZonedDateTime.now();
            ZonedDateTime nextRun = now.withHour(hours).withMinute(minutes).withSecond(0);
            if(now.compareTo(nextRun) > 0)
                nextRun = nextRun.plusDays(1);

            Duration duration = Duration.between(now, nextRun);
            long initialDelay = duration.getSeconds();

            ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
            scheduledFuture = scheduler.scheduleAtFixedRate(new MyRunnableTask(chatId),
                    initialDelay,
                    TimeUnit.DAYS.toSeconds(1),
                    TimeUnit.SECONDS);
        }

        public void stop() {
            scheduledFuture.cancel(true);
        }
    }

    class TestThread extends Thread {
        private long chatId;
        private long setTime;
        private boolean isRunning = true;

        public void setRunning(boolean running) {
            isRunning = running;
        }

        public TestThread(long chatId, int setTime) {
            this.chatId = chatId;
            this.setTime = setTime;
        }

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        @Override
        public void run() {
            while (isRunning) {
                try {
                    Thread.sleep(setTime * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String answer = BANK.getCurrency("USD", df);
                getInfo(chatId, answer);
            }
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

    public void getInfo(long chatId, String text) {
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
