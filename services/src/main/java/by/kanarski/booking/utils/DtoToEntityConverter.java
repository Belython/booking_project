package by.kanarski.booking.utils;

import by.kanarski.booking.constants.DtoName;
import by.kanarski.booking.constants.FieldValue;
import by.kanarski.booking.constants.SystemCurrency;
import by.kanarski.booking.constants.SystemLocale;
import by.kanarski.booking.dto.BillDto;
import by.kanarski.booking.dto.RoomDto;
import by.kanarski.booking.dto.UserDto;
import by.kanarski.booking.dto.facility.FacilityDto;
import by.kanarski.booking.dto.hotel.HotelDto;
import by.kanarski.booking.dto.hotel.UserHotelDto;
import by.kanarski.booking.dto.location.LocationDto;
import by.kanarski.booking.dto.roomType.RoomTypeDto;
import by.kanarski.booking.entities.Bill;
import by.kanarski.booking.entities.Room;
import by.kanarski.booking.entities.State;
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
import by.kanarski.booking.utils.threadLocal.UserPreferences;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class DtoToEntityConverter<E, D> {

    private static Locale defaultLocale = SystemLocale.DEFAULT;
    private static Currency defaultCurrency = SystemCurrency.DEFAULT;


    private static EntityBuilder entityBuilder = ContextHolder.getServiceContext().getBean(EntityBuilder.class);
    private static SystemLanguagesManager systemLanguagesManager = ContextHolder.getServiceContext()
            .getBean(SystemLanguagesManager.class);

    private String language;
    private Long languageId;
    private Class<E> entityClass;
    private Class<D> dtoClass;
    private boolean isCustomLanguage;

    public DtoToEntityConverter(Class<E> entityClass, Class<D> dtoClass) {
        this.entityClass = entityClass;
        this.dtoClass = dtoClass;
        this.isCustomLanguage = false;
    }

    private void updateLanguage() throws DaoException {
        if (!isCustomLanguage) {
            this.languageId = systemLanguagesManager.getLanguageId(UserPreferences.getLocale().getLanguage());
        } else {
            this.languageId = systemLanguagesManager.getLanguageId(language);
        }
    }

    private void setCustomLanguage(String language) {
        this.language = language;
        this.isCustomLanguage = true;
    }

    @SuppressWarnings("unchecked")
    public E toEntity(D dto) throws LocalisationException, DaoException {
        updateLanguage();
        if (dto == null) {
            return null;
        }
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
            case DtoName.FACILITY_DTO: {
                entity = toFacility((FacilityDto) dto);
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

    public E safeToEntity(D dto) throws LocalisationException, DaoException {
        updateLanguage();
        if (dto == null) {
            return null;
        }
        String dtoName = dtoClass.getSimpleName();
        Object entity = null;
        switch (dtoName) {
            case DtoName.ROOM_TYPE_DTO: {
                entity = safeToRoomType((RoomTypeDto) dto);
                break;
            }
        }
        return (E) entity;
    }

    public E toEntity(D dto, String language) throws LocalisationException, DaoException {
        setCustomLanguage(language);
        return toEntity(dto);
    }

    @SuppressWarnings("unchecked")
    public D toDto(E entity) throws LocalisationException, DaoException {
        updateLanguage();
        if (entity == null) {
            return null;
        }
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
            case DtoName.FACILITY_DTO: {
                dto = toFacilityDto((Facility) entity);
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

    public D toDto(E entity, String language) throws LocalisationException, DaoException {
        setCustomLanguage(language);
        return toDto(entity);
    }

        @SuppressWarnings("unchecked")
    public List<E> toEntityList(List<D> dtoList) throws LocalisationException, DaoException {
        updateLanguage();
        if (dtoList == null || dtoList.size() == 0) {
            return null;
        }
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
            case DtoName.FACILITY_DTO: {
                entityList = toFacilityList((List<FacilityDto>) dtoList);
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

    public List<E> toEntityList(List<D> dtoList, String language) throws LocalisationException, DaoException {
        setCustomLanguage(language);
        return toEntityList(dtoList);
    }


        @SuppressWarnings("unchecked")
    public List<D> toDtoList(List<E> entityList) throws LocalisationException, DaoException {
        updateLanguage();
        if (entityList == null || entityList.size() == 0) {
            return null;
        }
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
            case DtoName.FACILITY_DTO: {
                dtoList = toFacilityDtoList((List<Facility>) entityList);
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

    public List<D> toDtoList(List<E> entityList, String language) throws LocalisationException, DaoException {
        setCustomLanguage(language);
        return toDtoList(entityList);
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

    private RoomDto toRoomDto(Room room) throws LocalisationException {
        Long roomId = room.getRoomId();
        Hotel roomHotel = room.getHotel();
        HotelDto roomHotelDto = toHotelDto(roomHotel);
        RoomType roomType = room.getRoomType();
        RoomTypeDto roomTypeDto = toRoomTypeDto(roomType);
        Integer roomNumber = room.getRoomNumber();
        String roomStatus = toStateDto(room.getRoomStatus());
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
        User client = bill.getUser();
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
        String paymentStatus = toStateDto(bill.getPaymentStatus());
        String billStatus = toStateDto(bill.getBillStatus());
        return new BillDto(billId, clientDto, totalPersons, checkInDate, checkOutDate, hotelDto,
                roomDtoList, paymentAmount, paymentStatus, billStatus);
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
        Long bookingDate = DateUtil.parseDate(billDto.getBookingDate());
        Long checkInDate = DateUtil.parseDate(billDto.getCheckInDate());
        Long checkOutDate = DateUtil.parseDate(billDto.getCheckOutDate());
        List<RoomDto> roomDtoList = billDto.getRoomList();
        Set<Room> roomSet = toRoomSet(roomDtoList);
        Double paymentAmount = billDto.getPaymentAmount();
        Double paymentAmountUSD = CurrencyUtil.convertToUSD(paymentAmount, defaultCurrency);
        String billStatus = billDto.getBillStatus();
        return entityBuilder.buildBill(billId, client, bookingDate, totalPersons, checkInDate, checkOutDate,
               roomSet, paymentAmountUSD, billStatus);
    }

    private List<Bill> toBillList(List<BillDto> billDtoList) throws DaoException, LocalisationException {
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
        List<FacilityDto> facilityDtoList = toFacilityDtoList(facilitySet);
        String roomTypeStatus = toStateDto(roomType.getRoomTypeStatus());
        return new RoomTypeDto(roomTypeId, roomTypeName, maxPersons,
                pricePerNight, facilityDtoList, roomTypeStatus);
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
        List<FacilityDto> facilityDtoList = roomTypeDto.getFacilityList();
        Set<Facility> facilitySet = toFacilitySet(facilityDtoList);
        String roomTypeStatus = roomTypeDto.getRoomTypeStatus();
        return entityBuilder.buildRoomType(roomTypeId, roomTypeName, maxPersons, pricePerNightUSD,
                facilitySet, roomTypeStatus, languageId);
    }

    private RoomType safeToRoomType(RoomTypeDto roomTypeDto) throws DaoException {
        Long roomTypeId = roomTypeDto.getRoomTypeId();
        String roomTypeName = roomTypeDto.getRoomTypeName();
        roomTypeName = (StringUtils.isEmpty(roomTypeName)) ? FieldValue.ANY_ROOM_TYPE : roomTypeName;
        Integer maxPersons = roomTypeDto.getMaxPersons();
        Double pricePerNight = roomTypeDto.getPricePerNight();
        Double pricePerNightUSD = null;
        if (pricePerNight != null) {
            pricePerNightUSD = CurrencyUtil.convertToUSD(pricePerNight, SystemCurrency.DEFAULT);
        }
        List<FacilityDto> facilityDtoList = roomTypeDto.getFacilityList();
        Set<Facility> facilitySet = null;
        if (CollectionUtils.isNotEmpty(facilityDtoList)) {
            facilitySet = toFacilitySet(facilityDtoList);
        }
        String roomTypeStatus = roomTypeDto.getRoomTypeStatus();
        return entityBuilder.buildRoomType(roomTypeId, roomTypeName, maxPersons, pricePerNightUSD,
                facilitySet, roomTypeStatus, languageId);
    }

    private Set<Facility> toFacilitySet(List<FacilityDto> facilityDtoList) throws DaoException {
        Set<Facility> facilitySet = new HashSet<>();
        for (FacilityDto facilityDto : facilityDtoList) {
            String facilityName = facilityDto.getFacilityName();
            Facility facility = entityBuilder.buildFacility(facilityName, languageId);
            facilitySet.add(facility);
        }
        return facilitySet;
    }

    private List<RoomType> toRoomTypeList(List<RoomTypeDto> roomTypeDtoList) throws DaoException {
        List<RoomType> roomTypeList = new ArrayList<>();
        for (RoomTypeDto roomTypeDto : roomTypeDtoList) {
            RoomType roomType = toRoomType(roomTypeDto);
            roomTypeList.add(roomType);
        }
        return roomTypeList;
    }

    private FacilityDto toFacilityDto(Facility facility) {
        Long facilityId = facility.getFacilityId();
        FacilityTranslation facilityTranslation = facility.getFacilityTranslationMap().get(languageId);
        String facilityName = facilityTranslation.getFacilityName();
        String facilityStatus = toStateDto(facility.getFacilityStatus());
        return new FacilityDto(facilityId, facilityName, facilityStatus);
    }

    private Facility toFacility(FacilityDto facilityDto) throws DaoException {
        Long facilityId = facilityDto.getFacilityId();
        String facilityName = facilityDto.getFacilityName();
        String facilityStatus = facilityDto.getFacilityStatus();
        Long languageId = systemLanguagesManager.getLanguageId(language);
        return entityBuilder.buildFacility(facilityId, facilityName, facilityStatus, languageId);
    }

    private List<FacilityDto> toFacilityDtoList(Collection<Facility> facilityList) {
        List<FacilityDto> facilityDtoList = new ArrayList<>();
        for (Facility facility : facilityList) {
            FacilityDto facilityDto = toFacilityDto(facility);
            facilityDtoList.add(facilityDto);
        }
        return facilityDtoList;
    }

    private List<Facility> toFacilityList(List<FacilityDto> facilityDtoList) throws DaoException {
        List<Facility> facilityList = new ArrayList<>();
        for (FacilityDto facilityDto : facilityDtoList) {
            Facility facility = toFacility(facilityDto);
            facilityList.add(facility);
        }
        return facilityList;
    }
    private LocationDto toLocationDto(Location location) {
        Long locationId = location.getLocationId();
        LocationTranslation locationTranslation = location.getLocationTranslationMap().get(languageId);
        String country = locationTranslation.getCountry();
        String city = locationTranslation.getCity();
        String locationStatus = toStateDto(location.getLocationStatus());
        return new LocationDto(locationId, country, city, locationStatus);
    }

    private Location toLocation(LocationDto locationDto) throws DaoException {
        Long locationId = locationDto.getLocationId();
        String country = locationDto.getCountry();
        String city = locationDto.getCity();
        String locationStatus = locationDto.getLocationStatus();
        String language = locationDto.getLanguage();
        Long languageId = systemLanguagesManager.getLanguageId(language);
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

    private HotelDto toHotelDto(Hotel hotel) {
        Long hotelId = hotel.getHotelId();
        Location location = hotel.getLocation();
        LocationDto locationDto = toLocationDto(location);
        HotelTranslation hotelTranslation = hotel.getHotelTranslationMap().get(languageId);
        String hotelName = hotelTranslation.getHotelName();
        String hotelStatus = toStateDto(hotel.getHotelStatus());
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
        Long languageId = systemLanguagesManager.getLanguageId(language);
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

    private Hotel toHotel(HotelDto hotelDto) throws DaoException {
        Long hotelId = hotelDto.getHotelId();
        LocationDto locationDto = hotelDto.getLocation();
        Location location = toLocation(locationDto);
        String hotelName = hotelDto.getHotelName();
        String hotelStatus = hotelDto.getHotelStatus();
        String laguage = hotelDto.getLanguage();
        Long languageId = systemLanguagesManager.getLanguageId(laguage);
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

    private UserDto toUserDto(User user) {
        Long userId = user.getUserId();
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String email = user.getEmail();
        String login = user.getUserName();
        String password = user.getPassword();
        Set<String> roleSet = user.getRoleSet();
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
        Set<String> roleSet = userDto.getRoleSet();
        String userStatus = userDto.getUserStatus();
        return entityBuilder.buildUser(userId, firstName, lastName, email, login, password, roleSet, userStatus);
    }

    private List<User> toUserList(List<UserDto> userDtoList) throws DaoException {
        List<User> userList = new ArrayList<>();
        for (UserDto userDto : userDtoList) {
            User user = toUser(userDto);
            userList.add(user);
        }
        return userList;
    }

    private String toStateDto(State state) {
        return state.getStateName();
    }

    private State toState(String stateDto) {
        return new State(null, stateDto);
    }

    private Set<String> toStateDtoSet(Set<State> stateSet) {
        Set<String> stateDtoSet = new HashSet<>();
        for (State state : stateSet) {
            String stateDto = toStateDto(state);
            stateDtoSet.add(stateDto);
        }
        return stateDtoSet;
    }

    private Set<State> toStateSet(Set<String> stateDtoSet) {
        Set<State> stateSet = new HashSet<>();
        for (String stateDto : stateDtoSet) {
            State state = toState(stateDto);
            stateSet.add(state);
        }
        return stateSet;
    }
//    private Class getPersistentDtoClass() {
//        ParameterizedType classType = (ParameterizedType) getClass().getGenericSuperclass();
//        Class<D> persistentClass = (Class<D>) classType.getActualTypeArguments()[1];
//        return persistentClass;
//    }

}
