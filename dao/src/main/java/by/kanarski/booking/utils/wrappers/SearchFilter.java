package by.kanarski.booking.utils.wrappers;

import by.kanarski.booking.constants.AliasName;
import by.kanarski.booking.constants.AliasValue;
import by.kanarski.booking.constants.BookingSystemLocale;
import by.kanarski.booking.constants.SearchParameter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

public class SearchFilter extends ArrayList<FilterElement> {

    private Map<String, String> aliasMap = new HashMap<>();

    public static SearchFilter createBasicEqFilter(String property, Object value) {
        FilterElement filterElement = new FilterElement(property, CriteriaConstraint.EQ, value);
        SearchFilter searchFilter = new SearchFilter();
        searchFilter.add(filterElement);
        return searchFilter;
    }

    public static SearchFilter createBasicLikeFilter(String property, Object value) {
        FilterElement filterElement = new FilterElement(property, CriteriaConstraint.LIKE, value + "%");
        SearchFilter searchFilter = new SearchFilter();
        searchFilter.add(filterElement);
        return searchFilter;
    }

    public static SearchFilter createBasicIlikeFilter(String property, Object value) {
        FilterElement filterElement = new FilterElement(property, CriteriaConstraint.ILIKE, value + "%");
        SearchFilter searchFilter = new SearchFilter();
        searchFilter.add(filterElement);
        return searchFilter;
    }

    public static SearchFilter createLanguageFilter(String language) {
        SearchFilter searchFilter = SearchFilter.createBasicEqFilter(SearchParameter.LANGUAGE_NAME, language);
        searchFilter.addAlias(AliasName.LANGUAGE, AliasValue.LANGUAGE);
        return searchFilter;
    }

    public static SearchFilter createBasicLanguageFilter() {
        String language = BookingSystemLocale.DEFAULT.getLanguage().toUpperCase();
        SearchFilter searchFilter = SearchFilter.createBasicEqFilter(SearchParameter.LANGUAGE_NAME, language);
        searchFilter.addAlias(AliasName.LANGUAGE, AliasValue.LANGUAGE);
        return searchFilter;
    }

    public static SearchFilter createAliasFilter(String aliasName, String alias) {
        SearchFilter searchFilter = new SearchFilter();
        searchFilter.addAlias(aliasName, alias);
        return searchFilter;
    }

    public void addAlias(String aliasName, String alias) {
        aliasMap.put(aliasName, alias);
    }

    public Set<String> getAliasNames() {
        return aliasMap.keySet();
    }

    public String getAlias(String aliasName) {
        return aliasMap.get(aliasName);
    }

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
        FilterElement filterElement = new FilterElement(property, CriteriaConstraint.LIKE, value + "%");
        this.add(filterElement);
    }

    public void setILikeFilter(String property, Object value) {
        FilterElement filterElement = new FilterElement(property, CriteriaConstraint.ILIKE, value + "%");
        this.add(filterElement);
    }


}
