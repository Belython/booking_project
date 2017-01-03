package by.kanarski.booking.utils;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

public interface Converter<S, D> {

    public D convert(S source, D destination);

}
