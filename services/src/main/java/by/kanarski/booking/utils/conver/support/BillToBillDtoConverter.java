package by.kanarski.booking.utils.conver.support;

import by.kanarski.booking.constants.SystemCurrency;
import by.kanarski.booking.constants.SystemLocale;
import by.kanarski.booking.dto.BillDto;
import by.kanarski.booking.dto.RoomDto;
import by.kanarski.booking.dto.UserDto;
import by.kanarski.booking.dto.hotel.HotelDto;
import by.kanarski.booking.entities.Bill;
import by.kanarski.booking.entities.Room;
import by.kanarski.booking.entities.User;
import by.kanarski.booking.entities.hotel.Hotel;
import by.kanarski.booking.utils.CurrencyUtil;
import by.kanarski.booking.utils.DateUtil;
import org.springframework.core.convert.converter.Converter;

import java.util.List;
import java.util.Set;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

public class BillToBillDtoConverter extends EntityConverter implements Converter<Bill, BillDto> {

    @Override
    public BillDto convert(Bill bill) {
        Long billId = bill.getBillId();
        User user = bill.getUser();
        UserDto clientDto = getConversionService().convert(user, UserDto.class);
        Integer totalPersons = bill.getTotalPersons();
        String checkInDate = DateUtil.getFormattedDate(bill.getCheckInDate(), SystemLocale.DEFAULT);
        String checkOutDate = DateUtil.getFormattedDate(bill.getCheckOutDate(), SystemLocale.DEFAULT);
        Set<Room> roomSet = bill.getRoomSet();
        List<RoomDto> roomDtoList = getConversionService().convertSetToList(roomSet, RoomDto.class);
        Hotel hotel = roomSet.iterator().next().getHotel();
        HotelDto hotelDto = getConversionService().convert(hotel, HotelDto.class);
        Double paymentAmountUSD = bill.getPaymentAmount();
        Double paymentAmount = CurrencyUtil.convertFromUSD(paymentAmountUSD, SystemCurrency.DEFAULT);
        String paymentStatus = getConversionService().convert(bill.getPaymentStatus(), String.class);
        String billStatus = getConversionService().convert(bill.getStatus(), String.class);
        return new BillDto(billId, clientDto, totalPersons, checkInDate, checkOutDate, hotelDto,
                roomDtoList, paymentAmount, paymentStatus, billStatus);
    }
}
