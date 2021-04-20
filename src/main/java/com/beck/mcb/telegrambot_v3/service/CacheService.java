package com.beck.mcb.telegrambot_v3.service;

import com.beck.mcb.telegrambot_v3.domain.Chat;
import com.beck.mcb.telegrambot_v3.enums.state.State;
import com.beck.mcb.telegrambot_v3.repo.ChatStateRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.WeakHashMap;

import static com.beck.mcb.telegrambot_v3.enums.state.State.GREETING;

@Service
@RequiredArgsConstructor
public class CacheService {
    private final ChatStateRepo repo;
    private final WeakHashMap<String, State> cache = new WeakHashMap<>();


    public Chat getById( String chatId ) {
        State state = cache.get(chatId);
        Chat.ChatBuilder builder = Chat.builder().chatId(chatId);
        if(state == null) {
            Chat chat = repo.findByChatId(chatId);
            if(chat == null) {
                return builder.state(GREETING).build();
            }
            return chat;
        }
        return builder.state(state).build();
    }

    public void saveOrUpdate( Chat chat ) {
        if(getById(chat.getChatId()) != null) {
            chat.setState(chat.getState().next());
        }
        repo.save(chat);
        cache.put(chat.getChatId() , chat.getState());
    }
}
