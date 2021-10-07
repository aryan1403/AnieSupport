package com.anie.Plugins.CallBacks;

import com.anie.Anie;
import com.anie.Master;

import org.telegram.telegrambots.meta.api.methods.groupadministration.UnbanChatMember;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class handleCallBack extends Anie implements Master {

    @Override
    public void handleRequest(Update update, String cmd) {
        if(update.getCallbackQuery().getData().startsWith("ban")) {
            long uid = Integer.parseInt(update.getCallbackQuery().getData().substring(4));
            System.out.println(uid);
            UnbanChatMember unbanChatMember = new UnbanChatMember(chatId(update), uid);

            try {
                execute(unbanChatMember);
                sendMessage(update, "Successfully Unbanned!\nI wish he would not do the same Again ;)");
            } catch (TelegramApiException e) {
                sendMessage(update, e.getMessage());
            }
        }
        
    }
    
}
