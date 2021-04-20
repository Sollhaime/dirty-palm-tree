package com.beck.mcb.telegrambot_v3.enums.command;

public enum Command {
    START,
    NEXT,
    SECOND_STAGE,
    GREETING,
    MENU;

    @Override
    public String toString() {
        return "/"+this.name();
    }
}
