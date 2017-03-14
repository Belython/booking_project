package by.kanarski.booking.services.impl;

import by.kanarski.booking.dao.interfaces.IExtendedBaseDao;
import by.kanarski.booking.dao.interfaces.ILocationDao;
import by.kanarski.booking.dto.location.LocationDto;
import by.kanarski.booking.entities.location.Location;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.services.interfaces.ILocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class LocationService extends ExtendedBaseService<Location, LocationDto> implements ILocationService {

    @Autowired
    private static ILocationDao locationDao;


    public List<LocationDto> getByCountry(String country, int page, int perPage) throws ServiceException {
        List<LocationDto> locationDtoList = null;
//        SearchFilter locationFilter = SearchFilter.createBasicEqFilter("country", country);
//        locationFilter.addEqFilter("language", "EN");
//        try {
//            List<Location> locationList = new ArrayList<>();
//            List<LocationTranslation> locationTranslationList = LocationTranslationDao.getInstance()
//                    .getListByFilter(locationFilter, page, perPage);
//            for (LocationTranslation locationTranslation : locationTranslationList) {
//                Location location = locationTranslation.getLocation();
//                locationList.add(location);
//            }
//            locationDtoList = converter.toDtoList(locationList);
//            transaction.commit();
//        } catch (DaoException e) {
//            ExceptionHandler.handleDaoException(transaction, e);
//        } catch (LocalisationException e) {
//            ExceptionHandler.handleLocalizationException(e);
//        }
        return locationDtoList;
    }

//    public List<LocationDto> getByCountry(String country, int page, int perPage) throws ServiceException {
//        TransactoinWrapper transaction = TransactionManager.getTransaction();
//        List<LocationDto> locationDtoList = null;
//        SearchFilter locationFilter = SearchFilter.createBasicEqFilter("country", country);
//        locationFilter.addEqFilter("language", "EN");
//        try {
//            transaction.begin();
//            List<Location> locationList = new ArrayList<>();
//            List<LocationTranslation> locationTranslationList = LocationTranslationDao.getInstance()
//                    .getListByFilter(locationFilter, page, perPage);
//            for (LocationTranslation locationTranslation : locationTranslationList) {
//                Location location = locationTranslation.getLocation();
//                locationList.add(location);
//            }
//            locationDtoList = converter.toDtoList(locationList);
//            transaction.commit();
//        } catch (DaoException e) {
//            ExceptionHandler.handleDaoException(transaction, e);
//        } catch (LocalisationException e) {
//            ExceptionHandler.handleLocalizationException(e);
//        }
//        return locationDtoList;
//    }


    @Override
    protected IExtendedBaseDao<Location> getDao() {
        return locationDao;
    }
}
