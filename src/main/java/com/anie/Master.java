package com.anie;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface Master {
    public void handleRequest(Update update, String cmd);
}
