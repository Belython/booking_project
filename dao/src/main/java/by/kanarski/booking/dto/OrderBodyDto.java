package by.kanarski.booking.dto;

import by.kanarski.booking.dto.abstr.LocalizableDto;

import java.io.Serializable;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

public class OrderBodyDto extends LocalizableDto implements Serializable {

    private static final Long serialVersionUID = 1L;

    private String destination;


}
