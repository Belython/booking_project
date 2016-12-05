package by.kanarski.booking.dao.impl;

import by.kanarski.booking.dao.interfaces.ILanguageDao;
import by.kanarski.booking.entities.Language;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LanguageDao extends ExtendedBaseDao<Language> implements ILanguageDao {

    @Autowired
    public LanguageDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

}
