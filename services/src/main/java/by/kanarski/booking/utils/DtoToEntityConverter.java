package by.kanarski.booking.utils;

import by.kanarski.booking.constants.BookingSystemCurrency;
import by.kanarski.booking.constants.BookingSystemLocale;
import by.kanarski.booking.constants.DtoName;
import by.kanarski.booking.dao.impl.RoomTypeDao;
import by.kanarski.booking.dto.*;
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
import by.kanarski.booking.utils.wrappers.SupportedLanguages;

import java.util.*;

// TODO: 21.11.2016 НУЖНО РЕАЛИЗОВАТЬ НОРМАЛЬНУЮ РАБОТУ С ЯЗЫКАМИ ЛОКАЛИЗУЕМЫХ СУЩНОСТЕЙ
public class DtoToEntityConverter<E, D> {

    private static Locale defaultLocale = BookingSystemLocale.DEFAULT;
    private static Currency defaultCurrency = BookingSystemCurrency.DEFAULT;

    private static Long currentLanguageId = 2L;

    private Class<E> entityClass;
    private Class<D> dtoClass;

    public DtoToEntityConverter(Class<E> entityClass, Class<D> dtoClass) {
        this.entityClass = entityClass;
        this.dtoClass = dtoClass;
        SupportedLanguages supportedLanguages = SupportedLanguagesManager.get();
        String currentLanguage = UserPreferences.getLocale().getLanguage().toUpperCase();
        this.currentLanguageId = Integer.toUnsignedLong(supportedLanguages.indexOf(currentLanguage) + 1);
    }

    public DtoToEntityConverter(Class<E> entityClass, Class<D> dtoClass)

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

    private static RoomDto toRoomDto(Room room) throws LocalisationException {
        Long roomId = room.getRoomId();
        Hotel roomHotel = room.getHotel();
        HotelDto roomHotelDto = toHotelDto(roomHotel);
        RoomType roomType = room.getRoomType();
        RoomTypeDto roomTypeDto = toRoomTypeDto(roomType);
        Integer roomNumber = room.getRoomNumber();
        String roomStatus = room.getRoomStatus();
        return new RoomDto(roomId, roomHotelDto, roomTypeDto, roomNumber, roomStatus);
    }

    private static List<RoomDto> toRoomDtoList(Set<Room> roomSet) throws LocalisationException {
        List<RoomDto> roomDtoList = new ArrayList<>();
        for (Room room : roomSet) {
            RoomDto roomDto = toRoomDto(room);
            roomDtoList.add(roomDto);
        }
        return roomDtoList;
    }

    private static List<RoomDto> toRoomDtoList(List<Room> roomList) throws LocalisationException {
        List<RoomDto> roomDtoList = new ArrayList<>();
        for (Room room : roomList) {
            RoomDto roomDto = toRoomDto(room);
            roomDtoList.add(roomDto);
        }
        return roomDtoList;
    }


    private static Room toRoom(RoomDto roomDto) throws LocalisationException, DaoException {
        Long roomId = roomDto.getRoomId();
        Hotel hotel = toHotel(roomDto.getHotel());
        RoomType roomType = toRoomType(roomDto.getRoomType());
        Integer roomNumber = roomDto.getRoomNumber();
        String roomStatus = roomDto.getRoomStatus();
        return EntityBuilder.buildRoom(roomId, hotel, roomType, roomNumber, roomStatus);
    }

    private static List<Room> toRoomList(List<RoomDto> roomDtoList) throws LocalisationException, DaoException {
        List<Room> roomList = new ArrayList<>();
        for (RoomDto roomDto : roomDtoList) {
            Room room = toRoom(roomDto);
            roomList.add(room);
        }
        return roomList;
    }

    private static Set<Room> toRoomSet(List<RoomDto> roomDtoList) throws LocalisationException, DaoException {
        Set<Room> roomSet = new HashSet<>();
        for (RoomDto roomDto : roomDtoList) {
            Room room = toRoom(roomDto);
            roomSet.add(room);
        }
        return roomSet;
    }

