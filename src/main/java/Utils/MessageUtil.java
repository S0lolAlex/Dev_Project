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
    public void setTime(SendMessage message, String time){
        message.setText("������ ��� ���������. \n " +
                "�������� ��� �������� :" + time);
        message.setReplyMarkup(Buttons.initTimeKeyboard());
    }

    public void returnMenu(SendMessage message,String text) {
        message.setText( text);
        message.setReplyMarkup(Buttons.startMarkup());
    }

    public void startBot(SendMessage message) {
        message.setText("������� �������. ��� ��� �������� ������������� �������� ����� �����");
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
        message.setText("������������");
        message.setReplyMarkup(Buttons.settingMarkup());
    }

    public void chooseBank(SendMessage message) {
        message.setText("������ ����");
        message.setReplyMarkup(Buttons.banks());
    }

    public void countFloatPoint(SendMessage message) {
        message.setText("������ ������� ����� ���� ����");
        message.setReplyMarkup(Buttons.setFloatPoint());
    }

    public void chooseCurrency(SendMessage message) {
        message.setText("������ ������");
        message.setReplyMarkup(Buttons.chooseCurrency());
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
        if (BotAnswer.getSchedules().get(chatId)!= null) {
            BotAnswer.getSchedules().get(chatId).stop();
            BotAnswer.getSchedules().remove(chatId);
        }
    }
}
