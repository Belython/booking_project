package by.kanarski.booking.dao.impl;

import by.kanarski.booking.dao.interfaces.IFacilityTranslationDao;
import by.kanarski.booking.entities.detail.DetailTranslation;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FacilityTranslationDao extends ExtendedBaseDao<DetailTranslation> implements IFacilityTranslationDao {

    @Autowired
    public FacilityTranslationDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }




}
