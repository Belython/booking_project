package by.kanarski.booking.utils.conver.support;

import by.kanarski.booking.entities.State;
import org.springframework.core.convert.converter.Converter;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

public class StateDtoToStateConverter extends EntityConverter implements Converter<String, State> {

    @Override
    public State convert(String stateDto) {
        return entityBuilder.buildState(stateDto);
    }
}
