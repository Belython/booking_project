package by.kanarski.booking.utils.wrappers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilterElement {

    private String property;
    private CriteriaConstraint constraint;
    private Object value;
    private boolean asc = true;

    public FilterElement(String property, CriteriaConstraint constraint, Object value) {
        this.property = property;
        this.constraint = constraint;
        this.value = value;
        setAsc();
    }

    public void setAsc() {
        this.asc = true;
    }

    public void setDesc() {
        this.asc = false;
    }

}

