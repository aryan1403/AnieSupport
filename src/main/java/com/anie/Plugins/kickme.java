package com.anie.Plugins;

import com.anie.Anie;
import com.anie.Master;
import org.telegram.telegrambots.meta.api.methods.groupadministration.KickChatMember;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class kickme extends Anie implements Master {
    @Override
    public void handleRequest(Update update, String cmd) {
        if(cmd.equalsIgnoreCase(handler()+"kickme")) {
            long uid = update.getMessage().getFrom().getId();

            KickChatMember kickChatMember = new KickChatMember(chatId(update), uid);
            try {
                execute(kickChatMember);
                sendMessage(update, "Successfully Kicked " + update.getMessage().getFrom().getUserName());
            } catch (TelegramApiException e) {
                sendMessage(update, e.getMessage());
            }
        }
    }
}
