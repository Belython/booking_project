package by.kanarski.booking.dao.impl;

import by.kanarski.booking.dao.interfaces.IFacilityTranslationDao;
import by.kanarski.booking.entities.facility.FacilityTranslation;

public class FacilityTranslationDao extends ExtendedBaseDao<FacilityTranslation> implements IFacilityTranslationDao {

    private static FacilityTranslationDao instance = null;

    private FacilityTranslationDao() {
    }

    public static synchronized FacilityTranslationDao getInstance() {
        if (instance == null) {
            instance = new FacilityTranslationDao();
        }
        return instance;
    }

}
