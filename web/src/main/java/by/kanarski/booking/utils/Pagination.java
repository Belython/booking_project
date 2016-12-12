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
    private static final int MIN_ROWS_ON_PAGE = 5;
    private static final int FIRST_PAGE = 1;
    private static final int PAGE_FOR_PAGINATION = 1;
    private static final int PAGER_SIZE = 5;

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
        request.setAttribute(Parameter.ROWS, perPage);
        return perPage;
    }

    /**
     * the method gets the record number from which to read the value from the database
     *
     * @param request
     * @return
     */
    public int getStartRow(HttpServletRequest request) {
        //default params to read
        int perPage = getItemPerPage(request);
        int currentRowFromDatabase = FIRST_PAGE;
        int currentPage = currentRowFromDatabase;
        //checks the current page number on the user interface
        if (request.getParameter(Parameter.START_PAGE) != null) {
            currentPage = Integer.parseInt(request.getParameter(Parameter.START_PAGE));
            currentRowFromDatabase = Integer.parseInt(request.getParameter(Parameter.START_PAGE)) * perPage - perPage + 1;
        }
//        request.setAttribute(Parameter.START_PAGE, currentRowFromDatabase);
        request.setAttribute(Parameter.CURRENT_PAGE, currentPage);

        return currentRowFromDatabase;
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
