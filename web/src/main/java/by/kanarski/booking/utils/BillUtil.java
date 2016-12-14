package by.kanarski.booking.utils;

import by.kanarski.booking.dto.RoomDto;
import by.kanarski.booking.dto.roomType.RoomTypeDto;
import by.kanarski.booking.exceptions.LocalisationException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class BillUtil {

    private static final Integer MILISECONDS = 1000;
    private static final Integer SECONDS = 60;
    private static final Integer MINUTES = 60;
    private static final Integer HOURS = 24;
    private static final Integer MILLISECONDS_IN_DAY = MILISECONDS * SECONDS * MINUTES * HOURS;

    public static double getPaymentAmount(Integer bookedDays, List<RoomDto> roomDtoList) {
        Double payment = 0D;
        for (RoomDto roomDto : roomDtoList) {
            Double part = roomDto.getRoomType().getPricePerNight() * bookedDays;
            payment += part;
        }
        return payment;
    }

    public static int getBookedDays(String formattedCheckInDate, String formattedCheckOutDate)
            throws LocalisationException {
        Long checkInDate = DateUtil.parseDate(formattedCheckInDate);
        Long checkOutDate = DateUtil.parseDate(formattedCheckOutDate);
        Integer bookedDays = Math.round((checkOutDate - checkInDate) / MILLISECONDS_IN_DAY);
        return bookedDays;
    }

    public static List<RoomDto> chooseRoomsForBooking(HashMap<RoomTypeDto, Integer> selectedRoomTypes,
                                                      List<RoomDto> roomDtoList) {
        List<RoomDto> selectedRoomList = new ArrayList<>();
        Set<RoomTypeDto> roomTypeDtoSet = selectedRoomTypes.keySet();
        for (RoomTypeDto roomTypeDto : roomTypeDtoSet) {
            int roomsAmount = selectedRoomTypes.get(roomTypeDto);
            int i = 0;
            while (roomsAmount > 0) {
                RoomDto roomDto = roomDtoList.get(i);
                if (roomDto.getRoomType().equals(roomTypeDto)) {
                    selectedRoomList.add(roomDto);
                    roomsAmount--;
                }
                i++;
            }
        }
        return selectedRoomList;
    }

}
