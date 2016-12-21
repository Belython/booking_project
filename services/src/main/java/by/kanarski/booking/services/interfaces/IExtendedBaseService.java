package by.kanarski.booking.services.interfaces;

import by.kanarski.booking.exceptions.ServiceException;

import java.util.List;

/**
 * Extended base service interface. Uses DTO as return parameter.
 * For transformations entity <--> DTO uses DtoToEntityConverter
 * @author Dzmitry Kanarski
 * @version 1.0
 * @see by.kanarski.booking.utils.DtoToEntityConverter
 */
public interface IExtendedBaseService<E, D> {
    
    /**
     * Appends entity in the database
     * @param t DTO to be appended to database
     * @throws ServiceException
     */
    void add(D t) throws ServiceException;
    
    /**
     * Recives DTO from databse by id
     * @param id the entity id
     * @return DTO with the corresponding id
     * @throws ServiceException
     */
    D getById(Long id) throws ServiceException;
    
    /**
     * Updates entity in database
     * @param t DTO to be update
     * @throws ServiceException
     */
    void update(D t) throws ServiceException;
    
    /**
     * Deletes entity in database
     * @param t DTO to delete
     * @throws ServiceException
     */
    void delete(D t) throws ServiceException;

    /**
     * Recives all DTO from table. Not recommended for use
     * @return all DTOs
     * @throws ServiceException
     */
    List<D> getAll() throws ServiceException;

    /**
     * Updates list of entities
     * @param tList list of DTOs to update
     * @throws ServiceException
     */
    void updateList(List<D> tList) throws ServiceException;

    /**
     * Adds list of entities
     * @param tList  list of DTOs to add
     * @throws ServiceException
     */
    void addList(List<D> tList) throws ServiceException;

}
