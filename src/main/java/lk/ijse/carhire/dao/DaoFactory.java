package lk.ijse.carhire.dao;

import lk.ijse.carhire.dao.custom.Impl.CarCategoryDaoImpl;
import lk.ijse.carhire.dao.custom.Impl.CarDaoImpl;
import lk.ijse.carhire.dao.custom.Impl.CustomerDaoImpl;
import lk.ijse.carhire.dao.custom.Impl.RentDaoImpl;

public class DaoFactory {
    private static DaoFactory daoFactory;

    private DaoFactory(){
    }
    public static DaoFactory getInstance(){
        if (daoFactory == null) {
            daoFactory=new DaoFactory();
        }
        return daoFactory;
    }

    public SuperDao getDao(DaoTypes type){
        switch (type){
            case CUSTOMER:
                return new CustomerDaoImpl();
            case CAR:
                return new CarDaoImpl();
            case CARCATEGORY:
                return new CarCategoryDaoImpl();
            case RENT:
                return new RentDaoImpl();
            default:
                return null;
        }
    }

    public enum DaoTypes {
        CAR, CARCATEGORY, CUSTOMER, RENT

    }
}
