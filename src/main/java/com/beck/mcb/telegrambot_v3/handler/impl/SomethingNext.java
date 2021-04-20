package com.beck.mcb.telegrambot_v3.handler.impl;

import com.beck.mcb.telegrambot_v3.annotation.WithCommand;
import com.beck.mcb.telegrambot_v3.annotation.WithState;
import com.beck.mcb.telegrambot_v3.handler.BaseStateHandler;
import com.beck.mcb.telegrambot_v3.message.MessageBuilder;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import static com.beck.mcb.telegrambot_v3.enums.command.Command.GREETING;
import static com.beck.mcb.telegrambot_v3.enums.command.Command.MENU;
import static com.beck.mcb.telegrambot_v3.enums.command.Command.NEXT;
import static com.beck.mcb.telegrambot_v3.enums.state.State.SECOND_STATE;

@WithState(states = SECOND_STATE)
public class SomethingNext implements BaseStateHandler {
    @WithCommand(commands = NEXT)
    public SendMessage next( String chatId){
        MessageBuilder builder = MessageBuilder.builder(chatId)
                .textLine("This is your next page")
                .textLine("Here are all available commands")
                .row()
                .button("next", NEXT)
                .button("menu", MENU);

        return builder.build();
    }

    @WithCommand(commands = MENU)
    public SendMessage menu( String chatId){
        MessageBuilder builder = MessageBuilder.builder(chatId)
                .textLine("This is your next page")
                .textLine("Here are all available commands")
                .row()
                .button("asdasdsadasd", GREETING)
                .button("menu", MENU);

        return builder.build();
    }

}
