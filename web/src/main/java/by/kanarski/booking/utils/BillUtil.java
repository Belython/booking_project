package by.kanarski.booking.utils;

import by.kanarski.booking.dto.RoomDto;
import by.kanarski.booking.exceptions.LocalisationException;

import java.util.List;

public class BillUtil {

    public static double getPaymentAmount(int bookedDays, List<RoomDto> roomDtoList) {
        double payment = 0;
        for (RoomDto roomDto : roomDtoList) {
            double part = roomDto.getRoomType().getPricePerNight() * bookedDays;
            payment += part;
        }
        return payment;
    }

    public static int getBookedDays(String formattedCheckInDate, String formattedCheckOutDate)
            throws LocalisationException {
        final long MILLISECONDS_IN_DAY = 24 * 60 * 60 * 1000;
        long checkInDate = DateUtil.parseDate(formattedCheckInDate);
        long checkOutDate = DateUtil.parseDate(formattedCheckOutDate);
        int bookedDays = Math.round((checkOutDate - checkInDate) / MILLISECONDS_IN_DAY);
        return bookedDays;
    }

}
