package by.kanarski.booking.utils;

import by.kanarski.booking.constants.Parameter;
import by.kanarski.booking.dto.hotel.HotelDto;
import by.kanarski.booking.dto.location.LocationDto;
import by.kanarski.booking.dto.roomType.RoomTypeDto;
import by.kanarski.booking.utils.field.FieldBuilder;
import by.kanarski.booking.utils.field.FieldDescriptor;

import java.util.*;

// TODO: 09.09.2016 что-нибудь сделать с этим
//Очень кривая реализация, стыд

public class ConstrainUtil {

    public static FieldDescriptor<HotelDto> byHotel(HotelDto selectedHotelDto, List<HotelDto> hotelDtoList) {
        LocationDto selectedLocationDto = selectedHotelDto.getLocation();
//        String selectedHotelCountry = selectedLocationDto.getCountry();
//        String selectedHotelCity = selectedLocationDto.getCity();
        String selectedHotelName = selectedHotelDto.getHotelName();

        List<LocationDto> locationDtoList = new ArrayList<>();
        Set<String> hotelNameSet = new HashSet<>();
        HotelDto resHotelDto = null;

        for (HotelDto hotelDto : hotelDtoList) {
            long hotelId = hotelDto.getHotelId();
//            String hotelCountry = hotelDto.getLocation().getCountry();
//            String hotelCity = hotelDto.getLocation().getCity();
            LocationDto locationDto = hotelDto.getLocation();
            String hotelName = hotelDto.getHotelName();
            if (locationDto.equals(selectedLocationDto)) {
                if (hotelNameSet.isEmpty()) {
                    resHotelDto = hotelDto;
                }
                if (hotelName.equals(selectedHotelName)) {
                    selectedHotelDto.setHotelId(hotelId);
                }
                hotelNameSet.add(hotelName);
                
            }
            locationDtoList.add(locationDto);
        }
        if (!(hotelNameSet.contains(selectedHotelName)) && resHotelDto != null) {
            selectedHotelDto.setHotelName(resHotelDto.getHotelName());
            selectedHotelDto.setHotelId(resHotelDto.getHotelId());
        }

        FieldDescriptor<Long> hotelIdPrimitive = FieldBuilder.buildFreePrimitive();
        FieldDescriptor<LocationDto> locationEntity = byLocation(selectedLocationDto, locationDtoList);
        FieldDescriptor<String> hotelNamePrimitive = FieldBuilder.buildPrimitive(hotelNameSet);
        LinkedHashMap<String, FieldDescriptor> hotelFields = new LinkedHashMap<>();
        hotelFields.put(Parameter.HOTEL_ID, hotelIdPrimitive);
        hotelFields.put(Parameter.HOTEL_LOCATION, locationEntity);
        hotelFields.put(Parameter.HOTEL_NAME, hotelNamePrimitive);
        FieldDescriptor<HotelDto> hotelEntity = FieldBuilder.buildEntity(hotelFields, selectedHotelDto);

        hotelEntity.setOwner(selectedHotelDto);
        return hotelEntity;
    }

