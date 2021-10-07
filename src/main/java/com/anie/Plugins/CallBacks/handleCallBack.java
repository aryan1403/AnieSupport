package com.anie.Plugins.CallBacks;

import java.util.ArrayList;
import com.anie.Anie;
import com.anie.Master;
import org.telegram.telegrambots.meta.api.methods.groupadministration.GetChatAdministrators;
import org.telegram.telegrambots.meta.api.methods.groupadministration.UnbanChatMember;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.chatmember.ChatMember;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class handleCallBack extends Anie implements Master {

    @Override
    public void handleRequest(Update update, String cmd) {
        if (update.getCallbackQuery().getData().startsWith("ban")) {
            long uid = Integer.parseInt(update.getCallbackQuery().getData().substring(3));
            UnbanChatMember unbanChatMember = new UnbanChatMember(
                    update.getCallbackQuery().getMessage().getChatId().toString(), uid);
            try {
                if (isAdmin(update)) {
                    execute(unbanChatMember);
                    SendMessage msg = new SendMessage(update.getCallbackQuery().getMessage().getChatId().toString(),
                            "Successfully Unbanned!\nI wish he would not do the same Again ;)");
                    execute(msg);
                }
            } catch (TelegramApiException e) {
                sendMessage(update, e.getMessage());
            }
        }

    }

    public boolean isAdmin(Update update) {
        GetChatAdministrators administrators = new GetChatAdministrators(
                update.getCallbackQuery().getMessage().getChatId().toString());
        ArrayList<ChatMember> members;
        try {
            members = execute(administrators);
            for (ChatMember chm : members) {
                long id = Integer.parseInt(chm.toString().substring(chm.toString().indexOf("id")+3, chm.toString().indexOf(",", chm.toString().indexOf("id"))));
                if(update.getCallbackQuery().getFrom().getId() == id) {
                    return true;
                }
            }
        } catch (TelegramApiException e) {
            sendMessage(update, e.getMessage());
        }
        return false;

    }

}
