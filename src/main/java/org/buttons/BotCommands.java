package org.buttons;

import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

import java.util.List;

public interface BotCommands {
        List<BotCommand> LIST_OF_COMMANDS = List.of(
                new BotCommand("/start", "start bot"),
                new BotCommand("/help", "bot info")
        );

        String HELP_TEXT = "This bot need for GoIt project  " +
                "The following commands are available to you:\n\n" +
                "/start - start the bot\n" +
                "/help - help menu";
}
