package by.kanarski.booking.utils;

import by.kanarski.booking.constants.SystemLocale;
import by.kanarski.booking.exceptions.LocalisationException;
import by.kanarski.booking.managers.ExceptionMessageManager;
import by.kanarski.booking.utils.threadLocal.UserPreferences;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;
import java.util.NavigableSet;
import java.util.TreeMap;

public class DateUtil {

    private static final Locale DEFAULT_LOCALE = SystemLocale.DEFAULT;
    private Locale currentLocale = UserPreferences.getLocale();

    private DateUtil() {
    }

    public static String getFormattedDate(long date, Locale locale) {
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM, locale);
        return dateFormat.format(new Date(date));
    }

    public static String getFormattedDate(long date) {
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM, UserPreferences.getLocale());
        return dateFormat.format(new Date(date));
    }

    public static long parseDate(String formattedDate, Locale locale) throws LocalisationException {
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM, locale);
        long date = 0;
        try {
            date = dateFormat.parse(formattedDate).getTime();
        } catch (ParseException e) {
            throw new LocalisationException(ExceptionMessageManager.PARSE_DATE_EXCEPTION.get(), e);
        }
        return date;
    }

    public static long parseDate(String formattedDate) throws LocalisationException{
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM, DEFAULT_LOCALE);
        long date = 0;
        try {
            date = dateFormat.parse(formattedDate).getTime();
        } catch (ParseException e1) {
            try {
                dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM, UserPreferences.getLocale());
                date = dateFormat.parse(formattedDate).getTime();
            } catch (ParseException e2) {
                throw new LocalisationException(ExceptionMessageManager.PARSE_DATE_EXCEPTION.get(), e2);
            }
        }
        return date;
    }

    public static TreeMap<String, String> localizeBookedDates(TreeMap<Long, Long> bookedDates, Locale locale)
            throws LocalisationException {
        TreeMap<String, String> localizedBookedDates = new TreeMap<>();
        if (bookedDates != null) {
            NavigableSet<Long> bookingStartDates = bookedDates.navigableKeySet();
            for (Long bookingStartDate : bookingStartDates) {
                Long bookingEndDate = bookedDates.get(bookingStartDate);
                String localizedBookingStartDate = DateUtil.getFormattedDate(bookingStartDate, locale);
                String localizedBookingEndDate = DateUtil.getFormattedDate(bookingEndDate, locale);
                localizedBookedDates.put(localizedBookingStartDate, localizedBookingEndDate);
            }
        }
        return localizedBookedDates;
    }

    public static TreeMap<Long, Long> delocalizeBookedDates(TreeMap<String, String> bookedDates, Locale locale)
            throws LocalisationException {
        TreeMap<Long, Long> delocalizedBookedDates = new TreeMap<>();
        if (bookedDates != null) {
            NavigableSet<String> bookingStartDates = bookedDates.navigableKeySet();
            for (String bookingStartDate : bookingStartDates) {
                String bookingEndDate = bookedDates.get(bookingStartDate);
                Long delocalizedBookingStartDate = DateUtil.parseDate(bookingStartDate, locale);
                Long delocalizedBookingEndDate = DateUtil.parseDate(bookingEndDate, locale);
                delocalizedBookedDates.put(delocalizedBookingStartDate, delocalizedBookingEndDate);
            }
        }
        return delocalizedBookedDates;
    }

    /**
     * Returns date, formated according to the default locale
     * @param formattedDate date, formatted accroding to the user's locale
     * @return formatted by default locale
     */

    public static String getDefaultLocaleDate(String formattedDate) {
        Locale userLocale = UserPreferences.getLocale();
        long date = 0;
        try {
            date = DateUtil.parseDate(formattedDate, userLocale);
        } catch (LocalisationException e) {
            SystemLogger.getInstance().logError(DateUtil.class,
                    ExceptionMessageManager.PARSE_DATE_EXCEPTION.get(), e);
        }
        return DateUtil.getFormattedDate(date, DEFAULT_LOCALE);

    }

}
