package pre.jsonHandler;

import by.kanarski.booking.entities.Language;
import by.kanarski.booking.entities.User;
import by.kanarski.booking.entities.facility.Facility;
import by.kanarski.booking.entities.facility.FacilityTranslation;
import by.kanarski.booking.entities.hotel.Hotel;
import by.kanarski.booking.entities.hotel.HotelTranslation;
import by.kanarski.booking.entities.location.Location;
import by.kanarski.booking.entities.location.LocationTranslation;
import by.kanarski.booking.entities.roomType.RoomType;
import by.kanarski.booking.entities.roomType.RoomTypeTranslation;
import org.springframework.stereotype.Component;

import java.util.*;

import static by.kanarski.booking.constants.FieldValue.ROLE_USER;
import static by.kanarski.booking.constants.FieldValue.STATUS_ACTIVE;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

@Component
public class TestEntityGenerator {

    private static TestEntityGenerator instance = null;

//    private final UserDao USER_DAO = UserDao.getInstance();
//    private final LocationDao LOCATION_DAO = LocationDao.getInstance();
//    private final HotelDao HOTEL_DAO = HotelDao.getInstance();
//    private final RoomTypeDao ROOM_TYPE_DAO = RoomTypeDao.getInstance();
//    private final RoomDao ROOM_DAO = RoomDao.getInstance();
//    private final BillDao BILL_DAO = BillDao.getInstance();

    private static final int USERS_AMOUNT = 10;
    private static final int UNIQUE_VALUES = 10;
    private static final int LOCATIONS_AMOUNT = 10;
    private static final int LANGUAGES_AMOUNT = 3;
    private static final int LOCATION_TRANSLATIONS_AMOUNT = LOCATIONS_AMOUNT * LANGUAGES_AMOUNT;
    private static final int HOTELS_PER_LOCATION = 3;
    private static final int HOTELS_AMOUNT = 5 * HOTELS_PER_LOCATION;
    private static final int HOTEL_TRANSLATIONS_AMOUNT = HOTELS_AMOUNT * LANGUAGES_AMOUNT;
    private static final int FACILITYS_AMOUNT = 10;
    private static final int FACILITY_TRANSLATIONS_AMOUNT = FACILITYS_AMOUNT * LANGUAGES_AMOUNT;
    private static final int ROOM_TYPES_AMOUNT = 10;
    private static final int ROOM_TYPE_TRANSLATIONS_AMOUNT = ROOM_TYPES_AMOUNT * LANGUAGES_AMOUNT;
    private static final int FACILITIES_PER_ROOM_TYPE = 4;



    private TestEntityGenerator() {
    }

    public static TestEntityGenerator getInstance() {
        if (instance == null) {
            instance = new TestEntityGenerator();
        }
        return instance;
    }

    public List<User> generateUsers() {
        Random random = getRandom();
        List<User> userList = new ArrayList<>();
        for (int i = 1; i <= USERS_AMOUNT; i++) {
            long userId = i;
            String firstName = "testFirstNameExpected" + random.nextInt(UNIQUE_VALUES);
            String lastName = "testLastNameExpected" + random.nextInt(UNIQUE_VALUES);
            String email = "testExpected" + i + "@gmail.com";
            String login = "testLoginExpected" + i;
            String password = "testPasswordExpected" + random.nextInt(UNIQUE_VALUES);
            String role = ROLE_USER;
            String userStatus = STATUS_ACTIVE;
            userList.add(new User(userId, firstName, lastName, email, login, password, role, userStatus));
        }
        return userList;
    }

    public List<Language> generateLanguages() {
        Random random = getRandom();
        List<Language> languageList = new ArrayList<>();
        for (int i = 1; i <= LANGUAGES_AMOUNT; i++) {
            long languageId = i;
            String name = "expectedLanguage" + i;
            languageList.add(new Language(languageId, name));
        }
        return languageList;
    }
    
