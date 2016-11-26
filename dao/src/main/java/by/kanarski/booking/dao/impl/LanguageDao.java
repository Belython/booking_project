package by.kanarski.booking.dao.impl;

import by.kanarski.booking.dao.interfaces.ILanguageDao;
import by.kanarski.booking.entities.Language;

public class LanguageDao extends ExtendedBaseDao<Language> implements ILanguageDao {

    private static LanguageDao instance = null;

    private LanguageDao() {
    }

    public static synchronized LanguageDao getInstance() {
        if (instance == null) {
            instance = new LanguageDao();
        }
        return instance;
    }

}
