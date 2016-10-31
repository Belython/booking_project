package by.kanarski.booking.utils.field;

import java.util.*;

public class FieldBuilder {

    private static final String PRIMITIVE = "primitive";
    private static final String FREE_PRIMITIVE = "freePrimitive";
    private static final String DATE = "date";
    private static final String MONEY = "money";
    private static final String ITERABLE = "iterable";
    private static final String MAP_DATE_DATE = "mapDateDate";
    private static final String ENTITY = "entity";

    public static <T> FieldDescriptor<T> buildPrimitive(Iterable<T> allowedValues) {
        FieldDescriptor<T> fieldDescriptor = new FieldDescriptor<>();
        fieldDescriptor.setFieldType(PRIMITIVE);
        fieldDescriptor.setAllowedValues(allowedValues);
        return fieldDescriptor;
    }

    public static FieldDescriptor buildFreePrimitive() {
        FieldDescriptor fieldDescriptor = new FieldDescriptor();
        fieldDescriptor.setFieldType(FREE_PRIMITIVE);
        return fieldDescriptor;
    }

    public static FieldDescriptor buildDate() {
        FieldDescriptor fieldDescriptor = new FieldDescriptor();
        fieldDescriptor.setFieldType(DATE);
        return fieldDescriptor;
    }

    public static FieldDescriptor buildMoney() {
        FieldDescriptor fieldDescriptor = new FieldDescriptor();
        fieldDescriptor.setFieldType(MONEY);
        return fieldDescriptor;
    }

    public static FieldDescriptor buildIterable() {
        FieldDescriptor fieldDescriptor = new FieldDescriptor();
        fieldDescriptor.setFieldType(ITERABLE);
        return fieldDescriptor;
    }

    public static <T> FieldDescriptor<T> buildEntity(LinkedHashMap<String, FieldDescriptor> fieldMap, T owner) {
        FieldDescriptor<T> fieldDescriptor = new FieldDescriptor<>();
        fieldDescriptor.setFieldType(ENTITY);
        fieldDescriptor.setFieldMap(fieldMap);
        fieldDescriptor.setOwner(owner);
        return fieldDescriptor;
    }

}