    public List<LocationTranslation> generateLocationTranslations() {
        Random random = getRandom();
        List<Language> languageList = JsonManager.getLanguageList();
        List<LocationTranslation> locationTranslationList = new ArrayList<>();
        for (int i = 1; i <= LOCATION_TRANSLATIONS_AMOUNT; i++) {
            long locationTranslationId = i;
            String country = "testCountryExpected" + random.nextInt(UNIQUE_VALUES);
            String city = "testCityExpected" + i;
            Integer languageId = (Math.floorMod(i - 1, LANGUAGES_AMOUNT) + 1);
            Language language = languageList.get(languageId - 1);
            locationTranslationList.add(new LocationTranslation(locationTranslationId, country, city, null, language));
        }
        return locationTranslationList;
    }

    public List<Location> generateLocations() {
        Random random = getRandom();
        List<Location> locationList = new ArrayList<>();
        List<LocationTranslation> locationTranslationList = JsonManager.getLocationTranslationList();
        for (int i = 1; i <= LOCATIONS_AMOUNT; i++) {
            long locationId = i;
            String country = null;
            String city = null;
            Map<Long, LocationTranslation> locationTranslationMap = new HashMap<>();
            for (int j = 1; j <= LANGUAGES_AMOUNT; j++) {
                Integer locationTranslationId = j + (i - 1) * LANGUAGES_AMOUNT;
                LocationTranslation locationTranslation = locationTranslationList.get(locationTranslationId - 1);
                locationTranslationMap.put(Integer.toUnsignedLong(j), locationTranslation);
                if (j == 1) {
                    country = locationTranslation.getCountry();
                    city = locationTranslation.getCity();
                }
            }
            String locationStatus = STATUS_ACTIVE;
            Location location = new Location(locationId, country, city, null, locationTranslationMap, locationStatus);
            locationList.add(location);
        }
        return locationList;
    }

    public List<HotelTranslation> generateHotelTranslations() {
        Random random = getRandom();
        List<Language> languageList = JsonManager.getLanguageList();
        List<HotelTranslation> hotelTranslationList = new ArrayList<>();
        for (int i = 1; i <= HOTEL_TRANSLATIONS_AMOUNT; i++) {
            long hotelTranslationId = i;
            String hotelName = "testHotelExpected" + i;
            Integer languageId = (Math.floorMod(i - 1, LANGUAGES_AMOUNT) + 1);
            Language language = languageList.get(languageId - 1);
            hotelTranslationList.add(new HotelTranslation(hotelTranslationId, hotelName, null, language));
        }
        return hotelTranslationList;
    }

    public List<Hotel> generateHotels() {
        Random random = getRandom();
        List<Hotel> hotelList = new ArrayList<>();
        List<HotelTranslation> hotelTranslationList = JsonManager.getHotelTranslationList();
        List<Location> locationList = JsonManager.getLocationList();
        for (int i = 1; i <= HOTELS_AMOUNT; i++) {
            long hotelId = i;
            int locationId = Math.floorDiv(i - 1, HOTELS_PER_LOCATION);
            Location location = locationList.get(locationId);
            String hotelName = null;
            Map<Long, HotelTranslation> hotelTranslationMap = new HashMap<>();
            for (int j = 1; j <= LANGUAGES_AMOUNT; j++) {
                Integer hotelTranslationId = j + (i - 1) * LANGUAGES_AMOUNT;
                HotelTranslation hotelTranslation = hotelTranslationList.get(hotelTranslationId - 1);
                hotelTranslationMap.put(Integer.toUnsignedLong(j), hotelTranslation);
                if (j == 1) {
                    hotelName = hotelTranslation.getHotelName();
                }
            }
            String hotelStatus = STATUS_ACTIVE;
            Hotel hotel = new Hotel(hotelId, hotelName, location, null, hotelTranslationMap, hotelStatus);
            hotelList.add(hotel);
        }
        return hotelList;
    }

    public List<FacilityTranslation> generateFacilityTranslations() {
        Random random = getRandom();
        List<Language> languageList = JsonManager.getLanguageList();
        List<FacilityTranslation> facilityTranslationList = new ArrayList<>();
        for (int i = 1; i <= FACILITY_TRANSLATIONS_AMOUNT; i++) {
            long facilityTranslationId = i;
            String facilityName = "testFacilityExpected" + i;
            Integer languageId = (Math.floorMod(i - 1, LANGUAGES_AMOUNT) + 1);
            Language language = languageList.get(languageId - 1);
            facilityTranslationList.add(new FacilityTranslation(facilityTranslationId, facilityName, null, language));
        }
        return facilityTranslationList;
    }

