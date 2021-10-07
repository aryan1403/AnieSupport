package com.anie.Plugins;

import java.util.ArrayList;
import java.util.List;

import com.anie.Anie;
import com.anie.Master;
import org.telegram.telegrambots.meta.api.methods.groupadministration.BanChatMember;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class ban extends Anie implements Master{

    @Override
    public void handleRequest(Update update, String cmd) {
        if(cmd.equalsIgnoreCase(handler()+"ban") && update.getMessage().isReply()) {
            long uid = update.getMessage().getReplyToMessage().getFrom().getId();

            BanChatMember member = new BanChatMember(chatId(update), uid);

            List<List<InlineKeyboardButton>> bList = new ArrayList<List<InlineKeyboardButton>>();
            InlineKeyboardButton button = new InlineKeyboardButton("🚫 Unban");
            button.setCallbackData("ban"+uid);
            List<InlineKeyboardButton> buttons = new ArrayList<>();
            buttons.add(button);
            bList.add(buttons);
            InlineKeyboardMarkup markup = new InlineKeyboardMarkup(bList);

            try {
                execute(member);
                SendMessage message = new SendMessage(chatId(update), "Successfully Banned "+ update.getMessage().getReplyToMessage().getFrom().getUserName());
                message.setReplyMarkup(markup);
                execute(message);
            } catch (TelegramApiException e) {
                sendMessage(update, e.getMessage());
            }
        }
    }
    
}
