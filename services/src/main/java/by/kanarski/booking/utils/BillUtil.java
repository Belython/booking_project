package by.kanarski.booking.utils;

import by.kanarski.booking.dto.RoomDto;
import by.kanarski.booking.dto.forms.BookRoomsForm;
import by.kanarski.booking.dto.roomType.OrderedRoomTypeDto;
import by.kanarski.booking.exceptions.LocalisationException;

import java.util.ArrayList;
import java.util.List;

public class BillUtil {

    private static final Integer MILISECONDS = 1000;
    private static final Integer SECONDS = 60;
    private static final Integer MINUTES = 60;
    private static final Integer HOURS = 24;
    private static final Integer MILLISECONDS_IN_DAY = MILISECONDS * SECONDS * MINUTES * HOURS;

    public static Double getPaymentAmount(Integer bookedDays, List<RoomDto> roomDtoList) {
        Double payment = 0D;
        for (RoomDto roomDto : roomDtoList) {
            Double part = roomDto.getRoomType().getPricePerNight() * bookedDays;
            payment += part;
        }
        return payment;
    }

    public static Double getPaymentAmount(String formattedCheckInDate, String formattedCheckOutDate,
                                          List<RoomDto> roomDtoList) throws LocalisationException {
        Integer bookedDays = getBookedDays(formattedCheckInDate, formattedCheckOutDate);
        Double payment = 0D;
        for (RoomDto roomDto : roomDtoList) {
            Double part = roomDto.getRoomType().getPricePerNight() * bookedDays;
            payment += part;
        }
        return payment;
    }

    public static List<RoomDto> chooseRoomsForBooking(BookRoomsForm bookRoomsForm, List<RoomDto> roomDtoList) {
        List<RoomDto> orderedRoomList = new ArrayList<>();
        List<OrderedRoomTypeDto> orderedRoomTypeDtoList = bookRoomsForm.getOrderedRooms();
        for (OrderedRoomTypeDto orderedRoomTypeDto : orderedRoomTypeDtoList) {
            int roomsAmount = orderedRoomTypeDto.getAmount();
            int i = 0;
            while (roomsAmount > 0) {
                RoomDto roomDto = roomDtoList.get(i);
                Long roomTypeId = roomDto.getRoomType().getRoomTypeId();
                if (roomTypeId.equals(orderedRoomTypeDto.getRoomTypeId())) {
                    orderedRoomList.add(roomDto);
                    roomsAmount--;
                }
                i++;
            }
        }
        return orderedRoomList;
    }


    private static int getBookedDays(String formattedCheckInDate, String formattedCheckOutDate)
            throws LocalisationException {
        Long checkInDate = DateUtil.parseDate(formattedCheckInDate);
        Long checkOutDate = DateUtil.parseDate(formattedCheckOutDate);
        Integer bookedDays = Math.round((checkOutDate - checkInDate) / MILLISECONDS_IN_DAY);
        return bookedDays;
    }


}
