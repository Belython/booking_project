package by.kanarski.booking.utils.conver.service;

import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

public class EntitiyConversionService extends GenericConversionService implements IEntityConversionService {

    @Override
    public <T> List<T> convert(List<?> sourceList, Class<T> targetClass) {
        Assert.notNull(sourceList, "Cannot convert null list.");
        List<T> targetList = new ArrayList<>();
        for (Object source : sourceList) {
            T target = super.convert(source, targetClass);
            targetList.add(target);
        }
        return targetList;
    }

    @Override
    public <T> Set<T> convert(Set<?> sourceSet, Class<T> targetClass) {
        Assert.notNull(sourceSet, "Cannot convert null set.");
        Set<T> targetSet = new HashSet<>();
        for (Object source : sourceSet) {
            T target = super.convert(source, targetClass);
            targetSet.add(target);
        }
        return targetSet;
    }

    @Override
    public <T> List<T> convertSetToList(Set<?> sourceSet, Class<T> targetClass) {
        Assert.notNull(sourceSet, "Cannot convert null set.");
        List<T> targetList = new ArrayList<>();
        for (Object source : sourceSet) {
            T target = super.convert(source, targetClass);
            targetList.add(target);
        }
        return targetList;
    }

    @Override
    public <T> Set<T> convertListToSet(List<?> sourceList, Class<T> targetClass) {
        Assert.notNull(sourceList, "Cannot convert null list.");
        Set<T> targetSet = new HashSet<>();
        for (Object source : sourceList) {
            T target = super.convert(source, targetClass);
            targetSet.add(target);
        }
        return targetSet;
    }
}
