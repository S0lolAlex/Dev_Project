package Utils;

import org.functionalInteface.MessageSender;
import lombok.Getter;
import lombok.NonNull;
import org.buttons.BotCommands;
import org.buttons.Buttons;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class MessageUtil implements BotCommands {
    @Getter
    private static NotificationScheduler shedule = null;

    public void setTime(SendMessage message, String time) {
        message.setText("Оберіть час оповіщення. \n " +
                "Поточний час оповіщень :" + time);
        message.setReplyMarkup(Buttons.initTimeKeyboard());
    }

    public void returnMenu(SendMessage message, String text) {
        message.setText(text);
        message.setReplyMarkup(Buttons.startMarkup());
    }

    public void startBot(SendMessage message) {
        message.setText("Ласкаво просимо. Цей бот допоможе відслідковувати актуальні курси валют");
        message.setReplyMarkup(Buttons.startMarkup());
    }

    public void getInfo(SendMessage message, String text) {
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

    public void chooseBank(SendMessage message,UserPreferences user) {
        message.setText("Оберіть банк");
        message.setReplyMarkup(Buttons.banks(user));
    }

    public void countFloatPoint(SendMessage message,UserPreferences user) {
        message.setText("Оберіть кількість знаків після коми");
        message.setReplyMarkup(Buttons.setFloatPoint(user));
    }

    public void chooseCurrency(SendMessage message,UserPreferences user) {
        message.setText("Оберіть валюту");
        message.setReplyMarkup(Buttons.chooseCurrency(user));
    }

    @NonNull
    public void startSchedule(String chatId, SendMessage message, String text, int hours, MessageSender sender) {
        stopSchedule(chatId);
        try {
            BotAnswer.getSchedules().put(chatId, new NotificationScheduler(hours, () -> {
                BotAnswer.getMESSAGE_MENU().getInfo(message, text);
                sender.sendMessage(message);
            }));
            BotAnswer.getSchedules().get(chatId).start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void stopSchedule(String chatId) {
        if (BotAnswer.getSchedules().get(chatId) != null) {
            BotAnswer.getSchedules().get(chatId).stop();
            BotAnswer.getSchedules().remove(chatId);
        }
    }
}
