package com.beck.mcb.telegrambot_v3.enums.state;

import lombok.Getter;

import java.util.Arrays;

public enum State {
    GREETING(Group.GREETING),//1
    SECOND_STATE(Group.GREETING),//2
    MENU();

    @Getter
    private final Group group;

    State(){
        this.group = Group.ALL;
    }
    State(Group group){
        this.group = group;
    }

    private static State[] vals = values();

    public State next() {
        return Arrays.stream(vals)
                .filter(next -> next.getGroup().equals(this.getGroup()))
                .filter(next -> next.ordinal() == this.ordinal()+1)
                .findAny()
                .orElse(MENU);
    }
    public enum Group{
        GREETING,
        ALL;
    }
}
