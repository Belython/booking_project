package by.kanarski.booking.entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

@Entity
@GenericGenerator(
        name = "increment",
        strategy = "increment"
)
@DynamicUpdate
@DynamicInsert
@AllArgsConstructor
@NoArgsConstructor
public class Language implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long languageId;
    private String name;

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator = "increment"
    )
    public Long getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Long languageId) {
        this.languageId = languageId;
    }

    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Language language = (Language) o;

        if (!languageId.equals(language.languageId)) return false;
        return name.equals(language.name);

    }

    @Override
    public int hashCode() {
        int result = languageId.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }
}
