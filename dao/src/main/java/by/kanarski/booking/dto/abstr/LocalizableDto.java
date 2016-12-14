package by.kanarski.booking.dto.abstr;

import by.kanarski.booking.utils.threadLocal.UserPreferences;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

@Data
public abstract class LocalizableDto implements Serializable {

    private static final Long serialVersionUID = 1L;

    private String language;

    public LocalizableDto() {
        this.language = UserPreferences.getLocale().getLanguage();
    }
    public LocalizableDto(String govno) {
        this.language = UserPreferences.getLocale().getLanguage();
    }

}
