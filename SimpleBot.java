
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import java.util.ArrayList;
import java.util.List;
public class SimpleBot extends TelegramLongPollingBot {

    public static void main(String[] args) throws TelegramApiException {

        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        try {

            telegramBotsApi.registerBot(new SimpleBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return "MySecond33Bot";
    }

    @Override
    public String getBotToken() {
        return "5635682179:AAEI2l_OhvqgUJrAZqodFyYlQghie9lpx5E";
    }

    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if (message != null && message.hasText()) {

            switch (message.getText()) {
                case "Інформація":

                    sendMsg(message, "Інформація");

                    //System.out.println(message.getText());
                    break;
                case "Налаштування":
                    sendMsg(message, "Налаштування");
                    System.out.println(message.getText());
                    
                    break;
              /*  case "Команда 2":
                    sendMsg(message, "Это команда 2");
                    System.out.println(message.getText());
                    break; */
                default:
                    sendMsg(message, "Щось треба натиснути");
                    System.out.println(message.getText());
                    break;
            }
        }
    }

    public void sendMsg (Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);

        // Создаем клавиатуру
        ReplyKeyboardMarkup replyKeyboardMarkup = new
                ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        // Создаем список строк клавиатуры
        List<KeyboardRow> keyboard = new ArrayList<>();

        // Первая строчка клавиатуры
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        // Добавляем кнопки в первую строчку клавиатуры
        keyboardFirstRow.add("Інформація");
        // keyboardFirstRow.add("Налаштування");

        // Вторая строчка клавиатуры
        KeyboardRow keyboardSecondRow = new KeyboardRow();
        // Добавляем кнопки во вторую строчку клавиатуры
        keyboardSecondRow.add("Налаштування");
      //  keyboardSecondRow.add("Команда 4");

        // Добавляем все строчки клавиатуры в список
        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);
        // и устанавливаем этот список нашей клавиатуре
        replyKeyboardMarkup.setKeyboard(keyboard);

        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}




