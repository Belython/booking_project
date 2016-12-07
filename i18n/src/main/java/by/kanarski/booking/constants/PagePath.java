package by.kanarski.booking.constants;

public class PagePath {

    //User pages

    public static final String INDEX = "/WEB-INF/jsp/index.jsp";
    public static final String REGISTRATION = "/WEB-INF/assets/jsp/user/registration.jsp";
    public static final String SEARCH_RESULTS = "/WEB-INF/assets/jsp/user/searchResults.jsp";
    public static final String SELECT_ROOMS = "/WEB-INF/assets/jsp/user/selectRooms.jsp";

    //Client pages

    public static final String ACCOUNT = "/WEB-INF/assets/jsp/client/account.jsp";
    public static final String REMIND_PASSWORD = "/WEB-INF/assets/jsp/client/remindPassword.jsp";

    //Admin pages

    public static final String ADMIN_MAIN = "/WEB-INF/assets/jsp/admin/adminMain.jsp";

    public static final String ROOMS_REDACTOR = "/WEB-INF/assets/jsp/admin/roomsRedactor.jsp";
    public static final String ROOM_TYPES_REDACTOR = "/WEB-INF/assets/jsp/admin/roomTypesRedactor.jsp";
    public static final String LOCATIONS_REDACTOR = "/WEB-INF/assets/jsp/admin/locationsRedactor.jsp";
    public static final String USERS_REDACTOR = "/WEB-INF/assets/jsp/admin/usersRedactor.jsp";
    public static final String HOTELS_REDACTOR = "/WEB-INF/assets/jsp/admin/hotelsRedactor.jsp";
    public static final String BILLS_REDACTOR_PATH = "/WEB-INF/assets/jsp/admin/billsRedactor.jsp";

    //Error page

    public static final String ERROR = "/WEB-INF/assets/jsp/error/error.jsp";


    //AJAX includes

    //User

    public static final String POSSIBLE_DESTINATIONS = "/WEB-INF/assets/jsp/user/includes/possibleDestinations.jsp";


    //Admin

    public static final String ALTER_TABLE_ROW = "/WEB-INF/assets/jsp/admin/tableRedactor/alterTableRow.jsp";
    public static final String NEW_TABLE_ROW = "/WEB-INF/assets/jsp/admin/tableRedactor/newTableRow.jsp";

    private PagePath() {

    }
}
