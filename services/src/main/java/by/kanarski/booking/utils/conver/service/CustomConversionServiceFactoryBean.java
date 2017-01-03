package by.kanarski.booking.utils.conver.service;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.convert.support.ConversionServiceFactory;
import org.springframework.core.convert.support.GenericConversionService;

import java.util.Set;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

public class CustomConversionServiceFactoryBean implements FactoryBean<IEntityConversionService>, InitializingBean {

    private Set<?> converters;

    private EntitiyConversionService conversionService;


    /**
     * Configure the set of custom converter objects that should be added:
     * implementing {@link org.springframework.core.convert.converter.Converter},
     * {@link org.springframework.core.convert.converter.ConverterFactory},
     * or {@link org.springframework.core.convert.converter.GenericConverter}.
     */
    public void setConverters(Set<?> converters) {
        this.converters = converters;
    }

    @Override
    public void afterPropertiesSet() {
        this.conversionService = createConversionService();
        ConversionServiceFactory.registerConverters(this.converters, this.conversionService);
    }

    /**
     * Create the ConversionService instance returned by this factory bean.
     * <p>Creates a simple {@link GenericConversionService} instance by default.
     * Subclasses may override to customize the ConversionService instance that
     * gets created.
     */
    protected EntitiyConversionService createConversionService() {
        return new EntitiyConversionService();
    }


    // implementing FactoryBean

    @Override
    public IEntityConversionService getObject() {
        return this.conversionService;
    }

    @Override
    public Class<? extends IEntityConversionService> getObjectType() {
        return EntitiyConversionService.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

}