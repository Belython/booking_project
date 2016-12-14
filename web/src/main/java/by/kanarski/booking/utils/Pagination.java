package by.kanarski.booking.utils;

import by.kanarski.booking.constants.Parameter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

@Component
public class Pagination {
    private static final int MIN_ROWS_ON_PAGE = 2;
    private static final int FIRST_PAGE = 1;
    private static final int PAGE_FOR_PAGINATION = 1;

    public Pagination() {
    }

    /**
     * the method receives a number of records per page
     *
     * @param request
     * @return
     */
    public int getItemPerPage(HttpServletRequest request) {
        int perPage;
        String perPageAsString = request.getParameter(Parameter.PER_PAGE);
        if (StringUtils.isBlank(perPageAsString)) {
            perPage = MIN_ROWS_ON_PAGE;
        } else {
            perPage = Integer.parseInt(request.getParameter(Parameter.PER_PAGE));
        }
        request.setAttribute(Parameter.PER_PAGE, perPage);
        return perPage;
    }

    /**
     * the method gets the record number from which to read the value from the database
     *
     * @param request
     * @return
     */
    public int getStartRow(HttpServletRequest request) {
        int perPage = getItemPerPage(request);
        int page = FIRST_PAGE;
        int startRow = FIRST_PAGE;
        String curretnPageAsString = request.getParameter(Parameter.PAGE);
        if (curretnPageAsString != null) {
            page = Integer.parseInt(curretnPageAsString);
            startRow = Integer.parseInt(curretnPageAsString) * perPage - perPage;
        }
        request.setAttribute(Parameter.PAGE, page);
        return startRow;
    }

    /**
     * check the total number of records on the multiplicity of the minimum number of records per page,
     * if the number is not a multiple, add another page
     *
     * @param pagesCount- number of pages
     * @return
     */
    public int getPagesCount(int pagesCount) {
        if (pagesCount % MIN_ROWS_ON_PAGE != 0) {
            pagesCount += PAGE_FOR_PAGINATION;
        }
        return pagesCount;
    }

    public Integer getTotalPages(Long totalItems, Integer perPage, HttpServletRequest request) {
        Integer totalPages =  Math.round(totalItems / perPage);
        totalPages = (totalPages <= 0) ? 1 : totalPages;
        request.setAttribute(Parameter.TOTAL_PAGES, totalPages);
        return totalPages;
    }
}
