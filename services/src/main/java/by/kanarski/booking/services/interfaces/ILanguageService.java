package by.kanarski.booking.services.interfaces;

import by.kanarski.booking.entities.Language;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.utils.wrappers.SystemLanguages;

public interface ILanguageService extends IExtendedBaseService<String, Language> {

    void initializeSystemLanguages(SystemLanguages systemLanguages) throws ServiceException;

}
