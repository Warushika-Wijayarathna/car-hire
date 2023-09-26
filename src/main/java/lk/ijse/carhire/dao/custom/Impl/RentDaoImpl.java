package lk.ijse.carhire.dao.custom.Impl;


import lk.ijse.carhire.dao.SessionFactoryConfiguration;
import lk.ijse.carhire.dao.custom.RentDao;
import lk.ijse.carhire.entity.RentEntity;
import org.hibernate.Session;

import java.util.List;

public class RentDaoImpl implements RentDao {
    Session session = SessionFactoryConfiguration.getInstance().getSession();

    @Override
    public boolean add(RentEntity rentEntity) throws Exception {
        return false;
    }

    @Override
    public boolean update(RentEntity rentEntity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public RentEntity get(String s) throws Exception {
        return null;
    }

    @Override
    public List<RentEntity> getAll() throws Exception {
        return null;
    }
}
