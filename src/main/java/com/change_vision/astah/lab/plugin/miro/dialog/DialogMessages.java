package com.change_vision.astah.lab.plugin.miro.dialog;

import com.change_vision.jude.api.inf.ui.IMessageProvider;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class DialogMessages implements IMessageProvider {
    public static final String DEFAULT_BUNDLE = "messages";

    private static ResourceBundle INTERNAL_MESSAGES = ResourceBundle.getBundle(
            DEFAULT_BUNDLE, Locale.getDefault(),
            DialogMessages.class.getClassLoader());

    public static String getMessage(String key, Object... parameters) {
        String entry = INTERNAL_MESSAGES.getString(key);
        return MessageFormat.format(entry, parameters);
    }

    @Override
    public String provideMessage(String key, Object... parameters) {
        return getMessage(key, parameters);
    }
}
