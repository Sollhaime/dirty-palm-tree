package com.beck.mcb.telegrambot_v3.provider;

import com.beck.mcb.telegrambot_v3.annotation.WithState;
import com.beck.mcb.telegrambot_v3.enums.state.State;
import com.beck.mcb.telegrambot_v3.handler.BaseStateHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Stream;

@Slf4j
@Component
@RequiredArgsConstructor
public class HandlerProvider {
    private final List<BaseStateHandler> handlers;

    public BaseStateHandler getHandlerFor( State source ) {
        String text = source.toString();
        return handlers.stream()
                .filter(handler -> handler.getClass()
                        .isAnnotationPresent(WithState.class))
                .filter(handler -> Stream.of(handler.getClass()
                        .getAnnotation(WithState.class)
                        .states())
                        .anyMatch(state -> state.toString().equalsIgnoreCase(extractState(text))))
                .findAny().orElseThrow(UnsupportedOperationException::new);

    }

    private String extractState( String text ) {
        return text.split(" ")[0];
    }
}
