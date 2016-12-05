package by.kanarski.booking.utils.threadLocal;

import by.kanarski.booking.constants.SystemCurrency;
import by.kanarski.booking.constants.SystemLocale;

import java.util.Currency;
import java.util.Locale;

/**
 * Contains current user's preferces as thread local variables
 * @author Dzmitry Kanarski
 * @version 1.0
 * @see ThreadLocalUtil
 */

public class UserPreferences {

    private static final int PAGE_FOR_PAGINATION = 1;
    private static int startRow = 0;
    private static int rowsPerPage = 5;

    private UserPreferences() {

    }

    public static Locale getLocale() {
        Locale locale = (Locale) ThreadLocalUtil.LOCALE.get(SystemLocale.DEFAULT);
        return locale;
    }

    public static void setLocale(Locale locale) {
        ThreadLocalUtil.LOCALE.set(locale);
    }

    public static Currency getCurrency() {
        Currency currency = (Currency) ThreadLocalUtil.CURRENCY.get(SystemCurrency.DEFAULT);
        return currency;
    }

    public static void setCurrency(Currency currency) {
        ThreadLocalUtil.CURRENCY.set(currency);
    }

    public static int getStartRow() {
        return startRow;
    }

    public static void setStartRow(int startRow) {
        UserPreferences.startRow = startRow;
    }

    public static int getRowsPerPage() {
        return rowsPerPage;
    }

    public static void setRowsPerPage(int rowsPerPage) {
        UserPreferences.rowsPerPage = rowsPerPage;
    }
}