    private static BillDto toBillDto(Bill bill) throws LocalisationException {
        Long billId = bill.getBillId();
        User client = bill.getClient();
        UserDto clientDto = toUserDto(client);
        Integer totalPersons = bill.getTotalPersons();
        String checkInDate = DateUtil.getFormattedDate(bill.getCheckInDate(), defaultLocale);
        String checkOutDate = DateUtil.getFormattedDate(bill.getCheckOutDate(), defaultLocale);
        Set<Room> roomSet = bill.getRoomSet();
        List<RoomDto> roomDtoList = toRoomDtoList(roomSet);
        Hotel hotel = roomSet.iterator().next().getHotel();
        HotelDto hotelDto = DtoToEntityConverter.toHotelDto(hotel);
        Double paymentAmountUSD = bill.getPaymentAmount();
        Double paymentAmount = CurrencyUtil.convertFromUSD(paymentAmountUSD, defaultCurrency);
        String billStatus = bill.getBillStatus();
        return new BillDto(billId, clientDto, totalPersons, checkInDate, checkOutDate, hotelDto,
                roomDtoList, paymentAmount, billStatus);
    }

    private static List<BillDto> toBillDtoList(Set<Bill> billSet) throws LocalisationException {
        List<BillDto> billDtoList = new ArrayList<>();
        for (Bill bill : billSet) {
            BillDto billDto = toBillDto(bill);
            billDtoList.add(billDto);
        }
        return billDtoList;
    }

    private static List<BillDto> toBillDtoList(List<Bill> billList) throws LocalisationException {
        List<BillDto> billDtoList = new ArrayList<>();
        for (Bill bill : billList) {
            BillDto billDto = toBillDto(bill);
            billDtoList.add(billDto);
        }
        return billDtoList;
    }

    private static Bill toBill(BillDto billDto) throws LocalisationException, DaoException {
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
        return EntityBuilder.buildBill(billId, client, totalPersons, checkInDate, checkOutDate,
               roomSet, paymentAmountUSD, billStatus);
    }

    private static List<Bill> toBillList(List<BillDto> billDtoList) throws DaoException, LocalisationException {
        return null;
    }
    
    private static Set<Bill> toBillSet(List<BillDto> billDtoList) throws DaoException, LocalisationException {
        return null;
    }

    private static RoomTypeDto toRoomTypeDto(RoomType roomType) {
        Long roomTypeId = roomType.getRoomTypeId();
        RoomTypeTranslation roomTypeTranslation = roomType.getRoomTypeTranslationMap().get(currentLanguageId);
        String roomTypeName = roomTypeTranslation.getRoomTypeName();
        Integer maxPersons = roomType.getMaxPersons();
        Double pricePerNightUSD = roomType.getPricePerNight();
        Double pricePerNight = CurrencyUtil.convertFromUSD(pricePerNightUSD, defaultCurrency);
        Set<Facility> facilitySet = roomType.getFacilitySet();
        StringBuilder facilities = new StringBuilder();
        for (Facility facility : facilitySet) {
            FacilityTranslation facilityTranslation = facility.getFacilityTranslationMap().get(currentLanguageId);
            String facilityName = facilityTranslation.getFacilityName();
            facilities.append(facilityName);
        }
        String roomTypeStatus = roomType.getRoomTypeStatus();
        return new RoomTypeDto(roomTypeId, roomTypeName, maxPersons,
                pricePerNight, facilities.toString(), roomTypeStatus);
    }

    private static List<RoomTypeDto> toRoomTypeDtoList(List<RoomType> roomTypeList) {
        List<RoomTypeDto> roomTypeDtoList = new ArrayList<>();
        for (RoomType roomType : roomTypeList) {
            RoomTypeDto roomTypeDto = toRoomTypeDto(roomType);
            roomTypeDtoList.add(roomTypeDto);
        }
        return roomTypeDtoList;
    }

    private static RoomType toRoomType(RoomTypeDto roomTypeDto) throws DaoException {
        Long roomTypeId = roomTypeDto.getRoomTypeId();
        String roomTypeName = roomTypeDto.getRoomTypeName();
        Integer maxPersons = roomTypeDto.getMaxPersons();
        Double pricePerNight = roomTypeDto.getPricePerNight();
        Double pricePerNightUSD = CurrencyUtil.convertToUSD(pricePerNight, defaultCurrency);
        Set<Facility> facilitySet = toFacilitySet(roomTypeId);
        String roomTypeStatus = roomTypeDto.getRoomTypeStatus();
        return EntityBuilder.buildRoomType(roomTypeId, roomTypeName, maxPersons, pricePerNightUSD,
                facilitySet, roomTypeStatus);
    }

