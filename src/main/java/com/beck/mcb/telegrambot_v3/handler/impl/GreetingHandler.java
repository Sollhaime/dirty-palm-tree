package com.beck.mcb.telegrambot_v3.handler.impl;

import com.beck.mcb.telegrambot_v3.annotation.WithCommand;
import com.beck.mcb.telegrambot_v3.annotation.WithState;
import com.beck.mcb.telegrambot_v3.enums.command.Command;
import com.beck.mcb.telegrambot_v3.enums.state.State;
import com.beck.mcb.telegrambot_v3.handler.BaseStateHandler;
import com.beck.mcb.telegrambot_v3.message.MessageBuilder;
import com.beck.mcb.telegrambot_v3.service.CacheService;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import static com.beck.mcb.telegrambot_v3.enums.command.Command.MENU;
import static com.beck.mcb.telegrambot_v3.enums.command.Command.NEXT;
import static com.beck.mcb.telegrambot_v3.enums.state.State.GREETING;
@Slf4j
@WithState(states = GREETING)
public class GreetingHandler implements BaseStateHandler {
    @WithCommand(commands = NEXT)
    public SendMessage next(String chatId){
        MessageBuilder builder = MessageBuilder.builder(chatId)
                .textLine("Hello, i am ExpertBot")
                .textLine("Here are all available commands")
                .row()
                .button("next", NEXT)
                .button("menu", MENU);

        return builder.build();
    }


}
