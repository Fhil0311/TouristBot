package com.savitsky;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

@Configuration
public class AppContext {
    @Bean
    public TouristBot touristBot() {
        return new TouristBot();
    }

    @Bean
    public TelegramBotsApi telegramBotsApi() {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(touristBot());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
        return telegramBotsApi;
    }

    @Bean
    public SendMessage sendMessage() {
        return new SendMessage();
    }

    @Bean
    public EditMessageText editMessageText() {
        return new EditMessageText();
    }

    @Bean
    public KeyboardRow keyboardRow() {
        return new KeyboardRow();
    }

    @Bean
    public ReplyKeyboardMarkup replyKeyboardMarkup() {
        return new ReplyKeyboardMarkup();
    }
}
