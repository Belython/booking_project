package by.kanarski.booking.services.impl;

import by.kanarski.booking.dao.interfaces.ILanguageDao;
import by.kanarski.booking.entities.Language;
import by.kanarski.booking.exceptions.DaoException;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.services.interfaces.ILanguageService;
import by.kanarski.booking.utils.wrappers.SystemLanguages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class LanguageService extends ExtendedBaseService<String, Language> implements ILanguageService {

    @Autowired
    private ILanguageDao languageDao;

    public void initializeSystemLanguages(SystemLanguages systemLanguages) throws ServiceException {
        try {
            List<Language> languageList = languageDao.getAll();
            for (Language language : languageList) {
                systemLanguages.add(language.getName());
            }
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

}
