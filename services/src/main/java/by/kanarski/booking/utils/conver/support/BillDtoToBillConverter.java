package by.kanarski.booking.utils.conver.support;

import by.kanarski.booking.constants.SystemCurrency;
import by.kanarski.booking.dto.BillDto;
import by.kanarski.booking.dto.RoomDto;
import by.kanarski.booking.dto.UserDto;
import by.kanarski.booking.entities.Bill;
import by.kanarski.booking.entities.Room;
import by.kanarski.booking.entities.State;
import by.kanarski.booking.entities.User;
import by.kanarski.booking.utils.CurrencyUtil;
import by.kanarski.booking.utils.DateUtil;
import by.kanarski.booking.utils.EntityBuilder;
import by.kanarski.booking.utils.conver.service.IEntityConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.converter.Converter;

import java.util.List;
import java.util.Set;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

public class BillDtoToBillConverter implements Converter<BillDto, Bill> {

    @Autowired
    private EntityBuilder entityBuilder;
    @Autowired
    private ApplicationContext applicationContext;
    private IEntityConversionService conversionService = applicationContext.getBean(IEntityConversionService.class);

    @Override
    public Bill convert(BillDto billDto) {
        Long billId = billDto.getBillId();
        UserDto userDto = billDto.getClient();
        User user = conversionService.convert(userDto, User.class);
        Integer totalPersons = billDto.getTotalPersons();
        Long bookingDate = DateUtil.parseDate(billDto.getBookingDate());
        Long checkInDate = DateUtil.parseDate(billDto.getCheckInDate());
        Long checkOutDate = DateUtil.parseDate(billDto.getCheckOutDate());
        List<RoomDto> roomDtoList = billDto.getRoomList();
        Set<Room> roomSet = conversionService.convertListToSet(roomDtoList, Room.class);
        Double paymentAmount = billDto.getPaymentAmount();
        Double paymentAmountUSD = CurrencyUtil.convertToUSD(paymentAmount, SystemCurrency.DEFAULT);
        State paymentStatus = conversionService.convert(billDto.getPaymentStatus(), State.class);
        State billStatus = conversionService.convert(billDto.getBillStatus(), State.class);
        return entityBuilder.buildBill(billId, user, bookingDate, totalPersons, checkInDate, checkOutDate,
                roomSet, paymentAmountUSD, paymentStatus, billStatus);
    }
}
