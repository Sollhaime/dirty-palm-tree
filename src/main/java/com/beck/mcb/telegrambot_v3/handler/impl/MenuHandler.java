package com.beck.mcb.telegrambot_v3.handler.impl;

import com.beck.mcb.telegrambot_v3.annotation.WithCommand;
import com.beck.mcb.telegrambot_v3.annotation.WithState;
import com.beck.mcb.telegrambot_v3.enums.command.Command;
import com.beck.mcb.telegrambot_v3.enums.state.State;
import com.beck.mcb.telegrambot_v3.handler.BaseStateHandler;
import com.beck.mcb.telegrambot_v3.message.MessageBuilder;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import static com.beck.mcb.telegrambot_v3.enums.command.Command.GREETING;

@WithState(states = State.MENU)
public class MenuHandler implements BaseStateHandler {

    @WithCommand(commands = Command.MENU)
    public SendMessage next( String chatId){
        MessageBuilder builder = MessageBuilder.builder(chatId)
                .textLine("Hello, i am ExpertBot")
                .textLine("Here are all available commands")
                .row()
                .button("greeting", GREETING);

        return builder.build();
    }
}
