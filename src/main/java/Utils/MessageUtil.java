package Utils;

import Utils.scheduler.NotificationScheduler;
import lombok.Getter;
import org.buttons.BotCommands;
import org.buttons.Buttons;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;



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
//    public void startSchedule(SendMessage message, int hours) {
//        if(!shedule.isRun()){
//            shedule.stop();
//            shedule = new NotificationScheduler(hours, () -> {
//                try{
//                    execute(message);
//                }catch (TelegramApiException e){
//                    e.printStackTrace();
//                }
//            });}
//        shedule.start();
//    }
}
