package by.kanarski.booking.dao.interfaces;

import by.kanarski.booking.entities.Language;
import by.kanarski.booking.exceptions.DaoException;

import java.util.List;

/**
 * Language dao iterface
 * @author Dzmitry Kanarski
 * @version 1.0
 * @see IBaseDao
 */

public interface ILanguageDao extends IBaseDao<Language> {

    void updateList(List<Language> locationList) throws DaoException;

    void addList(List<Language> locationList) throws DaoException;

}
