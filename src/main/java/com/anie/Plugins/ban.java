package com.anie.Plugins;

import java.util.ArrayList;
import java.util.List;
import com.anie.Anie;
import com.anie.Master;
import org.telegram.telegrambots.meta.api.methods.groupadministration.BanChatMember;
import org.telegram.telegrambots.meta.api.methods.groupadministration.GetChatAdministrators;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.chatmember.ChatMember;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class ban extends Anie implements Master {

    @Override
    public void handleRequest(Update update, String cmd) {
        if (cmd.equalsIgnoreCase(handler() + "ban") && update.getMessage().isReply()) {
            long uid = update.getMessage().getReplyToMessage().getFrom().getId();

            BanChatMember member = new BanChatMember(chatId(update), uid);

            List<List<InlineKeyboardButton>> bList = new ArrayList<List<InlineKeyboardButton>>();
            InlineKeyboardButton button = new InlineKeyboardButton("🚫 Unban");
            button.setCallbackData("ban" + uid);
            List<InlineKeyboardButton> buttons = new ArrayList<>();
            buttons.add(button);
            bList.add(buttons);
            InlineKeyboardMarkup markup = new InlineKeyboardMarkup(bList);

            try {
                if (isAdmin(update)) {
                    execute(member);
                    SendMessage message = new SendMessage(chatId(update),
                            "Successfully Banned " + update.getMessage().getReplyToMessage().getFrom().getUserName());
                    message.setReplyMarkup(markup);
                    execute(message);
                } else {
                    sendMessage(update, "You should be an Admin to Execute This Command!");
                }
            } catch (TelegramApiException e) {
                sendMessage(update, e.getMessage());
            }
        }
    }

    public boolean isAdmin(Update update) {
        GetChatAdministrators administrators = new GetChatAdministrators(
                update.getMessage().getChatId().toString());
        ArrayList<ChatMember> members;
        try {
            members = execute(administrators);
            for (ChatMember chm : members) {
                long id = Integer.parseInt(chm.toString().substring(chm.toString().indexOf("id") + 3,
                        chm.toString().indexOf(",", chm.toString().indexOf("id"))));
                if (update.getMessage().getFrom().getId() == id) {
                    return true;
                }
            }
        } catch (TelegramApiException e) {
            sendMessage(update, e.getMessage());
        }
        return false;

    }

}
