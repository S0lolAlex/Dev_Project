package Utils;

import ApiTelegramBot.MessageSender;
import Utils.scheduler.NotificationScheduler;
import lombok.Getter;
import org.buttons.BotCommands;
import org.buttons.Buttons;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class MessageUtil implements BotCommands {
    @Getter
    private static NotificationScheduler shedule = null;
    public void setTime(SendMessage message, String time){
        message.setText("Оберіть час оповіщення. \n " +
                "Поточний час оповіщень :" + time);
        message.setReplyMarkup(Buttons.initTimeKeyboard());
    }

    public void returnMenu(SendMessage message,String text) {
        message.setText( text);
        message.setReplyMarkup(Buttons.startMarkup());
    }

    public void startBot(SendMessage message,String userName) {
        message.setText("Привіт, " + userName + "! Я Телеграм бот.'");
        message.setReplyMarkup(Buttons.startMarkup());
    }

    public void getInfo(SendMessage message,String text) {
        message.setText(text);
        message.setReplyMarkup(Buttons.startMarkup());
    }

    public void sendHelpText(SendMessage message) {
        message.setText(HELP_TEXT);
    }

    public void setSettings(SendMessage message) {
        message.setText("Налаштування");
        message.setReplyMarkup(Buttons.settingMarkup());
    }

    public void chooseBank(SendMessage message) {
        message.setText("Оберіть банк");
        message.setReplyMarkup(Buttons.banks());
    }

    public void countFloatPoint(SendMessage message) {
        message.setText("Оберіть кількість знаків після коми");
        message.setReplyMarkup(Buttons.setFloatPoint());
    }

    public void chooseCurrency(SendMessage message) {
        message.setText("Оберіть валюту");
        message.setReplyMarkup(Buttons.chooseCurrency());
    }

    public void startSchedule(SendMessage message, String text, int hours, MessageSender sender) {
        try {
            if (shedule == null) {
                shedule = createScheduler(message, text, hours, sender);
            }
            if (shedule.isRun()) {
                shedule.stop();
                shedule = createScheduler(message, text, hours, sender);
            }
            shedule.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static NotificationScheduler createScheduler(SendMessage message, String text, int hours, MessageSender sender) {
        return new NotificationScheduler(hours, () -> {
            BotAnswer.getMESSAGE_MENU().getInfo(message, text);
            sender.sendMessage(message);
        });
    }
}
