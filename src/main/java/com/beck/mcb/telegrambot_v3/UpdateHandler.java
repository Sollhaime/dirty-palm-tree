package com.beck.mcb.telegrambot_v3;

import com.beck.mcb.telegrambot_v3.domain.Chat;
import com.beck.mcb.telegrambot_v3.enums.command.Command;
import com.beck.mcb.telegrambot_v3.enums.state.State;
import com.beck.mcb.telegrambot_v3.handler.BaseStateHandler;
import com.beck.mcb.telegrambot_v3.provider.HandlerProvider;
import com.beck.mcb.telegrambot_v3.provider.MethodExecutor;
import com.beck.mcb.telegrambot_v3.service.CacheService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

@Slf4j
@Component
@RequiredArgsConstructor
public class UpdateHandler {
    private final HandlerProvider provider;
    private final CacheService cacheService;
    private final MethodExecutor executor;

    public SendMessage handleUpdate( Update update ) {
        User from;
        String textOrCommand;
        if(isWithText(update)) {
            from = update.getMessage().getFrom();
            textOrCommand = extractText(update); // /SECOND_STAGE
        } else {
            var callbackQuery = update.getCallbackQuery();
            from = callbackQuery.getFrom();
            textOrCommand = callbackQuery.getData();
        }
        if(textOrCommand != null && from != null) {
            Chat chat = cacheService.getById(String.valueOf(from.getId()));
            SendMessage message = execute(chat , textOrCommand);
            cacheService.saveOrUpdate(chat); // /SECOND_STAGE
            return message;
        } else {
            throw new IllegalArgumentException(update.toString());
        }
    }

    private String extractText( Update update ) {
        return update.getMessage().getText();
    }

    private boolean isWithText( Update update ) {
        return update.hasMessage() && update.getMessage().hasText();
    }

    private SendMessage execute( Chat chat , String textOrCommand ) {
        BaseStateHandler handler = provider.getHandlerFor(chat.getState());
        return executor.execute(chat , handler , textOrCommand);
    }
}
