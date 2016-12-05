package by.kanarski.booking.utils;

import by.kanarski.booking.constants.DtoName;
import by.kanarski.booking.constants.FieldValue;
import by.kanarski.booking.constants.SystemCurrency;
import by.kanarski.booking.constants.SystemLocale;
import by.kanarski.booking.dto.BillDto;
import by.kanarski.booking.dto.RoomDto;
import by.kanarski.booking.dto.UserDto;
import by.kanarski.booking.dto.hotel.HotelDto;
import by.kanarski.booking.dto.hotel.UserHotelDto;
import by.kanarski.booking.dto.location.LocationDto;
import by.kanarski.booking.dto.roomType.RoomTypeDto;
import by.kanarski.booking.entities.Bill;
import by.kanarski.booking.entities.Room;
import by.kanarski.booking.entities.User;
import by.kanarski.booking.entities.facility.Facility;
import by.kanarski.booking.entities.facility.FacilityTranslation;
import by.kanarski.booking.entities.hotel.Hotel;
import by.kanarski.booking.entities.hotel.HotelTranslation;
import by.kanarski.booking.entities.location.Location;
import by.kanarski.booking.entities.location.LocationTranslation;
import by.kanarski.booking.entities.roomType.RoomType;
import by.kanarski.booking.entities.roomType.RoomTypeTranslation;
import by.kanarski.booking.exceptions.DaoException;
import by.kanarski.booking.exceptions.LocalisationException;
import by.kanarski.booking.managers.SupportedLanguagesManager;
import by.kanarski.booking.utils.threadLocal.UserPreferences;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.*;

//@Component
//@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class DtoToEntityConverter<E, D> {

    private static Locale defaultLocale = SystemLocale.DEFAULT;
    private static Currency defaultCurrency = SystemCurrency.DEFAULT;

//    @Autowired
//    private IRoomTypeDao roomTypeDao;

//    @Autowired
    private ApplicationContext context = new ClassPathXmlApplicationContext("service-config.xml");
    private EntityBuilder entityBuilder = (EntityBuilder) context.getBean("entityBuilder");
