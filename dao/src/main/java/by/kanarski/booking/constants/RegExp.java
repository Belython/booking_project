package by.kanarski.booking.constants;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

public class RegExp {

    public static final String RAW_DESTINATION = "([\\s,._]*)[A-Яа-я\\w-]+(\\1)";
//    public static final String RAW_DESTINATION = "[A-Яа-я\\w-]+";
    public static final String COMMAS = "[,._]";
    public static final String FIRST_IN_LIST = "^[A-Яа-я\\w- ]+, ";
    public static final String LAST_IN_LIST = ", [A-Яа-я\\w- ]+$";
    public static final String N_IN_LIST = ", [A-Яа-я\\w- ]+, ";
    public static final String DESTINATION = "((^[A-Яа-я\\w- ]+, )|(, (\\3), )|(, (\\3))$)";
    public static final String LOGIN = "\\w+";
    public static final String EMAIL = "^[0-9a-zA-Zа-яА-я]*[@][a-z]*[.][a-z]{1,3}$";
    public static final String PASSWORD = "*+";

}
