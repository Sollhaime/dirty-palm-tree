package com.beck.mcb.telegrambot_v3.repo;

import com.beck.mcb.telegrambot_v3.domain.Chat;
import com.beck.mcb.telegrambot_v3.enums.state.State;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatStateRepo extends MongoRepository<Chat, String> {
    Chat findByChatId(String chatId);
}