    private static Set<Facility> toFacilitySet(Long roomTypeId) throws DaoException {
        RoomType roomType = RoomTypeDao.getInstance().getById(roomTypeId);
        return roomType.getFacilitySet();
    }

    private static List<RoomType> toRoomTypeList(List<RoomTypeDto> roomTypeDtoList) throws DaoException {
        List<RoomType> roomTypeList = new ArrayList<>();
        for (RoomTypeDto roomTypeDto : roomTypeDtoList) {
            RoomType roomType = toRoomType(roomTypeDto);
            roomTypeList.add(roomType);
        }
        return roomTypeList;
    }

    private static Set<RoomType> toRoomTypeSet(List<RoomTypeDto> roomTypeDtoList) throws DaoException {
        Set<RoomType> roomTypeSet = new HashSet<>();
        for (RoomTypeDto roomTypeDto : roomTypeDtoList) {
            RoomType roomType = toRoomType(roomTypeDto);
            roomTypeSet.add(roomType);
        }
        return roomTypeSet;
    }

    private static LocationDto toLocationDto(Location location) {
        Long locationId = location.getLocationId();
        LocationTranslation locationTranslation = location.getLocationTranslationMap().get(currentLanguageId);
        String country = locationTranslation.getCountry();
        String city = locationTranslation.getCity();
        String locationStatus = location.getLocationStatus();
        return new LocationDto(locationId, country, city, locationStatus);
    }

    private static Location toLocation(LocationDto locationDto) throws DaoException {
        Long locationId = locationDto.getLocationId();
        String country = locationDto.getCountry();
        String city = locationDto.getCity();
        String locationStatus = locationDto.getLocationStatus();
        return EntityBuilder.buildLocation(locationId, country, city, locationStatus);
    }

    private static List<LocationDto> toLocationDtoList(List<Location> locationList) {
        List<LocationDto> locationDtoList = new ArrayList<>();
        for (Location location : locationList) {
            LocationDto locationDto = DtoToEntityConverter.toLocationDto(location);
            locationDtoList.add(locationDto);
        }
        return locationDtoList;
    }

    private static List<Location> toLocationList(List<LocationDto> locationDtoList) throws DaoException {
        List<Location> locationList = new ArrayList<>();
        for (LocationDto locationDto : locationDtoList) {
            Location location = DtoToEntityConverter.toLocation(locationDto);
            locationList.add(location);
        }
        return locationList;
    }

    private static Set<Location> toLocationSet(List<LocationDto> locationDtoList) throws DaoException {
        Set<Location> locationSet = new HashSet<>();
        for (LocationDto locationDto : locationDtoList) {
            Location location = DtoToEntityConverter.toLocation(locationDto);
            locationSet.add(location);
        }
        return locationSet;
    }

    private static HotelDto toHotelDto(Hotel hotel) {
        Long hotelId = hotel.getHotelId();
        Location location = hotel.getLocation();
        LocationDto locationDto = toLocationDto(location);
        HotelTranslation hotelTranslation = hotel.getHotelTranslationMap().get(currentLanguageId);
        String hotelName = hotelTranslation.getHotelName();
        String hotelStatus = hotel.getHotelStatus();
        return new HotelDto(hotelId, locationDto, hotelName, hotelStatus);
    }

