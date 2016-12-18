package by.kanarski.booking.utils.filter;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

public enum CriteriaConstraint {

    EQ("="), NE("<>"), GT(">"), LT("<"), GE("=>"), LE("<="),
    LIKE(""), ILIKE(""),
    BEETWEN("");

    private String operator;

    CriteriaConstraint(String operator) {
        this.operator = operator;
    }

    public String getOperator() {
        return operator;
    }

    @Override
    public String toString() {
        return this.name().toUpperCase();
    }
}
