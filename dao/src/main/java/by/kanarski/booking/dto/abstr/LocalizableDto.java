package by.kanarski.booking.dto.abstr;

import by.kanarski.booking.utils.threadLocal.UserPreferences;
import lombok.Data;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

@Data
public abstract class LocalizableDto {

    private String language;

    public LocalizableDto() {
        this.language = UserPreferences.getLocale().getLanguage();
    }

}
