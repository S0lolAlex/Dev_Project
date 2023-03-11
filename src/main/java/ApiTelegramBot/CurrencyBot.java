package ApiTelegramBot;

import Utils.BotAnswer;
import org.buttons.BotCommands;
import org.buttons.Buttons;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class CurrencyBot extends TelegramLongPollingBot implements BotCommands {
    private static final BotAnswer answer = new BotAnswer();

    @Override
    public String getBotUsername() {
        return "bot";
    }

    @Override
    public String getBotToken() {
        return "token";
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
        String userName;
        long CHAT_ID = 0;

        if (update.hasMessage()) {
            CHAT_ID = update.getMessage().getChatId();
            SendMessage message = new SendMessage();
            message.setChatId(CHAT_ID);
            userName = update.getMessage().getFrom().getFirstName();

            if (update.getMessage().hasText()) {
                reseive = update.getMessage().getText();
                answer.botAnswerUtils(reseive,message,userName);
                try{
                    execute(message);
                }catch (TelegramApiException e){
                    e.printStackTrace();
                }
            }
        } else if (update.hasCallbackQuery()) {
            CHAT_ID = update.getCallbackQuery().getMessage().getChatId();
            SendMessage message = new SendMessage();
            message.setChatId(CHAT_ID);
            userName = update.getCallbackQuery().getFrom().getFirstName();
            reseive = update.getCallbackQuery().getData();
            answer.botAnswerUtils(reseive,message,userName);
            try{
                execute(message);
            }catch (TelegramApiException e){
                e.printStackTrace();
            }
        }
    }
}
