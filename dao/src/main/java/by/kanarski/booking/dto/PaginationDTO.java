package by.kanarski.booking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginationDto implements Serializable {

    private static final Long serialVersionUID = 1L;

    private Integer page;
    private Integer perPage;
    private Integer pages;

}