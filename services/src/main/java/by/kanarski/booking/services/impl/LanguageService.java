package by.kanarski.booking.services.impl;

import by.kanarski.booking.dao.impl.LanguageDao;
import by.kanarski.booking.dao.interfaces.IExtendedBaseDao;
import by.kanarski.booking.entities.Language;
import by.kanarski.booking.services.interfaces.ILanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class LanguageService extends ExtendedBaseService<Language, String> implements ILanguageService {

    @Autowired
    private LanguageDao languageDao;

    @Override
    protected IExtendedBaseDao<Language> getDao() {
        return languageDao;
    }
}
