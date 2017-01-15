package by.kanarski.booking.services.impl;

import by.kanarski.booking.constants.AliasName;
import by.kanarski.booking.constants.AliasValue;
import by.kanarski.booking.constants.FieldValue;
import by.kanarski.booking.constants.SearchParameter;
import by.kanarski.booking.dao.interfaces.IHotelDao;
import by.kanarski.booking.dao.interfaces.ILocationDao;
import by.kanarski.booking.dto.DestinationDto;
import by.kanarski.booking.dto.hotel.HotelDto;
import by.kanarski.booking.dto.location.LocationDto;
import by.kanarski.booking.entities.hotel.Hotel;
import by.kanarski.booking.entities.location.Location;
import by.kanarski.booking.exceptions.DaoException;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.services.interfaces.IHotelService;
import by.kanarski.booking.utils.BookingExceptionHandler;
import by.kanarski.booking.utils.ServiceHelper;
import by.kanarski.booking.utils.filter.SearchFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class HotelService extends ExtendedBaseService<Hotel, HotelDto> implements IHotelService {

    @Autowired
    private IHotelDao hotelDao;

    @Autowired
    private ILocationDao locationDao;
    
    @Autowired
    private ServiceHelper serviceHelper;

    @Override
    public List<HotelDto> getByHotelName(HotelDto initialHotelDto, int page, int perPage) throws ServiceException {
        List<HotelDto> hotelDtoList = null;
        try {
            String localizedHotelName = initialHotelDto.getHotelName();
            String language = initialHotelDto.getLanguage();
            String hotelName = serviceHelper.getNotLocalizedHotelName(localizedHotelName, language);
            SearchFilter hotelFilter = SearchFilter.createBasicEqFilter(SearchParameter.HOTELNAME, hotelName);
            List<Hotel> hotelList = hotelDao.getListByFilter(hotelFilter, page, perPage);
            hotelDtoList = conversionService.convert(hotelList, HotelDto.class);
        } catch (DaoException e) {
            BookingExceptionHandler.handleDaoException(e);
        }
        return hotelDtoList;
    }

    @Override
    public List<HotelDto> getByCountry(HotelDto initialHotelDto, int page, int perPage) throws ServiceException {
        List<HotelDto> hotelDtoList = null;
        try {
            String localizedCountry = initialHotelDto.getLocation().getCountry();
            String language = initialHotelDto.getLanguage();
            String country = serviceHelper.getNotLocalizedCountry(localizedCountry, language);
            SearchFilter hotelFilter = SearchFilter.createAliasFilter(AliasName.LOCATION, AliasValue.LANGUAGE);
            hotelFilter.addEqFilter(SearchParameter.LOCATION_COUNTRY, country);
            List<Hotel> hotelList = hotelDao.getListByFilter(hotelFilter, page, perPage);
            hotelDtoList = conversionService.convert(hotelList, HotelDto.class);
        } catch (DaoException e) {
            BookingExceptionHandler.handleDaoException(e);
        }
        return hotelDtoList;
    }

    @Override
    public List<HotelDto> getByCity(HotelDto initialHotelDto, int page, int perPage) throws ServiceException {
        List<HotelDto> hotelDtoList = null;
        try {
            String localizedCity = initialHotelDto.getLocation().getCity();
            String language = initialHotelDto.getLanguage();
            String city = serviceHelper.getNotLocalizedCity(localizedCity, language);
            SearchFilter hotelFilter = SearchFilter.createAliasFilter(AliasName.LOCATION, AliasValue.LANGUAGE);
            hotelFilter.addEqFilter(SearchParameter.LOCATION_CITY, city);
            List<Hotel> hotelList = hotelDao.getListByFilter(hotelFilter, page, perPage);
            hotelDtoList = conversionService.convert(hotelList, HotelDto.class);
        } catch (DaoException e) {
            BookingExceptionHandler.handleDaoException(e);
        }
        return hotelDtoList;
    }

    @Override
    public List<HotelDto> getByDestination(DestinationDto destinationDto, int page, int perPage) throws ServiceException {
        List<HotelDto> hotelDtoList = null;
        List<String> parameterList = destinationDto.getParameterList();
        String language = destinationDto.getLanguage();
        try {
            int resultsLeft = perPage;
            List<Hotel> hotelList = new ArrayList<>();
            List<Location> locationList = new ArrayList<>();
            switch (parameterList.size()) {
                default: {
                    String parameter = parameterList.get(0);
                    {
                        String defaultHotelName = serviceHelper.getNotLocalizedHotelName(parameter, language);
                        SearchFilter filter = SearchFilter.createBasicIlikeFilter(SearchParameter.HOTELNAME,
                                defaultHotelName);
                        List<Hotel> partHotelList = hotelDao.getListByFilter(filter, page, resultsLeft);
                        hotelList.addAll(partHotelList);
                        resultsLeft -= hotelList.size();
                    }
                    if (resultsLeft > 0) {
                        String defaultCountry = serviceHelper.getNotLocalizedCountry(parameter, language);
                        SearchFilter filter = SearchFilter.createBasicIlikeFilter(SearchParameter.COUNTRY,
                                defaultCountry);
                        List<Location> partLocationList = locationDao.getListByFilter(filter, page, resultsLeft);
                        locationList.addAll(partLocationList);
                        resultsLeft -= locationList.size();
                    }
                    if (resultsLeft > 0) {
                        String defaultCity = serviceHelper.getNotLocalizedCity(parameter, language);
                        SearchFilter filter = SearchFilter.createBasicIlikeFilter(SearchParameter.CITY,
                                defaultCity);
                        List<Location> partLocationList = locationDao.getListByFilter(filter, page, resultsLeft);
                        locationList.addAll(partLocationList);
                        resultsLeft -= locationList.size();
                    }
                    break;
                }
            }

            List<HotelDto> partHotelDtoList = new ArrayList<>();

            if (hotelList.size() != 0) {
                partHotelDtoList = conversionService.convert(hotelList, HotelDto.class);
            }
            List<LocationDto> partLocationDtoList = new ArrayList<>();
            if (locationList.size() != 0) {
                partLocationDtoList = conversionService.convert(locationList, LocationDto.class);
            }
            List<HotelDto> anyHotelDtoList = toAnyHotelDtoList(partLocationDtoList);
            hotelDtoList = partHotelDtoList;
            hotelDtoList.addAll(anyHotelDtoList);
        } catch (DaoException e) {
            BookingExceptionHandler.handleDaoException(e);
        }
        return hotelDtoList;
    }

    public HotelDto getByDescription(HotelDto description) throws ServiceException {
        HotelDto hotelDto = null;
        String hotelName = description.getHotelName();
        String country = description.getLocation().getCountry();
        String city = description.getLocation().getCity();
        String language = description.getLanguage();
        try {
            String notLocalizedHotelName = serviceHelper.getNotLocalizedHotelName(hotelName, language);
            String notLocalizedCountry = serviceHelper.getNotLocalizedCountry(country, language);
            String notLocalizedCity = serviceHelper.getNotLocalizedCity(city, language);
            SearchFilter searchFilter = SearchFilter.createAliasFilter(AliasName.LOCATION, AliasValue.LOCATION)
                    .addEqFilter(SearchParameter.HOTELNAME, notLocalizedHotelName)
                    .addEqFilter(SearchParameter.LOCATION_COUNTRY, notLocalizedCountry)
                    .addEqFilter(SearchParameter.LOCATION_CITY, notLocalizedCity);
            Hotel hotel = hotelDao.getUniqueByFilter(searchFilter);
            hotelDto = conversionService.convert(hotel, HotelDto.class);
        } catch (DaoException e) {
            BookingExceptionHandler.handleDaoException(e);
        }
        return hotelDto;
    }

    private HotelDto toAnyHotelDto(LocationDto locationDto) {
        return new HotelDto(locationDto, FieldValue.ANY_HOTEL);
    }

    private List<HotelDto> toAnyHotelDtoList(List<LocationDto> locationDtoList) {
        List<HotelDto> hotelDtoList = new ArrayList<>();
        for (LocationDto locationDto : locationDtoList) {
            hotelDtoList.add(toAnyHotelDto(locationDto));
        }
        return hotelDtoList;
    }
}
