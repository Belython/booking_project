package by.kanarski.booking.utils.conver.support;

import by.kanarski.booking.entities.State;
import org.springframework.core.convert.converter.Converter;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

public class StateToStateDtoConverter extends EntityConverter implements Converter<State, String> {

    @Override
    public String convert(State state) {
        return state.getStateName();
    }
}
