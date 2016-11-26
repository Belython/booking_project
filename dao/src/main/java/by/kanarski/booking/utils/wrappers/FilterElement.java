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
    private CriteriaRule rule;
    private Object value;

}

