package by.kanarski.booking.utils.filter;

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
public class FilterElement implements IFilter {

    private String property;
    private CriteriaConstraint constraint;
    private Object value1;
    private Object value2;
    private boolean asc = true;

    public FilterElement(String property, CriteriaConstraint constraint, Object value1) {
        this.property = property;
        this.constraint = constraint;
        this.value1 = value1;
        setAsc();
    }

    public FilterElement(String property, CriteriaConstraint constraint, Object value1, Object value2) {
        this.property = property;
        this.constraint = constraint;
        this.value1 = value1;
        this.value2 = value2;
        setAsc();
    }

    public void setAsc() {
        this.asc = true;
    }

    public void setDesc() {
        this.asc = false;
    }

}

