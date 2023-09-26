package lk.ijse.carhire.dao.custom.Impl;

import lk.ijse.carhire.dao.CrudUtil;
import lk.ijse.carhire.dao.SessionFactoryConfiguration;
import lk.ijse.carhire.dao.custom.CarDao;
import lk.ijse.carhire.entity.CarEntity;
import org.hibernate.Session;

import java.util.List;

public class CarDaoImpl implements CarDao {
    Session session = SessionFactoryConfiguration.getInstance().getSession();

    @Override
    public boolean add(CarEntity t) throws Exception {
        return CrudUtil.executeUpdate("INSERT INTO Customer VALUES(?,?,?,?,?);",
                t.getId());
    }

    @Override
    public boolean update(CarEntity carEntity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public CarEntity get(String s) throws Exception {
        return null;
    }

    @Override
    public List<CarEntity> getAll() throws Exception {
        return null;
    }
}
