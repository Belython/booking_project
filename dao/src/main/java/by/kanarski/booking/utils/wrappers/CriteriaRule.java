package by.kanarski.booking.utils.wrappers;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

public enum CriteriaRule {

    EQ("="), GT(">"), LT("<"), GE("=>"), LE("<=");

    private String operator;

    CriteriaRule(String operator) {
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
