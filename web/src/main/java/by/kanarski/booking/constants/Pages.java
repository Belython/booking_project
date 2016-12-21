package by.kanarski.booking.constants;

import java.util.Arrays;
import java.util.List;

public class Pages {
    //pages url
    public static final String PAGE_CLIENT = "client/client";
    public static final String PAGE_FORGOT_PASSWORD = "client/forgot_password";
    public static final String PAGE_INDEX = "index";
    public static final String PAGE_START = "";
    public static final String PAGE_REGISTRATION = "client/registration";
    public static final String PAGE_ALL_CLIENT_ORDERS = "client/orders";
    public static final String PAGE_PROCESS_ORDER = "admin/process_order";
    public static final String PAGE_EDIT_CLIENT = "client/edit_client_data";
    public static final String PAGE_SUCCESS_REDIRECT = "redirect:success";
    public static final String PAGE_ADMIN = "admin";
    public static final String PAGE_ERROR = "error";
    public static final String PAGE_ALL_ORDERS = "admin/all_orders";
    public static final String PAGE_ALL_USERS = "admin/all_users";
    public static final String PAGE_EDIT_ORDER = "client/edit_order";
    public static final String PAGE_SEARCH_RESULTS = "search_results";
    public static final String PAGE_HOTEL = "hotel";
    public static final String PAGE_MY_ACCOUNT = "my_account";
    public static final String PAGE_USERS_REDACTOR = "admin/users";
    //redirect
    public static final String REDIRECT_PAGE_ADD_CAR = "redirect:admin/main";
    public static final String REDIRECT_PAGE_USER = "redirect:user";
    public static final String REDIRECT_PAGE_CLIENT = "redirect:client/client";
    public static final String REDIRECT_INDEX = "redirect:users/index";
    public static final String REDIRECT_START = "redirect:/";
    public static final String REDIRECT_HOTEL = "redirect:hotel";
    public static final String REDIRECT_ALL_CLIENT_ORDERS = "redirect:client_orders";
    public static final String REDIRECT_ALL_ORDERS = "redirect:get_all_orders";
    //ajax pages
    public static final String POSSIBLE_DESTINATIONS = "possibleDestinations";
    //error pages
    public static final String PAGE_404 = "404";
    public static final String PAGE_500 = "500";
    //pages values for requests
    public static final String VALUE_CLIENT_PAGE = "client/client";
    public static final String VALUE_ADMIN_PAGE = "admin/main";
    public static final String VALUE_GO_TO_REGISTRATION = "/go_to_registration";
    public static final String VALUE_FORGOT_PASSWORD = "/forgot_password";
    public static final String VALUE_LOGIN = "login";
    public static final String VALUE_LOGOUT = "logout";
    public static final String VALUE_GO_TO_EDIT_CLIENT = "go_to_edit_client";
    public static final String VALUE_CHANGE_DATA = "change_data";
    public static final String VALUE_GET_PASSWORD = "get_password";
    public static final String VALUE_REGISTER_USER = "/register";
    public static final String VALUE_GET_ALL_USERS = "get_all_users";
    public static final String VALUE_404 = "404";
    public static final String VALUE_CONTACT_INFO = "contact_info";
    public static final String VALUE_INDEX = "/index";
    public static final String VALUE_INDEX_HTM = "/index.htm";
    public static final String VALUE_START = "/";
    public static final String VALUE_GO_TO_ORDERS = "go_to_orders";
    public static final String VALUE_GET_ALL_ORDERS = "get_all_orders";
    public static final String VALUE_MAKE_ORDER = "make_order";
    public static final String VALUE_DELETE_ORDER = "delete_order";
    public static final String VALUE_EDIT_ORDER = "edit_order";
    public static final String VALUE_CLIENT_ORDERS = "client_orders";
    public static final String PROCESS_ORDER = "process_order";
    public static final String VALUE_SET_LOCALE = "/set_locale";
    public static final String VALUE_SET_CURRENCY = "/set_currency";
    public static final String VALUE_SEARCH_HOTELS = "searchHotels";
    public static final String VALUE_SEARCH_RESULTS = "search_results";
    public static final String VALUE_GET_DESTINATIONS = "get_destinations";
    public static final String VALUE_WATCH_HOTEL = "watchHotel";
    public static final String VALUE_BOOK_ROOMS = "bookRooms";
    public static final String VALUE_TO_ACCOUNT = "toAccount";
    public static final String VALUE_CANCEL_BOOKING = "cancelBooking";
    public static final String VALUE_PAY_BILL = "payBill";
    public static final String VALUE_REMOVE_BILL = "removeBill";

    public static final String MAPPING_START = "/*";
    public static final String MAPPING_USERS = "/users";
    public static final String MAPPING_MY_ACCOUNT = "/my_account";
    public static final String MAPPING_HOTELS = "/hotels";

    public static final List<String> VIEW_LIST= Arrays.asList(
            PAGE_INDEX, PAGE_ERROR, PAGE_HOTEL, PAGE_SEARCH_RESULTS, PAGE_MY_ACCOUNT
    );
}
