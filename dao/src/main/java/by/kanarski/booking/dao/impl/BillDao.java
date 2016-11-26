package by.kanarski.booking.dao.impl;

import by.kanarski.booking.dao.interfaces.IBillDao;
import by.kanarski.booking.entities.Bill;

public class BillDao extends ExtendedBaseDao<Bill> implements IBillDao {

    private static BillDao instance = null;

    private BillDao() {
    }

    public static synchronized BillDao getInstance() {
        if (instance == null) {
            instance = new BillDao();
        }
        return instance;
    }
}
