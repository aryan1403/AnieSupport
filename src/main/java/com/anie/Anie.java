package com.anie;

import com.anie.Helpers.cfg;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

public class Anie extends TelegramLongPollingBot {

    @Override
    public void onUpdateReceived(Update update) {

    }

    @Override
    public String getBotUsername() {
        return cfg.botUserName;
    }

    @Override
    public String getBotToken() {
        return cfg.botToken;
    }

}
