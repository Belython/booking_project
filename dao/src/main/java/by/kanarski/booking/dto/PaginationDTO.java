package by.kanarski.booking.dto;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 */

public class PaginationDto {
    private int id;
    private int pages;
    private int perPages;

    public PaginationDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getPerPages() {
        return perPages;
    }

    public void setPerPage(int perPage) {
        this.perPages = perPage;
    }
}
