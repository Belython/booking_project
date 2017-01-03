package by.kanarski.booking.utils.conver.service;

import org.springframework.core.convert.ConversionService;

import java.util.List;
import java.util.Set;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

public interface IEntityConversionService extends ConversionService {

    <T> List<T> convert(List<?> sourceList, Class<T> targetClass);

    <T> Set<T> convert(Set<?> sourceSet, Class<T> targetClass);

    <T> List<T> convertSetToList(Set<?> sourceSet, Class<T> targetClass);

    <T> Set<T> convertListToSet(List<?> sourceList, Class<T> targetClass);
}
