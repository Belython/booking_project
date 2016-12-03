package by.kanarski.booking.services.impl;

import by.kanarski.booking.constants.AliasName;
import by.kanarski.booking.constants.AliasValue;
import by.kanarski.booking.constants.SearchParameter;
import by.kanarski.booking.dao.impl.HotelDao;
import by.kanarski.booking.dao.impl.RoomDao;
import by.kanarski.booking.dto.OrderDto;
import by.kanarski.booking.dto.hotel.HotelDto;
import by.kanarski.booking.dto.hotel.UserHotelDto;
import by.kanarski.booking.entities.hotel.Hotel;
import by.kanarski.booking.exceptions.DaoException;
import by.kanarski.booking.exceptions.LocalisationException;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.services.interfaces.IUserHotelService;
import by.kanarski.booking.utils.DateUtil;
import by.kanarski.booking.utils.ExceptionHandler;
import by.kanarski.booking.utils.ServiceHelper;
import by.kanarski.booking.utils.hibernate.HibernateUtil;
import by.kanarski.booking.utils.transaction.TransactionManager;
import by.kanarski.booking.utils.transaction.TransactoinWrapper;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

import java.util.ArrayList;
import java.util.List;


public class UserHotelService extends ExtendedBaseService<Hotel, UserHotelDto> implements IUserHotelService {

    private static UserHotelService instance;
    private static HotelDao hotelDao = HotelDao.getInstance();
    private static RoomDao roomDao = RoomDao.getInstance();

    private UserHotelService() {
    }

    public static synchronized UserHotelService getInstance() {
        if (instance == null) {
            instance = new UserHotelService();
        }
        return instance;
    }

//    @Override
//    public UserHotelDto getByHotelName(String hotelName) throws ServiceException {
//        TransactoinWrapper transaction = TransactionManager.getTransaction();
//        UserHotelDto userHotelDto = null;
//        SearchFilter hotelFilter = SearchFilter.createBasicEqFilter("hotelName", hotelName);
//        hotelFilter.addEqFilter("language", "EN");
//        try {
//            transaction.begin();
//            HotelTranslation hotelTranslation = HotelTranslationDao.getInstance().getUniqueByFilter(hotelFilter);
//            Hotel hotel = hotelTranslation.getHotel();
//            userHotelDto = converter.toDto(hotel);
//            transaction.rollback();
//        } catch (DaoException e) {
//            ExceptionHandler.handleDaoException(transaction, e);
//        } catch (LocalisationException e) {
//            ExceptionHandler.handleLocalizationException(e);
//        }
//        return userHotelDto;
//    }
//
//    @Override
//    public List<UserHotelDto> getByCountry(String country, int page, int perPage) throws ServiceException {
//        TransactoinWrapper transaction = TransactionManager.getTransaction();
//        List<UserHotelDto> userHotelDtoList = null;
//        SearchFilter locationFilter = SearchFilter.createBasicEqFilter("country", country);
//        locationFilter.addEqFilter("language", "EN");
//        try {
//            transaction.begin();
//            country = LocationTranslationDao.getInstance().getListByFilter(locationFilter, 0, 1).get(0).getCountry();
//            SearchFilter hotelFilter = SearchFilter.createBasicEqFilter("location.country", country);
//            List<Hotel> hotelList = hotelDao.getListByFilter(hotelFilter, page, perPage);
//            userHotelDtoList = converter.toDtoList(hotelList);
//            transaction.commit();
//        } catch (DaoException e) {
//            ExceptionHandler.handleDaoException(transaction, e);
//        } catch (LocalisationException e) {
//            ExceptionHandler.handleLocalizationException(e);
//        }
//        return userHotelDtoList;
//    }
//
//    @Override
//    public List<UserHotelDto> getByCity(String city, int page, int perPage) throws ServiceException {
//        TransactoinWrapper transaction = TransactionManager.getTransaction();
//        List<UserHotelDto> userHotelDtoList = null;
//        SearchFilter locationFilter = SearchFilter.createBasicEqFilter("country", city);
//        locationFilter.addEqFilter("language", "EN");
//        try {
//            transaction.begin();
//            city = LocationTranslationDao.getInstance().getListByFilter(locationFilter, 0, 1).get(0).getCity();
//            SearchFilter hotelFilter = SearchFilter.createBasicEqFilter("location.city", city);
//            List<Hotel> hotelList = hotelDao.getListByFilter(hotelFilter, page, perPage);
//            userHotelDtoList = converter.toDtoList(hotelList);
//            transaction.commit();
//        } catch (DaoException e) {
//            ExceptionHandler.handleDaoException(transaction, e);
//        } catch (LocalisationException e) {
//            ExceptionHandler.handleLocalizationException(e);
//        }
//        return userHotelDtoList;
//    }

