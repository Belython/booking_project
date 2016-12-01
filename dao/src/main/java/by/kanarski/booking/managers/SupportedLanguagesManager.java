package by.kanarski.booking.managers;

import by.kanarski.booking.utils.wrappers.SupportedLanguages;

import java.util.Enumeration;
import java.util.ResourceBundle;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

public class SupportedLanguagesManager {

    private static SupportedLanguages supportedLanguages;

    public static SupportedLanguages get() {
        if (supportedLanguages == null) {
            supportedLanguages = new SupportedLanguages();
            ResourceBundle bundle = ResourceManager.SUPPORTED_LANGUAGES.get();
            Enumeration<String> languageOrder = bundle.getKeys();
            while (languageOrder.hasMoreElements()) {
                String element = languageOrder.nextElement();
                String language = bundle.getString(element);
                supportedLanguages.add(language);
            }
        }
        return supportedLanguages;
    }

    public static Long getLanguageId(String language) {
        return (Integer.toUnsignedLong(get().indexOf(language.toUpperCase())) + 1);
    }

}
