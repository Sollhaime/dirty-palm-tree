package com.beck.mcb.telegrambot_v3.provider;

import com.beck.mcb.telegrambot_v3.annotation.WithCommand;
import com.beck.mcb.telegrambot_v3.annotation.WithState;
import com.beck.mcb.telegrambot_v3.domain.Chat;
import com.beck.mcb.telegrambot_v3.enums.command.Command;
import com.beck.mcb.telegrambot_v3.enums.state.State;
import com.beck.mcb.telegrambot_v3.handler.BaseStateHandler;
import com.beck.mcb.telegrambot_v3.service.CacheService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.User;

import java.lang.reflect.Method;
import java.util.stream.Stream;

@Slf4j
@Component
@RequiredArgsConstructor
public class MethodExecutor {
    public SendMessage execute( Chat target , BaseStateHandler handler, String textOrCommand ) {
        Method toExecute = Stream.of(handler.getClass().getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(WithCommand.class))
                .filter(method -> Stream.of(method.getAnnotation(WithCommand.class).commands()).anyMatch(command -> command.toString().equalsIgnoreCase(textOrCommand)))
                .findAny().orElseThrow(UnsupportedOperationException::new);
        return (SendMessage) ReflectionUtils.invokeMethod(toExecute , handler , target.getChatId());
    }

}