    private static List<HotelDto> toHotelDtoList(List<Hotel> hotelList) {
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

//    private static UserHotelDto toUserHotelDto(Hotel hotel, Set<Room> roomSet) throws LocalisationException {
//        HotelDto hotelDto = toHotelDto(hotel);
//        List<RoomDto> roomDtoSet = toRoomDtoList(roomSet);
//        return new UserHotelDto(hotelDto, roomDtoSet);
//    }

    private static UserHotelDto toUserHotelDto(Hotel hotel) throws LocalisationException {
        HotelDto hotelDto = toHotelDto(hotel);
        List<RoomDto> roomDtoSet = toRoomDtoList(hotel.getRoomSet());
        return new UserHotelDto(hotelDto, roomDtoSet);
    }

    private static List<UserHotelDto> toUserHotelDtoList(Set<Hotel> hotelSet) throws LocalisationException {
        List<UserHotelDto> userHotelDtoList = new ArrayList<>();
        for (Hotel hotel : hotelSet) {
            UserHotelDto userHotelDto = toUserHotelDto(hotel);
            userHotelDtoList.add(userHotelDto);
        }
        return userHotelDtoList;
    }

    private static List<UserHotelDto> toUserHotelDtoList(List<Hotel> hotelList) throws LocalisationException {
        List<UserHotelDto> userHotelDtoList = new ArrayList<>();
        for (Hotel hotel : hotelList) {
            UserHotelDto userHotelDto = toUserHotelDto(hotel);
            userHotelDtoList.add(userHotelDto);
        }
        return userHotelDtoList;
    }

    private static Hotel toHotel(UserHotelDto userHotelDto) throws DaoException {
        Long hotelId = userHotelDto.getHotelId();
        LocationDto locationDto = userHotelDto.getLocation();
        Location location = toLocation(locationDto);
        String hotelName = userHotelDto.getHotelName();
        String hotelStatus = userHotelDto.getHotelStatus();
        return EntityBuilder.buildHotel(hotelId, location, hotelName, hotelStatus);
    }

    private static List<Hotel> toHotelListFromUserHotelList(List<UserHotelDto> userHotelDtoList) throws DaoException {
        List<Hotel> hotelList = new ArrayList<>();
        for (UserHotelDto userHotelDto : userHotelDtoList) {
            Hotel hotel = toHotel(userHotelDto);
            hotelList.add(hotel);
        }
        return hotelList;
    }

    private static Set<Hotel> toHotelSetFromUserHotelList(List<UserHotelDto> userHotelDtoList) throws DaoException {
        Set<Hotel> hotelList = new HashSet<>();
        for (UserHotelDto userHotelDto : userHotelDtoList) {
            Hotel hotel = toHotel(userHotelDto);
            hotelList.add(hotel);
        }
        return hotelList;
    }

    private static Hotel toHotel(HotelDto hotelDto) throws DaoException {
        Long hotelId = hotelDto.getHotelId();
        LocationDto locationDto = hotelDto.getLocation();
        Location location = toLocation(locationDto);
        String hotelName = hotelDto.getHotelName();
        String hotelStatus = hotelDto.getHotelStatus();
        return EntityBuilder.buildHotel(hotelId, location, hotelName, hotelStatus);
    }

    private static List<Hotel> toHotelList(List<HotelDto> hotelDtoList) throws DaoException {
        List<Hotel> hotelList = new ArrayList<>();
        for (HotelDto hotelDto : hotelDtoList) {
            Hotel hotel = toHotel(hotelDto);
            hotelList.add(hotel);
        }
        return hotelList;
    }

    private static Set<Hotel> toHotelSet(List<HotelDto> hotelDtoList) throws DaoException {
        Set<Hotel> hotelSet = new HashSet<>();
        for (HotelDto hotelDto : hotelDtoList) {
            Hotel hotel = toHotel(hotelDto);
            hotelSet.add(hotel);
        }
        return hotelSet;
    }

    private static UserDto toUserDto(User user) {
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

    private static List<UserDto> toUserDtoList(List<User> userList) {
        List<UserDto> userDtoList = new ArrayList<>();
        for (User user : userList) {
            UserDto userDto = toUserDto(user);
            userDtoList.add(userDto);
        }
        return userDtoList;
    }

    private static User toUser(UserDto userDto) throws DaoException {
        Long userId = userDto.getUserId();
        String firstName = userDto.getFirstName();
        String lastName = userDto.getLastName();
        String email = userDto.getEmail();
        String login = userDto.getLogin();
        String password = userDto.getPassword();
        String role = userDto.getRole();
        String userStatus = userDto.getUserStatus();
        return EntityBuilder.buildUser(userId, firstName, lastName, email, login, password, role, userStatus);
    }

    private static List<User> toUserList(List<UserDto> userDtoList) throws DaoException {
        List<User> userList = new ArrayList<>();
        for (UserDto userDto : userDtoList) {
            User user = toUser(userDto);
            userList.add(user);
        }
        return userList;
    }

    private static Set<User> toUserSet(List<UserDto> userDtoList) throws DaoException {
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
