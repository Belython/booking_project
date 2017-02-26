package by.kanarski.booking.entities.abstr;

import by.kanarski.booking.entities.Language;

import javax.persistence.*;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

@MappedSuperclass
public abstract class Translation {

    private Language language;

    @MapsId("LANGUAGE_ID")
    @ManyToOne
    @JoinColumn(
            name = "LANGUAGE_ID"
    )
    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

}
