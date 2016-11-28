package by.kanarski.booking.utils.wrappers;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

public enum CriteriaConstraint {

    EQ("="), GT(">"), LT("<"), GE("=>"), LE("<="),
    LIKE(""), ILIKE("");

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
