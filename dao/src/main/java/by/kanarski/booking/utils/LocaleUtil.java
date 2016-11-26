package by.kanarski.booking.utils;

import java.util.Locale;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

public class LocaleUtil {



    public static String getDisplayLanguage(String languageTag) {
        return new Locale(languageTag).getDisplayLanguage();
    }

    public static String getLanguageTag(String displayName) {
        return null;
    }    
    
}
