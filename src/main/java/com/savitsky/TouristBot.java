package com.savitsky;

import com.savitsky.entitys.City;
import com.savitsky.repositories.CitiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class TouristBot extends TelegramLongPollingBot {

    @Autowired
    private CitiesRepository citiesRepository;

    @Autowired
    private SendMessage sendMessage;

    @Autowired
    private ReplyKeyboardMarkup replyKeyboardMarkup;

    private static final String TOKEN = "1092309309:AAH92HwR25cF8lCjY-9pP-tBcU14K3wAsqA";
    private static final String BOT_USERNAME = "TouristTaskBot";

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();

        if (message != null && message.hasText()) {
            String messageText = message.getText();
            City cityByName = citiesRepository.findByCityName(messageText);

            if (cityByName != null) {
                sendMessageToUser(message, cityByName.getCityDescription());
            } else if (message.getText().equals("/start") || message.getText().equals("Available cities")) {
                sendAllCities(message);
            } else {
                sendMessageToUser(message, "Sorry, the bot does not support such a city");
            }
        }
    }

    public void sendAllCities(Message message) {
        String botHello = "The TouristBot the bot gives the user reference information about the entered city\n" +
                "_Available cities_:\n";
        for (City city : citiesRepository.findAll()) {
            botHello += ("*" + city.getCityName() + "*\n");
        }
        sendMessageToUser(message, botHello);
    }

    @Override
    public String getBotUsername() {
        return BOT_USERNAME;
    }

    @Override
    public String getBotToken() {
        return TOKEN;
    }

    public void sendMessageToUser(Message message, String text) {
        sendMessage.enableMarkdownV2(true);
        sendMessage.setChatId(message.getChatId());
        sendMessage.setText(text);
        try {
            setButtons(sendMessage);
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void setButtons(SendMessage sendMessage) {
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow keyboardFirstRow = new KeyboardRow();
        keyboardFirstRow.add(new KeyboardButton("Available cities"));

        keyboard.add(keyboardFirstRow);
        replyKeyboardMarkup.setKeyboard(keyboard);
    }

}
