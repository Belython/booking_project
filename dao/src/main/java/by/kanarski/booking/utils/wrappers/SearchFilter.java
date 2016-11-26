package by.kanarski.booking.utils.wrappers;

import java.util.ArrayList;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

public class SearchFilter extends ArrayList<FilterElement> {

    public void setFilter(String property, CriteriaRule filterRule, Object value) {
        FilterElement filterElement = new FilterElement(property, filterRule, value);
        this.add(filterElement);
    }

    public void setEqFilter(String property, Object value) {
        FilterElement filterElement = new FilterElement(property, CriteriaRule.EQ, value);
        this.add(filterElement);
    }

    public void setGtFilter(String property, Object value) {
        FilterElement filterElement = new FilterElement(property, CriteriaRule.GT, value);
        this.add(filterElement);
    }

    public void setLtFilter(String property, Object value) {
        FilterElement filterElement = new FilterElement(property, CriteriaRule.LT, value);
        this.add(filterElement);
    }

    public void setGeFilter(String property, Object value) {
        FilterElement filterElement = new FilterElement(property, CriteriaRule.GE, value);
        this.add(filterElement);
    }

    public void setLeFilter(String property, Object value) {
        FilterElement filterElement = new FilterElement(property, CriteriaRule.LE, value);
        this.add(filterElement);
    }

    public static SearchFilter createBasicEqFilter(String property, Object value) {
        FilterElement filterElement = new FilterElement(property, CriteriaRule.EQ, value);
        SearchFilter searchFilter = new SearchFilter();
        searchFilter.add(filterElement);
        return searchFilter;
    }

}
