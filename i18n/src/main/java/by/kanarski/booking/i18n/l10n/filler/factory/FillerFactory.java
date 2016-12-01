package by.kanarski.booking.i18n.l10n.filler.factory;


import by.kanarski.booking.i18n.l10n.filler.Filler;
import by.kanarski.booking.utils.BookingSystemLogger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FillerFactory {

    private static FillerFactory instance;
    private static BookingSystemLogger logger = BookingSystemLogger.getInstance().setSender(FillerFactory.class);

    private FillerFactory() {
    }

    public static synchronized FillerFactory getInstance() {
        if (instance == null) {
            instance = new FillerFactory();
        }
        return instance;
    }

    public Filler defineFiller(String pagePath) {
        String pageNameRegExp = "/\\w+\\.";
        Pattern pattern = Pattern.compile(pageNameRegExp);
        Matcher matcher = pattern.matcher(pagePath);
        matcher.find();
        String pageName = matcher.group();
        pageName = pageName.substring(1, pageName.length() - 1);
        FillerManager fillerManager = null;
        try {
            fillerManager = FillerManager.valueOf(pageName.toUpperCase());
        } catch (IllegalArgumentException e) {
            fillerManager = FillerManager.INDEX;
            logger.logError(e.getMessage(), e);
        }
        Filler current = fillerManager.getFiller();
        return current;
    }
}
