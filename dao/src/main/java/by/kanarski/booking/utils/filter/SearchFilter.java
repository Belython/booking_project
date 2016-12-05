package by.kanarski.booking.utils.filter;

import by.kanarski.booking.constants.AliasName;
import by.kanarski.booking.constants.AliasValue;
import by.kanarski.booking.constants.SystemLocale;
import by.kanarski.booking.constants.SearchParameter;

import java.util.*;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

public class SearchFilter {
    
    private List<ConjunctionElement> conjunctions = new ArrayList<>();
    private List<DisjunctionElement> disjunctions = new ArrayList<>();
    private List<FilterElement> filterList = new ArrayList<>();
    private Map<String, String> aliasMap = new HashMap<>();

    public List<ConjunctionElement> getConjunctions() {
        return this.conjunctions;
    }

    public static ConjunctionElement createConjunction() {
        return new ConjunctionElement();
    }

    public void addConjunction(ConjunctionElement conjunctionElement) {
        this.conjunctions.add(conjunctionElement);
    }
    
    public List<DisjunctionElement> getDisjunctions() {
        return this.disjunctions;
    }

    public static DisjunctionElement createDusjunction() {
        return new DisjunctionElement();
    }

    public void addDisjunction(DisjunctionElement disjunctionElement) {
        this.disjunctions.add(disjunctionElement);
    }

    public List<FilterElement> getFilterList() {
        return this.filterList;
    }

    public SearchFilter addFilter(FilterElement filterElement) {
        filterList.add(filterElement);
        return this;
    }

    public SearchFilter addFilter(String property, CriteriaConstraint filterConstraint, Object value) {
        FilterElement filterElement = new FilterElement(property, filterConstraint, value);
        filterList.add(filterElement);
        return this;
    }

    public SearchFilter addEqFilter(String property, Object value) {
        FilterElement filterElement = new FilterElement(property, CriteriaConstraint.EQ, value);
        filterList.add(filterElement);
        return this;
    }

    public SearchFilter addGtFilter(String property, Object value) {
        FilterElement filterElement = new FilterElement(property, CriteriaConstraint.GT, value);
        filterList.add(filterElement);
        return this;
    }

    public SearchFilter addLtFilter(String property, Object value) {
        FilterElement filterElement = new FilterElement(property, CriteriaConstraint.LT, value);
        filterList.add(filterElement);
        return this;
    }

    public SearchFilter addGeFilter(String property, Object value) {
        FilterElement filterElement = new FilterElement(property, CriteriaConstraint.GE, value);
        filterList.add(filterElement);
        return this;
    }

    public SearchFilter addLeFilter(String property, Object value) {
        FilterElement filterElement = new FilterElement(property, CriteriaConstraint.LE, value);
        filterList.add(filterElement);
        return this;
    }

    public SearchFilter addLikeFilter(String property, Object value) {
        FilterElement filterElement = new FilterElement(property, CriteriaConstraint.LIKE, value + "%");
        filterList.add(filterElement);
        return this;
    }

    public SearchFilter addILikeFilter(String property, Object value) {
        FilterElement filterElement = new FilterElement(property, CriteriaConstraint.ILIKE, value + "%");
        filterList.add(filterElement);
        return this;
    }

    public SearchFilter addBeetwenFilter(String property, Object value1, Object value2) {
        FilterElement filterElement = new FilterElement(property, CriteriaConstraint.BEETWEN, value1, value2);
        filterList.add(filterElement);
        return this;
    }

    public Set<String> getAliasNames() {
        return aliasMap.keySet();
    }

    public String getAlias(String aliasName) {
        return aliasMap.get(aliasName);
    }

    public void addAlias(String aliasName, String alias) {
        aliasMap.put(aliasName, alias);
    }

    public static SearchFilter createBasicEqFilter(String property, Object value) {
        FilterElement filterElement = new FilterElement(property, CriteriaConstraint.EQ, value);
        SearchFilter searchFilter = new SearchFilter();
        searchFilter.addFilter(filterElement);
        return searchFilter;
    }

    public static SearchFilter createBasicLikeFilter(String property, Object value) {
        FilterElement filterElement = new FilterElement(property, CriteriaConstraint.LIKE, value + "%");
        SearchFilter searchFilter = new SearchFilter();
        searchFilter.addFilter(filterElement);
        return searchFilter;
    }

    public static SearchFilter createBasicIlikeFilter(String property, Object value) {
        FilterElement filterElement = new FilterElement(property, CriteriaConstraint.ILIKE, value + "%");
        SearchFilter searchFilter = new SearchFilter();
        searchFilter.addFilter(filterElement);
        return searchFilter;
    }

    public static SearchFilter createLanguageFilter(String language) {
        SearchFilter searchFilter = SearchFilter.createBasicEqFilter(SearchParameter.LANGUAGE_NAME, language);
        searchFilter.addAlias(AliasName.LANGUAGE, AliasValue.LANGUAGE);
        return searchFilter;
    }

    public static SearchFilter createBasicLanguageFilter() {
        String language = SystemLocale.DEFAULT.getLanguage().toUpperCase();
        SearchFilter searchFilter = SearchFilter.createBasicEqFilter(SearchParameter.LANGUAGE_NAME, language);
        searchFilter.addAlias(AliasName.LANGUAGE, AliasValue.LANGUAGE);
        return searchFilter;
    }

    public static SearchFilter createAliasFilter(String aliasName, String alias) {
        SearchFilter searchFilter = new SearchFilter();
        searchFilter.addAlias(aliasName, alias);
        return searchFilter;
    }


}
