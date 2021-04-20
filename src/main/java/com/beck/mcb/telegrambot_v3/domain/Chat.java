package com.beck.mcb.telegrambot_v3.domain;

import com.beck.mcb.telegrambot_v3.enums.state.State;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
@Data
@Builder
public class Chat {
    @Id
    private String chatId;
    private State state;
}
