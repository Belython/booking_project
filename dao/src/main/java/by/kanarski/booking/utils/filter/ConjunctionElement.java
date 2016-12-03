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
public class ConjunctionElement extends SearchFilter implements IFilter {

    private boolean asc = true;

    public void setAsc() {
        this.asc = true;
    }

    public void setDesc() {
        this.asc = false;
    }

}

