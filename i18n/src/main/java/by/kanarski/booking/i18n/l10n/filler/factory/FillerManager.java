package by.kanarski.booking.i18n.l10n.filler.factory;

import by.kanarski.booking.constants.ContentName;
import by.kanarski.booking.constants.PagePath;
import by.kanarski.booking.constants.PageTextContentKeys;
import by.kanarski.booking.i18n.l10n.filler.Filler;

import java.util.*;

public enum FillerManager {
    INDEX (
            Arrays.asList(getIndex(), getHeader()),
            PagePath.INDEX
    ),
    ERROR (
            Arrays.asList(getError(), getHeader()),
            PagePath.ERROR
    ),
    REGISTRATION (
            Arrays.asList(getRegistration(), getHeader()),
            PagePath.REGISTRATION
    ),
    SEARCHRESULTS(
            Arrays.asList(getSelectHotel(), getHeader()),
            PagePath.SEARCH_RESULTS
    ),
    SELECTROOMS(
            Arrays.asList(getSelectRoom(), getHeader()),
            PagePath.SELECT_ROOMS
    ),
    ACCOUNT(
            Arrays.asList(getAccount(), getHeader()),
            PagePath.ACCOUNT
    ),
    ADMINMAIN(
            Arrays.asList(getAminMain(), getSideBar(), getHeader()),
            PagePath.ADMIN_MAIN
    ),
    ROOMSREDACTOR(
            Arrays.asList(getRoomsRedactor(), getSideBar(), getHeader()),
            PagePath.ROOMS_REDACTOR
            
    ),
    ROOMTYPESREDACTOR(
            Arrays.asList(getRoomTypesRedactor(), getSideBar(), getHeader()),
            PagePath.ROOM_TYPES_REDACTOR
            
    ),
    LOCATIONSREDACTOR(
            Arrays.asList(getLocationsRedactor(), getSideBar(), getHeader()),
            PagePath.LOCATIONS_REDACTOR
    ),
    USERSREDACTOR(
            Arrays.asList(getUsersRedactor(), getSideBar(), getHeader()),
            PagePath.USERS_REDACTOR
            
    ),
    HOTELSREDACTOR(
            Arrays.asList(getHotelsRedactor(), getSideBar(), getHeader()),
            PagePath.HOTELS_REDACTOR
                    
    ),
    REMINDPASSWORD(
            Arrays.asList(getRemindPassword(), getHeader()),
            PagePath.REMIND_PASSWORD
    );

    private List<Map<String, List<String>>> pageDescriptor;
    private String pagePath;

    FillerManager(List<Map<String, List<String>>> pageDescriptor, String pagePath) {
        this.pageDescriptor = pageDescriptor;
    }

    public Filler getFiller() {
        return new Filler(pageDescriptor);
    }

    public static Map<String, List<String>> getIndex() {
        Map<String, List<String>> contentMap = new HashMap<>();
        contentMap.put(ContentName.STRING, PageTextContentKeys.INDEX);
        return contentMap;
    }

    public static Map<String, List<String>> getHeader() {
        Map<String, List<String>> contentMap = new HashMap<>();
        List<String> tempStringKeyList = new ArrayList<>();
        tempStringKeyList.addAll(PageTextContentKeys.HEADER);
        tempStringKeyList.addAll(PageTextContentKeys.REGISTRATION);
        contentMap.put(ContentName.STRING, tempStringKeyList);
        contentMap.put(ContentName.LOCALE_MAP, PageTextContentKeys.LOCALE_LIST);
        contentMap.put(ContentName.CURRENCY_MAP, PageTextContentKeys.CURRENCY_LIST);
        return contentMap;
    }

    public static Map<String, List<String>> getError() {
        Map<String, List<String>> contentMap = new HashMap<>();
        contentMap.put(ContentName.STRING, PageTextContentKeys.ERROR);
        return contentMap;
    }

    public static Map<String, List<String>> getRegistration() {
        Map<String, List<String>> contentMap = new HashMap<>();
        contentMap.put(ContentName.STRING, PageTextContentKeys.REGISTRATION);
        return contentMap;
    }

    public static Map<String, List<String>> getAccount() {
        Map<String, List<String>> contentMap = new HashMap<>();
        contentMap.put(ContentName.STRING, PageTextContentKeys.ACCOUNT);
        return contentMap;
    }

    public static Map<String, List<String>> getSelectHotel() {
        Map<String, List<String>> contentMap = new HashMap<>();
        contentMap.put(ContentName.STRING, PageTextContentKeys.SELECT_HOTEL);
        return contentMap;
    }

    public static Map<String, List<String>> getSelectRoom() {
        Map<String, List<String>> contentMap = new HashMap<>();
        contentMap.put(ContentName.STRING, PageTextContentKeys.SELECT_ROOM);
        return contentMap;
    }

    public static Map<String, List<String>> getAminMain() {
        Map<String, List<String>> contentMap = new HashMap<>();
        contentMap.put(ContentName.STRING, PageTextContentKeys.ADMIN_MAIN);
        return contentMap;
    }

    public static Map<String, List<String>> getSideBar() {
        Map<String, List<String>> contentMap = new HashMap<>();
        contentMap.put(ContentName.STRING, PageTextContentKeys.SIDE_BAR);
        return contentMap;
    }

    public static Map<String, List<String>> getRoomsRedactor() {
        Map<String, List<String>> contentMap = new HashMap<>();
        contentMap.put(ContentName.STRING, PageTextContentKeys.TABLE_REDACTOR);
        contentMap.put(ContentName.COLUMN_NAME_LIST, PageTextContentKeys.ROOM_COLUMN_LIST);
        return contentMap;
    }

    public static Map<String, List<String>> getRoomTypesRedactor() {
        Map<String, List<String>> contentMap = new HashMap<>();
        contentMap.put(ContentName.STRING, PageTextContentKeys.TABLE_REDACTOR);
        contentMap.put(ContentName.COLUMN_NAME_LIST, PageTextContentKeys.ROOM_TYPE_COLUMN_LIST);
        return contentMap;
    }

    public static Map<String, List<String>> getLocationsRedactor() {
        Map<String, List<String>> contentMap = new HashMap<>();
        contentMap.put(ContentName.STRING, PageTextContentKeys.TABLE_REDACTOR);
        contentMap.put(ContentName.COLUMN_NAME_LIST, PageTextContentKeys.LOCATION_COLUMN_LIST);
        return contentMap;
    }

    public static Map<String, List<String>> getUsersRedactor() {
        Map<String, List<String>> contentMap = new HashMap<>();
        contentMap.put(ContentName.STRING, PageTextContentKeys.TABLE_REDACTOR);
        contentMap.put(ContentName.COLUMN_NAME_LIST, PageTextContentKeys.USER_COLUMN_LIST);
        return contentMap;
    }

    public static Map<String, List<String>> getHotelsRedactor() {
        Map<String, List<String>> contentMap = new HashMap<>();
        contentMap.put(ContentName.STRING, PageTextContentKeys.TABLE_REDACTOR);
        contentMap.put(ContentName.COLUMN_NAME_LIST, PageTextContentKeys.HOTEL_COLUMN_LIST);
        return contentMap;
    }

    public static Map<String, List<String>> getRemindPassword() {
        Map<String, List<String>> contentMap = new HashMap<>();
        contentMap.put(ContentName.STRING, PageTextContentKeys.REMIND_PASSWORD);
        return contentMap;
    }

    @Override
    public String toString() {
        return this.pagePath;
    }
}
