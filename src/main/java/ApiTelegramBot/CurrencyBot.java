package ApiTelegramBot;

import Utils.BotAnswer;
import org.buttons.BotCommands;
import org.buttons.Buttons;
import org.functionalInteface.MessageSender;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

//Main class of TelegramBot
public class CurrencyBot extends TelegramLongPollingBot implements BotCommands, MessageSender {
    private static final BotAnswer answer = new BotAnswer();

    @Override
    public String getBotUsername() {
        return "Bot";
    }

    @Override
    public String getBotToken() {
        return "token";
    }
//Bot constructor
    public CurrencyBot() {
        Buttons.initKeyboard();
        try {
            this.execute(new SetMyCommands(LIST_OF_COMMANDS, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
//Main method that get message from chat
    @Override
    public void onUpdateReceived(Update update) {
        String reseive;
        long CHAT_ID = 0;

        if (update.hasMessage()) {
            CHAT_ID = update.getMessage().getChatId();
            SendMessage message = new SendMessage();
            message.setChatId(CHAT_ID);

            if (update.getMessage().hasText()) {
                reseive = update.getMessage().getText();
                answer.botAnswerUtils(reseive,message, this);
                sendMessage(message);
            }
        } else if (update.hasCallbackQuery()) {
            CHAT_ID = update.getCallbackQuery().getMessage().getChatId();
            SendMessage message = new SendMessage();
            message.setChatId(CHAT_ID);
            reseive = update.getCallbackQuery().getData();
            answer.botAnswerUtils(reseive,message, this);
            sendMessage(message);
        }
    }
//Send message in chat
    public void sendMessage(SendMessage message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
