package com.anie.Plugins.CallBacks;

import com.anie.Anie;
import com.anie.Master;
import org.telegram.telegrambots.meta.api.methods.groupadministration.UnbanChatMember;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class handleCallBack extends Anie implements Master {

    @Override
    public void handleRequest(Update update, String cmd) {
        if (update.getCallbackQuery().getData().startsWith("ban")) {
            long uid = Integer.parseInt(update.getCallbackQuery().getData().substring(3));
            UnbanChatMember unbanChatMember = new UnbanChatMember(
                    update.getCallbackQuery().getMessage().getChatId().toString(), uid);
            try {
                execute(unbanChatMember);
                SendMessage msg = new SendMessage(update.getCallbackQuery().getMessage().getChatId().toString(),
                        "Successfully Unbanned!\nI wish he would not do the same Again ;)");
                execute(msg);
            } catch (TelegramApiException e) {
                sendMessage(update, e.getMessage());
            }
        }

    }
    /*
     * public boolean isAdmin(Update update) { GetChatAdministrators administrators
     * = new GetChatAdministrators(
     * update.getCallbackQuery().getMessage().getChatId().toString());
     * ArrayList<ChatMember> members;
     * 
     * ChatMember member = new
     * ChatMemberMember(update.getCallbackQuery().getFrom());
     * System.out.println(member); if (member.getStatus().equals("administrator") ||
     * member.getStatus().equals("creator")) { return true; } try { members =
     * execute(administrators); for (ChatMember chm : members) { if(chm.equals()) if
     * (member.getStatus().equals("administrator") ||
     * member.getStatus().equals("creator")) { return true; } } } catch
     * (TelegramApiException e) { sendMessage(update, e.getMessage()); } return
     * false;
     * 
     * }
     */

}
