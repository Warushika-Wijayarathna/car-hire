package lk.ijse.carhire.dao.custom.Impl;

import lk.ijse.carhire.dao.SessionFactoryConfiguration;
import lk.ijse.carhire.dao.custom.CarCategoryDao;
import lk.ijse.carhire.entity.CarCategoryEntity;
import org.hibernate.Session;

import java.util.List;

public class CarCategoryDaoImpl implements CarCategoryDao {
    Session session = SessionFactoryConfiguration.getInstance().getSession();

    @Override
    public boolean add(CarCategoryEntity carCategoryEntity) throws Exception {
        return false;
    }

    @Override
    public boolean update(CarCategoryEntity carCategoryEntity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public CarCategoryEntity get(String s) throws Exception {
        return null;
    }

    @Override
    public List<CarCategoryEntity> getAll() throws Exception {
        return null;
    }
}
