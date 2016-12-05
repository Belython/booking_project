package by.kanarski.booking.taglib;

import by.kanarski.booking.constants.SystemLocale;
import by.kanarski.booking.exceptions.LocalisationException;
import by.kanarski.booking.managers.ExceptionMessageManager;
import by.kanarski.booking.utils.SystemLogger;
import by.kanarski.booking.utils.CurrencyUtil;
import by.kanarski.booking.utils.DateUtil;
import by.kanarski.booking.utils.threadLocal.UserPreferences;

import java.util.Currency;
import java.util.Locale;

/**
 * Taglib class for localisation on JSP
 * @author Dzmitry Kanarski
 * @version 1.0
 * @see by.kanarski.booking.utils.threadLocal.UserPreferences
 */

public class Localisator {

    /**
     * Formats the given date according to the current locale
     * @param defaultFormattedDate date, formatted according to the default locale (US_en)
     * @return formatted date
     */

    public static String getDate(String defaultFormattedDate) {
        Locale defaultLocale = SystemLocale.DEFAULT;
        Locale userLocale = UserPreferences.getLocale();
        long date = 0;
        try {
            date = DateUtil.parseDate(defaultFormattedDate, defaultLocale);
        } catch (LocalisationException e) {
            SystemLogger.getInstance().logError(Localisator.class, ExceptionMessageManager.PARSE_DATE_EXCEPTION.get(), e);
        }
        String formattedDate = DateUtil.getFormattedDate(date, userLocale);
        return formattedDate;

    }

    /**
     * Formats the given money value according to the current currency
     * @param defaultCurrencyMoney money, formatted according to the default currency (USD)
     * @return formatted currency
     */

    public static double getMoney(double defaultCurrencyMoney) {
        Currency userCurrency = UserPreferences.getCurrency();
        double formattedMoney = CurrencyUtil.convertFromUSD(defaultCurrencyMoney, userCurrency);
        return formattedMoney;
    }

}