    public static FieldDescriptor<RoomTypeDto> byRoomType(RoomTypeDto selectedRoomTypeDto, List<RoomTypeDto> roomTypeDtoList) {
        String selectedRoomTypeName = selectedRoomTypeDto.getRoomTypeName();
        int selectedRoomTypemaxPersons = selectedRoomTypeDto.getMaxPersons();
        double selectedRoomTypePricePerNight = selectedRoomTypeDto.getPricePerNight();
        String selectedFacilities = selectedRoomTypeDto.getFacilities();

        Set<String> roomTypeDtoNameSet = new HashSet<>();
        Set<Integer> maxPersonsSet = new HashSet<>();
        Set<Double> pricePerNightSet = new HashSet<>();
        Set<String> facilitiesSet = new HashSet<>();
        RoomTypeDto resRoomTypeDto = null;

        for (RoomTypeDto roomTypeDto : roomTypeDtoList) {
            long roomTypeId = roomTypeDto.getRoomTypeId();
            String roomTypeName = roomTypeDto.getRoomTypeName();
            int maxPersons = roomTypeDto.getMaxPersons();
            double pricePerNight = roomTypeDto.getPricePerNight();
            String facilities = roomTypeDto.getFacilities();
            if (roomTypeName.equals(selectedRoomTypeName)) {
                maxPersonsSet.add(maxPersons);
                if (maxPersons == selectedRoomTypemaxPersons) {
                    pricePerNightSet.add(pricePerNight);
                    if (pricePerNight == selectedRoomTypePricePerNight) {
                        if (facilitiesSet.isEmpty()) {
                            resRoomTypeDto = roomTypeDto;
                        }
                        if (facilities.equals(selectedFacilities)) {
                            selectedRoomTypeDto.setRoomTypeId(roomTypeId);
                        }
                        facilitiesSet.add(facilities);
                    }
                }
            }
            roomTypeDtoNameSet.add(roomTypeName);
        }
        if ((!facilitiesSet.contains(selectedFacilities)) && (resRoomTypeDto != null)) {
            selectedRoomTypeDto.setFacilities(resRoomTypeDto.getFacilities());
            selectedRoomTypeDto.setRoomTypeId(resRoomTypeDto.getRoomTypeId());
        }

        LinkedHashMap<String, FieldDescriptor> roomTypeFields = new LinkedHashMap<>();
        FieldDescriptor<Long> roomTypeIdFieldDescriptor = FieldBuilder.buildFreePrimitive();
        FieldDescriptor<String> roomTypeNameFieldDescriptor = FieldBuilder.buildPrimitive(roomTypeDtoNameSet);
        FieldDescriptor<Integer> maxPersonsFieldDescriptor = FieldBuilder.buildPrimitive(maxPersonsSet);
        FieldDescriptor<Double> pricePerNightFieldDescriptor = FieldBuilder.buildPrimitive(pricePerNightSet);
        FieldDescriptor<String> facilitiesFieldDescriptor = FieldBuilder.buildPrimitive(facilitiesSet);
        roomTypeFields.put(Parameter.ROOM_TYPE_ID, roomTypeIdFieldDescriptor);
        roomTypeFields.put(Parameter.ROOM_TYPE_NAME, roomTypeNameFieldDescriptor);
        roomTypeFields.put(Parameter.ROOM_TYPE_MAX_PERSONS, maxPersonsFieldDescriptor);
        roomTypeFields.put(Parameter.ROOM_TYPE_PRICE_PER_NIGHT, pricePerNightFieldDescriptor);
        roomTypeFields.put(Parameter.ROOM_TYPE_FACILITIES, facilitiesFieldDescriptor);
        FieldDescriptor<RoomTypeDto> roomTypeEntity = FieldBuilder.buildEntity(roomTypeFields, selectedRoomTypeDto);

        if (pricePerNightSet.isEmpty()) {
            int maxPersons = maxPersonsSet.iterator().next();
            selectedRoomTypeDto.setMaxPersons(maxPersons);
            roomTypeEntity = byRoomType(selectedRoomTypeDto, roomTypeDtoList);
        }
        roomTypeFields = roomTypeEntity.getFieldMap();
        facilitiesFieldDescriptor = roomTypeFields.get(Parameter.ROOM_TYPE_FACILITIES);
        facilitiesSet = (Set<String>) facilitiesFieldDescriptor.getAllowedValues();
        if (facilitiesSet.isEmpty()) {
            double pricePerNight = pricePerNightSet.iterator().next();
            selectedRoomTypeDto.setPricePerNight(pricePerNight);
            roomTypeEntity = byRoomType(selectedRoomTypeDto, roomTypeDtoList);
        }

        roomTypeEntity.setOwner(selectedRoomTypeDto);
        return roomTypeEntity;
    }

    public static FieldDescriptor<LocationDto> byLocation(LocationDto selectedLocationDto, List<LocationDto> locationDtoList) {
        String selectedCountry = selectedLocationDto.getCountry();
        String selectedCity = selectedLocationDto.getCity();

        Set<String> countrySet = new HashSet<>();
        Set<String> citySet = new HashSet<>();
        LocationDto resLocationDto = null;

        for (LocationDto locationDto : locationDtoList) {
            long locationId = locationDto.getLocationId();
            String country = locationDto.getCountry();
            String city = locationDto.getCity();
            if (country.equals(selectedCountry)) {
                if (citySet.isEmpty()) {
                    resLocationDto = locationDto;
                }
                if (city.equals(selectedCity)) {
                    selectedLocationDto.setLocationId(locationId);
                }
                citySet.add(city);
            }
            countrySet.add(country);
        }
        if (!(citySet.contains(selectedCity)) && resLocationDto != null) {
            selectedLocationDto.setCity(resLocationDto.getCity());
            selectedLocationDto.setLocationId(resLocationDto.getLocationId());
        }

        FieldDescriptor<Long> locationIdPrimitive = FieldBuilder.buildFreePrimitive();
        FieldDescriptor<String> locationContryPrimitive = FieldBuilder.buildPrimitive(countrySet);
        FieldDescriptor<String> locationCityPrmitive = FieldBuilder.buildPrimitive(citySet);
        LinkedHashMap<String, FieldDescriptor> locationFields = new LinkedHashMap<>();
        locationFields.put(Parameter.LOCATION_ID, locationIdPrimitive);
        locationFields.put(Parameter.LOCATION_COUNTRY, locationContryPrimitive);
        locationFields.put(Parameter.LOCATION_CITY, locationCityPrmitive);
        FieldDescriptor<LocationDto> locationEntity = FieldBuilder.buildEntity(locationFields, selectedLocationDto);

        locationEntity.setOwner(selectedLocationDto);
        return locationEntity;
    }

}
