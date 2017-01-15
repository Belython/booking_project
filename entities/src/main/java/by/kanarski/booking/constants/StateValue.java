package by.kanarski.booking.constants;

import java.util.Arrays;
import java.util.List;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

public class StateValue {

    public static final String STATUS_ACTIVE = "STATUS_ACTIVE";
    public static final String STATUS_DELETED = "STATUS_DELETED";
    public static final String STATUS_PAID = "STATUS_PAID";
    public static final String STATUS_NOT_PAID = "STATUS_NOT_PAID";
    public static final String STATUS_CANCELED = "STATUS_CANCELED";
    public static final String ROLE_USER = "ROLE_USER";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_ANONYMOUS = "ROLE_ANONYMOUS";

    public static final List<String> STATUS_LIST = Arrays.asList(STATUS_ACTIVE, STATUS_DELETED);
    public static final List<String> ROLE_LIST = Arrays.asList(ROLE_ANONYMOUS, ROLE_USER, ROLE_ADMIN);


}
