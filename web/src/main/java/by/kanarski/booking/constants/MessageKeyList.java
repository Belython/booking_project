package by.kanarski.booking.constants;

import java.util.Arrays;
import java.util.List;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

public class MessageKeyList {

    //Locale
    private final List<String> localeList = Arrays.asList(
            "locale.ru_RU",
            "locale.en_US"
    );

    //Currency
    private final List<String> currencyList = Arrays.asList(
            "currency.BYR",
            "currency.RUB",
            "currency.USD"
    );

    public List<String> getLocaleList() {
        return localeList;
    }


    public List<String> getCurrencyList() {
        return currencyList;
    }

}