    public List<Facility> generateFacilitys() {
        Random random = getRandom();
        List<Facility> facilityList = new ArrayList<>();
        List<FacilityTranslation> facilityTranslationList = JsonManager.getFacilityTranslationList();
        for (int i = 1; i <= FACILITYS_AMOUNT; i++) {
            long facilityId = i;
            String facilityName = null;
            Map<Long, FacilityTranslation> facilityTranslationMap = new HashMap<>();
            for (int j = 1; j <= LANGUAGES_AMOUNT; j++) {
                Integer facilityTranslationId = j + (i - 1) * LANGUAGES_AMOUNT;
                FacilityTranslation facilityTranslation = facilityTranslationList.get(facilityTranslationId - 1);
                facilityTranslationMap.put(Integer.toUnsignedLong(j), facilityTranslation);
                if (j == 1) {
                    facilityName = facilityTranslation.getFacilityName();
                }
            }
            String facilityStatus = STATUS_ACTIVE;
            Facility facility = new Facility(facilityId, facilityName, facilityTranslationMap, null, facilityStatus);
            facilityList.add(facility);
        }
        return facilityList;
    }

    public List<RoomTypeTranslation> generateRoomTypeTranslations() {
        Random random = getRandom();
        List<Language> languageList = JsonManager.getLanguageList();
        List<RoomTypeTranslation> roomTypeTranslationList = new ArrayList<>();
        for (int i = 1; i <= ROOM_TYPE_TRANSLATIONS_AMOUNT; i++) {
            long roomTypeTranslationId = i;
            String roomTypeName = "testRoomTypeExpected" + i;
            Integer languageId = (Math.floorMod(i - 1, LANGUAGES_AMOUNT) + 1);
            Language language = languageList.get(languageId - 1);
            roomTypeTranslationList.add(new RoomTypeTranslation(roomTypeTranslationId, roomTypeName, null, language));
        }
        return roomTypeTranslationList;
    }

    public List<RoomType> generateRoomTypes() {
        Random random = getRandom();
        List<RoomType> roomTypeList = new ArrayList<>();
        List<RoomTypeTranslation> roomTypeTranslationList = JsonManager.getRoomTypeTranslationList();
        List<Facility> facilityList = JsonManager.getFacilityList();
        for (int i = 1; i <= ROOM_TYPES_AMOUNT; i++) {
            long roomTypeId = i;
            String roomTypeName = null;
            Map<Long, RoomTypeTranslation> roomTypeTranslationMap = new HashMap<>();
            for (int j = 1; j <= LANGUAGES_AMOUNT; j++) {
                Integer roomTypeTranslationId = j + (i - 1) * LANGUAGES_AMOUNT;
                RoomTypeTranslation roomTypeTranslation = roomTypeTranslationList.get(roomTypeTranslationId - 1);
                roomTypeTranslationMap.put(Integer.toUnsignedLong(j), roomTypeTranslation);
                if (j == 1) {
                    roomTypeName = roomTypeTranslation.getRoomTypeName();
                }
            }
            Integer maxPersons = 1 + random.nextInt(UNIQUE_VALUES);
            Double pricePerNight = 1 + random.nextInt(UNIQUE_VALUES) * 45.5;
            Set<Facility> facilitySet = new HashSet<>();
            for (int j = 1; j <= FACILITIES_PER_ROOM_TYPE; j++) {
                int facilityId = random.nextInt(FACILITYS_AMOUNT - 1);
                Facility facility = facilityList.get(facilityId);
                facilitySet.add(facility);
            }
            String roomTypeStatus = STATUS_ACTIVE;
            RoomType roomType = new RoomType(roomTypeId, roomTypeName, maxPersons, pricePerNight,
                    facilitySet, null, roomTypeTranslationMap, roomTypeStatus);
            roomTypeList.add(roomType);
        }
        return roomTypeList;
    }

    private Random getRandom() {
        long seed = new Random().nextLong();
        Random random = new Random(seed);
        return random;
    }



}
