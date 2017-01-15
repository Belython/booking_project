package by.kanarski.booking.utils.conver.support;

import by.kanarski.booking.utils.EntityBuilder;
import by.kanarski.booking.utils.SystemLanguagesManager;
import by.kanarski.booking.utils.conver.service.IEntityConversionService;
import by.kanarski.booking.utils.threadLocal.UserPreferences;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

public class EntityConverter {

    @Autowired
    protected EntityBuilder entityBuilder;
    @Autowired
    protected SystemLanguagesManager systemLanguagesManager;
    @Autowired
//    private IEntityConversionService conversionService;
    protected ApplicationContext applicationContext;
//    protected IEntityConversionService conversionService = applicationContext.getBean(IEntityConversionService.class);
    protected Long getLanguageId() {
        return systemLanguagesManager.getLanguageId(UserPreferences.getRequestedLocale().getLanguage());
    }

    protected IEntityConversionService getConversionService() {
        return applicationContext.getBean(IEntityConversionService.class);
    }


}