    @Override
    public List<UserHotelDto> getListByOrder(OrderDto orderDto, int page, int perPage) throws ServiceException {
        TransactoinWrapper transaction = TransactionManager.getTransaction();
        List<UserHotelDto> userHotelDtoList =  new ArrayList<>();
        HotelDto hotelDto = orderDto.getHotel();
        String language = orderDto.getLanguage();
        Long hotelId = hotelDto.getHotelId();
        String localizedCountry = hotelDto.getLocation().getCountry();
        String localizedCity = hotelDto.getLocation().getCity();
        Criteria criteria = HibernateUtil.getSession().createCriteria(Hotel.class);
        criteria
                .createAlias(AliasName.LOCATION, AliasValue.LOCATION)
                .createAlias(AliasName.ROOMSET, AliasValue.ROOMSET)
                .createAlias(AliasName.ROOMSET_ROOMTYPE, AliasValue.ROOMTYPE, JoinType.LEFT_OUTER_JOIN)
                .createAlias(AliasName.ROOMSET_BILLSET, AliasValue.BILLSET, JoinType.LEFT_OUTER_JOIN)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        try {
            transaction.begin();
            if (hotelId != null) {
                criteria.add(Restrictions.eq(SearchParameter.HOTELID, hotelId));
            } else {
                if (StringUtils.isNotBlank(localizedCountry)) {
                    String country = ServiceHelper.getNotLocalizedCountry(localizedCountry, language);
                    criteria.add(Restrictions.eq(SearchParameter.LOCATION_COUNTRY, country));
                    if (StringUtils.isNotBlank(localizedCity)) {
                        String city = ServiceHelper.getNotLocalizedCity(localizedCity, language);
                        criteria.add(Restrictions.eq(SearchParameter.LOCATION_CITY, city));
                    }
                }
            }
            Integer totalPersons = orderDto.getTotalPersons();
            Integer totalRooms = orderDto.getTotalRooms();
            Integer maxPersons = Math.round(totalPersons / totalRooms);
            maxPersons = (maxPersons <= 0) ? 1 : maxPersons;
            criteria.add(Restrictions.ge(SearchParameter.ROOMTYPE_MAXPERSONS, maxPersons));
            Long checkInDate = DateUtil.parseDate(orderDto.getCheckInDate());
            Long checkOutDate = DateUtil.parseDate(orderDto.getCheckOutDate());
            Conjunction condition1 = Restrictions.conjunction();
            condition1
                    .add(Restrictions.lt(SearchParameter.BILLSET_CHECKINDATE, checkInDate))
                    .add(Restrictions.ge(SearchParameter.BILLSET_CHECKOUTDATE, checkOutDate));
            Conjunction condition2 = Restrictions.conjunction();
            condition2
                    .add(Restrictions.gt(SearchParameter.BILLSET_CHECKOUTDATE, checkInDate))
                    .add(Restrictions.lt(SearchParameter.BILLSET_CHECKINDATE, checkOutDate));
            Conjunction condition3 = Restrictions.conjunction();
            condition3
                    .add(condition1)
                    .add(condition2);
            Conjunction condition4 = Restrictions.conjunction();
            condition4
                    .add(Restrictions.gt(SearchParameter.BILLSET_CHECKOUTDATE, checkInDate))
                    .add(Restrictions.between(SearchParameter.BILLSET_CHECKINDATE, checkInDate, checkOutDate));
            Disjunction disjunction = Restrictions.disjunction();
            disjunction
                    .add(condition3)
                    .add(condition4);

            List<Hotel> hotelList = hotelDao.getListByCriteria(criteria, page, perPage);
            userHotelDtoList = converter.toDtoList(hotelList);
            transaction.commit();
        } catch (DaoException e) {
            ExceptionHandler.handleDaoException(transaction, e);
        } catch (LocalisationException e) {
            ExceptionHandler.handleLocalizationException(e);
        }
        return userHotelDtoList;
    }

    @Override
    public UserHotelDto getByOrder(OrderDto orderDto) throws ServiceException {
        TransactoinWrapper transaction = TransactionManager.getTransaction();
        UserHotelDto userHotelDto = null;
        try {
            transaction.begin();
            Hotel hotel = HotelDao.getInstance().getById(orderDto.getHotel().getHotelId());
            userHotelDto = converter.toDto(hotel);
            transaction.commit();
        } catch (DaoException e) {
            ExceptionHandler.handleDaoException(transaction, e);
        } catch (LocalisationException e) {
            ExceptionHandler.handleLocalizationException(e);
        }
        return userHotelDto;
    }



}
