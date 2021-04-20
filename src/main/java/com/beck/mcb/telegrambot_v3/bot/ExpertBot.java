package com.beck.mcb.telegrambot_v3.bot;

import com.beck.mcb.telegrambot_v3.UpdateHandler;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
@Component
@RequiredArgsConstructor
public class ExpertBot extends TelegramLongPollingBot {
    private final UpdateHandler updateHandler;

    @Getter
    @Value("${telegram.bot.token}")
    private String BotToken;

    @Getter
    @Value("${telegram.bot.name}")
    private String BotUsername;

    @Override
    public void onUpdateReceived( Update update ) {
        try {
            SendMessage message = updateHandler.handleUpdate(update);
            execute(message);
        } catch (Exception e) {
            log.error("Something went wrong on handling update '{}'", update.toString());
        }
    }
}
