package by.kanarski.booking.utils.wrappers;

import by.kanarski.booking.constants.SearchParameter;

import java.util.ArrayList;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

public class SearchFilter extends ArrayList<FilterElement> {

    public void setFilter(String property, CriteriaConstraint filterRule, Object value) {
        FilterElement filterElement = new FilterElement(property, filterRule, value);
        this.add(filterElement);
    }

    public void setEqFilter(String property, Object value) {
        FilterElement filterElement = new FilterElement(property, CriteriaConstraint.EQ, value);
        this.add(filterElement);
    }

    public void setGtFilter(String property, Object value) {
        FilterElement filterElement = new FilterElement(property, CriteriaConstraint.GT, value);
        this.add(filterElement);
    }

    public void setLtFilter(String property, Object value) {
        FilterElement filterElement = new FilterElement(property, CriteriaConstraint.LT, value);
        this.add(filterElement);
    }

    public void setGeFilter(String property, Object value) {
        FilterElement filterElement = new FilterElement(property, CriteriaConstraint.GE, value);
        this.add(filterElement);
    }

    public void setLeFilter(String property, Object value) {
        FilterElement filterElement = new FilterElement(property, CriteriaConstraint.LE, value);
        this.add(filterElement);
    }

    public void setLikeFilter(String property, Object value) {
        FilterElement filterElement = new FilterElement(property, CriteriaConstraint.LIKE, value);
        this.add(filterElement);
    }

    public void setILikeFilter(String property, Object value) {
        FilterElement filterElement = new FilterElement(property, CriteriaConstraint.ILIKE, value);
        this.add(filterElement);
    }

    public static SearchFilter createBasicEqFilter(String property, Object value) {
        FilterElement filterElement = new FilterElement(property, CriteriaConstraint.EQ, value);
        SearchFilter searchFilter = new SearchFilter();
        searchFilter.add(filterElement);
        return searchFilter;
    }

    public static SearchFilter createLanguageFilter(Object language) {
        FilterElement filterElement = new FilterElement(SearchParameter.LANGUAGE, CriteriaConstraint.EQ, language);
        SearchFilter searchFilter = new SearchFilter();
        searchFilter.add(filterElement);
        return searchFilter;
    }

}
