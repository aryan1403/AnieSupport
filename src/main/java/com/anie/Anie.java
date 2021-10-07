package com.anie;

import com.anie.Helpers.cfg;
import com.anie.Plugins.ban;
import com.anie.Plugins.kickme;
import com.anie.Plugins.CallBacks.handleCallBack;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Anie extends TelegramLongPollingBot {

    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasCallbackQuery()) 
            new handleCallBack().handleRequest(update, "");
        if(update.hasMessage() && update.getMessage().getText().startsWith("/")) 
            sendRequest(update);
    }

    public void sendRequest(Update update) {
        String cmd = update.getMessage().getText();
        new ban().handleRequest(update, cmd);
        new kickme().handleRequest(update, cmd);
    }

    public String handler() {
        // return cfg.handler; Commented just for Faster Instance
        return "/";
    }

    public String chatId(Update update) {
        return update.getMessage().getChatId().toString();
    }

    public void sendMessage(Update update, String text) {
        SendMessage message = new SendMessage(chatId(update), text);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
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