//    @Autowired
//    private EntityBuilder entityBuilder;

    private Long languageId;
    private Class<E> entityClass;
    private Class<D> dtoClass;

    public DtoToEntityConverter(Class<E> entityClass, Class<D> dtoClass) {
        this.entityClass = entityClass;
        this.dtoClass = dtoClass;
        this.languageId = SupportedLanguagesManager.getLanguageId(UserPreferences.getLocale().getLanguage());
    }

    public DtoToEntityConverter(Class<E> entityClass, Class<D> dtoClass, String language) {
        this.entityClass = entityClass;
        this.dtoClass = dtoClass;
        this.languageId = SupportedLanguagesManager.getLanguageId(language);
    }

    @SuppressWarnings("unchecked")
    public E toEntity(D dto) throws LocalisationException, DaoException {
        String dtoName = dtoClass.getSimpleName();
        Object entity = null;
        switch (dtoName) {
            case DtoName.USER_DTO: {
                entity = toUser((UserDto) dto);
                break;
            }
            case DtoName.LOCATION_DTO: {
                entity = toLocation((LocationDto) dto);
                break;
            }
            case DtoName.HOTEL_DTO: {
                entity = toHotel((HotelDto) dto);
                break;
            }
            case DtoName.USER_HOTEL_DTO: {
                entity = toHotel((UserHotelDto) dto);
                break;
            }
            case DtoName.ROOM_TYPE_DTO: {
                entity = toRoomType((RoomTypeDto) dto);
                break;
            }
            case DtoName.ROOM_DTO: {
                entity = toRoom((RoomDto) dto);
                break;
            }
            case DtoName.BILL_DTO: {
                entity = toBill((BillDto) dto);
                break;
            }
        }
        return (E) entity;
    }

    @SuppressWarnings("unchecked")
    public D toDto(E entity) throws LocalisationException {
        String dtoName = dtoClass.getSimpleName();
        Object dto = null;
        switch (dtoName) {
            case DtoName.USER_DTO: {
                dto = toUserDto((User) entity);
                break;
            }
            case DtoName.LOCATION_DTO: {
                dto = toLocationDto((Location) entity);
                break;
            }
            case DtoName.HOTEL_DTO: {
                dto = toHotelDto((Hotel) entity);
                break;
            }
            case DtoName.USER_HOTEL_DTO: {
                dto = toUserHotelDto((Hotel) entity);
                break;
            }
            case DtoName.ROOM_TYPE_DTO: {
                dto = toRoomTypeDto((RoomType) entity);
                break;
            }
            case DtoName.ROOM_DTO: {
                dto = toRoomDto((Room) entity);
                break;
            }
            case DtoName.BILL_DTO: {
                dto = toBillDto((Bill) entity);
                break;
            }
        }
        return (D) dto;
    }

    @SuppressWarnings("unchecked")
    public Set<E> toEntitySet(List<D> dtoList) throws LocalisationException, DaoException {
        String dtoName = dtoClass.getSimpleName();
        Set entitySet = null;
        switch (dtoName) {
            case DtoName.USER_DTO: {
                entitySet = toUserSet((List<UserDto>) dtoList);
                break;
            }
            case DtoName.LOCATION_DTO: {
                entitySet = toLocationSet((List<LocationDto>) dtoList);
                break;
            }
            case DtoName.HOTEL_DTO: {
                entitySet = toHotelSet((List<HotelDto>) dtoList);
                break;
            }
            case DtoName.USER_HOTEL_DTO: {
                entitySet = toHotelSetFromUserHotelList((List<UserHotelDto>) dtoList);
                break;
            }
            case DtoName.ROOM_TYPE_DTO: {
                entitySet = toRoomTypeSet((List<RoomTypeDto>) dtoList);
                break;
            }
            case DtoName.ROOM_DTO: {
                entitySet = toRoomSet((List<RoomDto>) dtoList);
                break;
            }
            case DtoName.BILL_DTO: {
                entitySet = toBillSet((List<BillDto>) dtoList);
                break;
            }
        }
        return (Set<E>) entitySet;
    }

    @SuppressWarnings("unchecked")
    public List<E> toEntityList(List<D> dtoList) throws LocalisationException, DaoException {
        String dtoName = dtoClass.getSimpleName();
        List entityList = null;
        switch (dtoName) {
            case DtoName.USER_DTO: {
                entityList = toUserList((List<UserDto>) dtoList);
                break;
            }
            case DtoName.LOCATION_DTO: {
                entityList = toLocationList((List<LocationDto>) dtoList);
                break;
            }
            case DtoName.HOTEL_DTO: {
                entityList = toHotelList((List<HotelDto>) dtoList);
                break;
            }
            case DtoName.USER_HOTEL_DTO: {
                entityList = toHotelListFromUserHotelList((List<UserHotelDto>) dtoList);
                break;
            }
            case DtoName.ROOM_TYPE_DTO: {
                entityList = toRoomTypeList((List<RoomTypeDto>) dtoList);
                break;
            }
            case DtoName.ROOM_DTO: {
                entityList = toRoomList((List<RoomDto>) dtoList);
                break;
            }
            case DtoName.BILL_DTO: {
                entityList = toBillList((List<BillDto>) dtoList);
                break;
            }
        }
        return (List<E>) entityList;
    }

    @SuppressWarnings("unchecked")
    public List<D> toDtoList(List<E> entityList) throws LocalisationException, DaoException {
        String dtoName = dtoClass.getSimpleName();
        List dtoList = null;
        switch (dtoName) {
            case DtoName.USER_DTO: {
                dtoList = toUserDtoList((List<User>) entityList);
                break;
            }
            case DtoName.LOCATION_DTO: {
                dtoList = toLocationDtoList((List<Location>) entityList);
                break;
            }
            case DtoName.HOTEL_DTO: {
                dtoList = toHotelDtoList((List<Hotel>) entityList);
                break;
            }
            case DtoName.USER_HOTEL_DTO: {
                dtoList = toUserHotelDtoList((List<Hotel>) entityList);
                break;
            }
            case DtoName.ROOM_TYPE_DTO: {
                dtoList = toRoomTypeDtoList((List<RoomType>) entityList);
                break;
            }
            case DtoName.ROOM_DTO: {
                dtoList = toRoomDtoList((List<Room>) entityList);
                break;
            }
            case DtoName.BILL_DTO: {
                dtoList = toBillDtoList((List<Bill>) entityList);
                break;
            }
        }
        return (List<D>) dtoList;
    }

    public HotelDto toAnyHotelDto(LocationDto locationDto) {
        return new HotelDto(locationDto, FieldValue.ANY_HOTEL);
    }

    public List<HotelDto> toAnyHotelDtoList(List<LocationDto> locationDtoList) {
        List<HotelDto> hotelDtoList = new ArrayList<>();
        for (LocationDto locationDto : locationDtoList) {
            hotelDtoList.add(toAnyHotelDto(locationDto));
        }
        return hotelDtoList;
    }

    public Long getLanguageId() {
        return languageId;
    }

    public void setLanguage(String language) {
        this.languageId = SupportedLanguagesManager.getLanguageId(language);
    }

    private RoomDto toRoomDto(Room room) throws LocalisationException {
        Long roomId = room.getRoomId();
        Hotel roomHotel = room.getHotel();
        HotelDto roomHotelDto = toHotelDto(roomHotel);
        RoomType roomType = room.getRoomType();
        RoomTypeDto roomTypeDto = toRoomTypeDto(roomType);
        Integer roomNumber = room.getRoomNumber();
        String roomStatus = room.getRoomStatus();
        return new RoomDto(roomId, roomHotelDto, roomTypeDto, roomNumber, roomStatus);
    }

    private List<RoomDto> toRoomDtoList(Set<Room> roomSet) throws LocalisationException {
        List<RoomDto> roomDtoList = new ArrayList<>();
        for (Room room : roomSet) {
            RoomDto roomDto = toRoomDto(room);
            roomDtoList.add(roomDto);
        }
        return roomDtoList;
    }

    private List<RoomDto> toRoomDtoList(List<Room> roomList) throws LocalisationException {
        List<RoomDto> roomDtoList = new ArrayList<>();
        for (Room room : roomList) {
            RoomDto roomDto = toRoomDto(room);
            roomDtoList.add(roomDto);
        }
        return roomDtoList;
    }


    private Room toRoom(RoomDto roomDto) throws LocalisationException, DaoException {
        Long roomId = roomDto.getRoomId();
        Hotel hotel = toHotel(roomDto.getHotel());
        RoomType roomType = toRoomType(roomDto.getRoomType());
        Integer roomNumber = roomDto.getRoomNumber();
        String roomStatus = roomDto.getRoomStatus();
        return entityBuilder.buildRoom(roomId, hotel, roomType, roomNumber, roomStatus);
    }

    private List<Room> toRoomList(List<RoomDto> roomDtoList) throws LocalisationException, DaoException {
        List<Room> roomList = new ArrayList<>();
        for (RoomDto roomDto : roomDtoList) {
            Room room = toRoom(roomDto);
            roomList.add(room);
        }
        return roomList;
    }

    private Set<Room> toRoomSet(List<RoomDto> roomDtoList) throws LocalisationException, DaoException {
        Set<Room> roomSet = new HashSet<>();
        for (RoomDto roomDto : roomDtoList) {
            Room room = toRoom(roomDto);
            roomSet.add(room);
        }
        return roomSet;
    }

    private BillDto toBillDto(Bill bill) throws LocalisationException {
        Long billId = bill.getBillId();
        User client = bill.getClient();
        UserDto clientDto = toUserDto(client);
        Integer totalPersons = bill.getTotalPersons();
        String checkInDate = DateUtil.getFormattedDate(bill.getCheckInDate(), defaultLocale);
        String checkOutDate = DateUtil.getFormattedDate(bill.getCheckOutDate(), defaultLocale);
        Set<Room> roomSet = bill.getRoomSet();
        List<RoomDto> roomDtoList = toRoomDtoList(roomSet);
        Hotel hotel = roomSet.iterator().next().getHotel();
        HotelDto hotelDto = toHotelDto(hotel);
        Double paymentAmountUSD = bill.getPaymentAmount();
        Double paymentAmount = CurrencyUtil.convertFromUSD(paymentAmountUSD, defaultCurrency);
        String billStatus = bill.getBillStatus();
        return new BillDto(billId, clientDto, totalPersons, checkInDate, checkOutDate, hotelDto,
                roomDtoList, paymentAmount, billStatus);
    }

    private List<BillDto> toBillDtoList(Set<Bill> billSet) throws LocalisationException {
        List<BillDto> billDtoList = new ArrayList<>();
        for (Bill bill : billSet) {
            BillDto billDto = toBillDto(bill);
            billDtoList.add(billDto);
        }
        return billDtoList;
    }

    private List<BillDto> toBillDtoList(List<Bill> billList) throws LocalisationException {
        List<BillDto> billDtoList = new ArrayList<>();
        for (Bill bill : billList) {
            BillDto billDto = toBillDto(bill);
            billDtoList.add(billDto);
        }
        return billDtoList;
    }

    private Bill toBill(BillDto billDto) throws LocalisationException, DaoException {
        Long billId = billDto.getBillId();
        UserDto clientDto = billDto.getClient();
        User client = toUser(clientDto);
        Integer totalPersons = billDto.getTotalPersons();
        Long checkInDate = DateUtil.parseDate(billDto.getCheckInDate());
        Long checkOutDate = DateUtil.parseDate(billDto.getCheckOutDate());
        List<RoomDto> roomDtoList = billDto.getRoomList();
        Set<Room> roomSet = toRoomSet(roomDtoList);
        Double paymentAmount = billDto.getPaymentAmount();
        Double paymentAmountUSD = CurrencyUtil.convertToUSD(paymentAmount, defaultCurrency);
        String billStatus = billDto.getBillStatus();
        return entityBuilder.buildBill(billId, client, totalPersons, checkInDate, checkOutDate,
               roomSet, paymentAmountUSD, billStatus);
    }

    private List<Bill> toBillList(List<BillDto> billDtoList) throws DaoException, LocalisationException {
        return null;
    }
    
    private Set<Bill> toBillSet(List<BillDto> billDtoList) throws DaoException, LocalisationException {
        return null;
    }

    private RoomTypeDto toRoomTypeDto(RoomType roomType) {
        Long roomTypeId = roomType.getRoomTypeId();
        RoomTypeTranslation roomTypeTranslation = roomType.getRoomTypeTranslationMap().get(languageId);
        String roomTypeName = roomTypeTranslation.getRoomTypeName();
        Integer maxPersons = roomType.getMaxPersons();
        Double pricePerNightUSD = roomType.getPricePerNight();
        Double pricePerNight = CurrencyUtil.convertFromUSD(pricePerNightUSD, defaultCurrency);
        Set<Facility> facilitySet = roomType.getFacilitySet();
        StringBuilder facilities = new StringBuilder();
        for (Facility facility : facilitySet) {
            FacilityTranslation facilityTranslation = facility.getFacilityTranslationMap().get(languageId);
            String facilityName = facilityTranslation.getFacilityName();
            facilities.append(facilityName);
        }
        String roomTypeStatus = roomType.getRoomTypeStatus();
        return new RoomTypeDto(roomTypeId, roomTypeName, maxPersons,
                pricePerNight, facilities.toString(), roomTypeStatus);
    }

    private List<RoomTypeDto> toRoomTypeDtoList(List<RoomType> roomTypeList) {
        List<RoomTypeDto> roomTypeDtoList = new ArrayList<>();
        for (RoomType roomType : roomTypeList) {
            RoomTypeDto roomTypeDto = toRoomTypeDto(roomType);
            roomTypeDtoList.add(roomTypeDto);
        }
        return roomTypeDtoList;
    }

    private RoomType toRoomType(RoomTypeDto roomTypeDto) throws DaoException {
        Long roomTypeId = roomTypeDto.getRoomTypeId();
        String roomTypeName = roomTypeDto.getRoomTypeName();
        Integer maxPersons = roomTypeDto.getMaxPersons();
        Double pricePerNight = roomTypeDto.getPricePerNight();
        Double pricePerNightUSD = CurrencyUtil.convertToUSD(pricePerNight, defaultCurrency);
        Set<Facility> facilitySet = toFacilitySet(roomTypeId);
        String roomTypeStatus = roomTypeDto.getRoomTypeStatus();
        String language = roomTypeDto.getLanguage();
        Long laguageId = SupportedLanguagesManager.getLanguageId(language);
        return entityBuilder.buildRoomType(roomTypeId, roomTypeName, maxPersons, pricePerNightUSD,
                facilitySet, roomTypeStatus, languageId);
    }

    // TODO: 04.12.2016 Поправить, нужно добавить в EntutyBuilder
    private Set<Facility> toFacilitySet(Long roomTypeId) throws DaoException {
//        RoomType roomType = roomTypeDao.getById(roomTypeId);
        RoomType roomType = new RoomType();
        return roomType.getFacilitySet();
    }

    private List<RoomType> toRoomTypeList(List<RoomTypeDto> roomTypeDtoList) throws DaoException {
        List<RoomType> roomTypeList = new ArrayList<>();
        for (RoomTypeDto roomTypeDto : roomTypeDtoList) {
            RoomType roomType = toRoomType(roomTypeDto);
            roomTypeList.add(roomType);
        }
        return roomTypeList;
    }

    private Set<RoomType> toRoomTypeSet(List<RoomTypeDto> roomTypeDtoList) throws DaoException {
        Set<RoomType> roomTypeSet = new HashSet<>();
        for (RoomTypeDto roomTypeDto : roomTypeDtoList) {
            RoomType roomType = toRoomType(roomTypeDto);
            roomTypeSet.add(roomType);
        }
        return roomTypeSet;
    }

    private LocationDto toLocationDto(Location location) {
        Long locationId = location.getLocationId();
        LocationTranslation locationTranslation = location.getLocationTranslationMap().get(languageId);
        String country = locationTranslation.getCountry();
        String city = locationTranslation.getCity();
        String locationStatus = location.getLocationStatus();
        return new LocationDto(locationId, country, city, locationStatus);
    }

    private Location toLocation(LocationDto locationDto) throws DaoException {
        Long locationId = locationDto.getLocationId();
        String country = locationDto.getCountry();
        String city = locationDto.getCity();
        String locationStatus = locationDto.getLocationStatus();
        String language = locationDto.getLanguage();
        Long languageId = SupportedLanguagesManager.getLanguageId(language);
        return entityBuilder.buildLocation(locationId, country, city, locationStatus, languageId);
    }

    private List<LocationDto> toLocationDtoList(List<Location> locationList) {
        List<LocationDto> locationDtoList = new ArrayList<>();
        for (Location location : locationList) {
            LocationDto locationDto = toLocationDto(location);
            locationDtoList.add(locationDto);
        }
        return locationDtoList;
    }

    private List<Location> toLocationList(List<LocationDto> locationDtoList) throws DaoException {
        List<Location> locationList = new ArrayList<>();
        for (LocationDto locationDto : locationDtoList) {
            Location location = toLocation(locationDto);
            locationList.add(location);
        }
        return locationList;
    }

    private Set<Location> toLocationSet(List<LocationDto> locationDtoList) throws DaoException {
        Set<Location> locationSet = new HashSet<>();
        for (LocationDto locationDto : locationDtoList) {
            Location location = toLocation(locationDto);
            locationSet.add(location);
        }
        return locationSet;
    }

    private HotelDto toHotelDto(Hotel hotel) {
        Long hotelId = hotel.getHotelId();
        Location location = hotel.getLocation();
        LocationDto locationDto = toLocationDto(location);
        HotelTranslation hotelTranslation = hotel.getHotelTranslationMap().get(languageId);
        String hotelName = hotelTranslation.getHotelName();
        String hotelStatus = hotel.getHotelStatus();
        return new HotelDto(hotelId, locationDto, hotelName, hotelStatus);
    }

    private List<HotelDto> toHotelDtoList(List<Hotel> hotelList) {
        List<HotelDto> hotelDtoList = new ArrayList<>();
        for (Hotel hotel : hotelList) {
            HotelDto hotelDto = toHotelDto(hotel);
            hotelDtoList.add(hotelDto);
        }
        return hotelDtoList;
    }

    public static HotelDto toHotelDto(UserHotelDto userHotelDto) {
        Long hotelId = userHotelDto.getHotelId();
        LocationDto locationDto = userHotelDto.getLocation();
        String hotelName = userHotelDto.getHotelName();
        String hotelStatus = userHotelDto.getHotelStatus();
        return new HotelDto(hotelId, locationDto, hotelName, hotelStatus);
    }

    private UserHotelDto toUserHotelDto(Hotel hotel) throws LocalisationException {
        HotelDto hotelDto = toHotelDto(hotel);
        List<RoomDto> roomDtoSet = toRoomDtoList(hotel.getRoomSet());
        return new UserHotelDto(hotelDto, roomDtoSet);
    }

    private List<UserHotelDto> toUserHotelDtoList(Set<Hotel> hotelSet) throws LocalisationException {
        List<UserHotelDto> userHotelDtoList = new ArrayList<>();
        for (Hotel hotel : hotelSet) {
            UserHotelDto userHotelDto = toUserHotelDto(hotel);
            userHotelDtoList.add(userHotelDto);
        }
        return userHotelDtoList;
    }

    private List<UserHotelDto> toUserHotelDtoList(List<Hotel> hotelList) throws LocalisationException {
        List<UserHotelDto> userHotelDtoList = new ArrayList<>();
        for (Hotel hotel : hotelList) {
            UserHotelDto userHotelDto = toUserHotelDto(hotel);
            userHotelDtoList.add(userHotelDto);
        }
        return userHotelDtoList;
    }

    private Hotel toHotel(UserHotelDto userHotelDto) throws DaoException {
        Long hotelId = userHotelDto.getHotelId();
        LocationDto locationDto = userHotelDto.getLocation();
        Location location = toLocation(locationDto);
        String hotelName = userHotelDto.getHotelName();
        String hotelStatus = userHotelDto.getHotelStatus();
        String language = userHotelDto.getLanguage();
        Long languageId = SupportedLanguagesManager.getLanguageId(language);
        return entityBuilder.buildHotel(hotelId, location, hotelName, hotelStatus, languageId);
    }

    private List<Hotel> toHotelListFromUserHotelList(List<UserHotelDto> userHotelDtoList) throws DaoException {
        List<Hotel> hotelList = new ArrayList<>();
        for (UserHotelDto userHotelDto : userHotelDtoList) {
            Hotel hotel = toHotel(userHotelDto);
            hotelList.add(hotel);
        }
        return hotelList;
    }

    private Set<Hotel> toHotelSetFromUserHotelList(List<UserHotelDto> userHotelDtoList) throws DaoException {
        Set<Hotel> hotelList = new HashSet<>();
        for (UserHotelDto userHotelDto : userHotelDtoList) {
            Hotel hotel = toHotel(userHotelDto);
            hotelList.add(hotel);
        }
        return hotelList;
    }

    private Hotel toHotel(HotelDto hotelDto) throws DaoException {
        Long hotelId = hotelDto.getHotelId();
        LocationDto locationDto = hotelDto.getLocation();
        Location location = toLocation(locationDto);
        String hotelName = hotelDto.getHotelName();
        String hotelStatus = hotelDto.getHotelStatus();
        String laguage = hotelDto.getLanguage();
        Long languageId = SupportedLanguagesManager.getLanguageId(laguage);
        return entityBuilder.buildHotel(hotelId, location, hotelName, hotelStatus, languageId);
    }

    private List<Hotel> toHotelList(List<HotelDto> hotelDtoList) throws DaoException {
        List<Hotel> hotelList = new ArrayList<>();
        for (HotelDto hotelDto : hotelDtoList) {
            Hotel hotel = toHotel(hotelDto);
            hotelList.add(hotel);
        }
        return hotelList;
    }

    private Set<Hotel> toHotelSet(List<HotelDto> hotelDtoList) throws DaoException {
        Set<Hotel> hotelSet = new HashSet<>();
        for (HotelDto hotelDto : hotelDtoList) {
            Hotel hotel = toHotel(hotelDto);
            hotelSet.add(hotel);
        }
        return hotelSet;
    }

    private UserDto toUserDto(User user) {
        Long userId = user.getUserId();
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String email = user.getEmail();
        String login = user.getLogin();
        String password = user.getPassword();
        String role = user.getRole();
        String userStatus = user.getUserStatus();
        return new UserDto(userId, firstName, lastName, email, login, password, role, userStatus);
    }

    private List<UserDto> toUserDtoList(List<User> userList) {
        List<UserDto> userDtoList = new ArrayList<>();
        for (User user : userList) {
            UserDto userDto = toUserDto(user);
            userDtoList.add(userDto);
        }
        return userDtoList;
    }

    private User toUser(UserDto userDto) throws DaoException {
        Long userId = userDto.getUserId();
        String firstName = userDto.getFirstName();
        String lastName = userDto.getLastName();
        String email = userDto.getEmail();
        String login = userDto.getLogin();
        String password = userDto.getPassword();
        String role = userDto.getRole();
        String userStatus = userDto.getUserStatus();
        return entityBuilder.buildUser(userId, firstName, lastName, email, login, password, role, userStatus);
    }

    private List<User> toUserList(List<UserDto> userDtoList) throws DaoException {
        List<User> userList = new ArrayList<>();
        for (UserDto userDto : userDtoList) {
            User user = toUser(userDto);
            userList.add(user);
        }
        return userList;
    }

    private Set<User> toUserSet(List<UserDto> userDtoList) throws DaoException {
        Set<User> userSet = new HashSet<>();
        for (UserDto userDto : userDtoList) {
            User user = toUser(userDto);
            userSet.add(user);
        }
        return userSet;
    }

//    private Class getPersistentDtoClass() {
//        ParameterizedType classType = (ParameterizedType) getClass().getGenericSuperclass();
//        Class<D> persistentClass = (Class<D>) classType.getActualTypeArguments()[1];
//        return persistentClass;
//    }

}
