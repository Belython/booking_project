package by.kanarski.booking.services.impl;

import by.kanarski.booking.constants.AliasName;
import by.kanarski.booking.constants.AliasValue;
import by.kanarski.booking.constants.SearchParameter;
import by.kanarski.booking.dao.interfaces.IHotelDao;
import by.kanarski.booking.dao.interfaces.ILocationDao;
import by.kanarski.booking.dto.DestinationDto;
import by.kanarski.booking.dto.hotel.HotelDto;
import by.kanarski.booking.dto.location.LocationDto;
import by.kanarski.booking.entities.hotel.Hotel;
import by.kanarski.booking.entities.location.Location;
import by.kanarski.booking.exceptions.DaoException;
import by.kanarski.booking.exceptions.LocalisationException;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.services.interfaces.IHotelService;
import by.kanarski.booking.utils.DtoToEntityConverter;
import by.kanarski.booking.utils.ExceptionHandler;
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

//    private DtoToEntityConverter<Location, LocationDto> locationConverter = new DtoToEntityConverter<>(
//            Location.class, LocationDto.class);



//    private DtoToEntityConverter<Location, LocationDto> locationConverter = ContextHolder.getServiceContext().getBean(
//            DtoToEntityConverter.class, Location.class, LocationDto.class);

    @Override
    public List<HotelDto> getByHotelName(HotelDto initialHotelDto, int page, int perPage) throws ServiceException {
        List<HotelDto> hotelDtoList = null;
        try {
            String localizedHotelName = initialHotelDto.getHotelName();
            String language = initialHotelDto.getLanguage();
            String hotelName = serviceHelper.getNotLocalizedHotelName(localizedHotelName, language);
            SearchFilter hotelFilter = SearchFilter.createBasicEqFilter(SearchParameter.HOTELNAME, hotelName);
            List<Hotel> hotelList = hotelDao.getListByFilter(hotelFilter, page, perPage);
            hotelDtoList = converter.toDtoList(hotelList);
        } catch (DaoException e) {
            ExceptionHandler.handleDaoException(e);
        } catch (LocalisationException e) {
            ExceptionHandler.handleLocalizationException(e);
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
            hotelDtoList = converter.toDtoList(hotelList);
        } catch (DaoException e) {
            ExceptionHandler.handleDaoException(e);
        } catch (LocalisationException e) {
            ExceptionHandler.handleLocalizationException(e);
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
            hotelDtoList = converter.toDtoList(hotelList);
        } catch (DaoException e) {
            ExceptionHandler.handleDaoException(e);
        } catch (LocalisationException e) {
            ExceptionHandler.handleLocalizationException(e);
        }
        return hotelDtoList;
    }

    public List<HotelDto> getByDestination(DestinationDto destinationDto, int page, int perPage) throws ServiceException {
        List<HotelDto> hotelDtoList = null;
        List<String> parameterList = destinationDto.getParameterList();
        String language = destinationDto.getLanguage();
        try {
            int resultsLeft = perPage;
            List<Hotel> hotelList = new ArrayList<>();
            List<Location> locationList = new ArrayList<>();
            switch (parameterList.size()) {
//                case 1:
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
//                case 2: {
//                    String country = parameterList.get(0);
//                    String defaultCountry = getNotLocalizedCountry(country);
//                    SearchFilter filter = SearchFilter.createBasicIlikeFilter(SearchParameter.LOCATION_COUNTRY,
//                            defaultCountry);
//
//                    break;
//                }
            }
            DtoToEntityConverter<Location, LocationDto> locationConverter = new DtoToEntityConverter<>(
                    Location.class, LocationDto.class);

            List<HotelDto> partHotelDtoList = converter.toDtoList(hotelList);
            List<LocationDto> partLocationDtoList = locationConverter.toDtoList(locationList);
            List<HotelDto> anyHotelDtoList = converter.toAnyHotelDtoList(partLocationDtoList);
            hotelDtoList = partHotelDtoList;
            hotelDtoList.addAll(anyHotelDtoList);
        } catch (DaoException e) {
            ExceptionHandler.handleDaoException(e);
        } catch (LocalisationException e) {
            ExceptionHandler.handleLocalizationException(e);
        }
        return hotelDtoList;
    }
}
