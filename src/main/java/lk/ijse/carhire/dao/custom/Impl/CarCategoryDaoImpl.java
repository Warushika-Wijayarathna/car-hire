package lk.ijse.carhire.dao.custom.Impl;

import lk.ijse.carhire.dao.CrudUtil;
import lk.ijse.carhire.dao.SessionFactoryConfiguration;
import lk.ijse.carhire.dao.custom.CarCategoryDao;
import lk.ijse.carhire.entity.CarCategoryEntity;
import lk.ijse.carhire.entity.CustomerEntity;
import org.hibernate.Session;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CarCategoryDaoImpl implements CarCategoryDao {
    @Override
    public boolean add(CarCategoryEntity carCategoryEntity) throws Exception {
        return CrudUtil.executeUpdate("INSERT INTO Car_Category VALUES(?,?);",
                carCategoryEntity.getId(),carCategoryEntity.getType());
    }

    @Override
    public boolean update(CarCategoryEntity carCategoryEntity) throws Exception {
        return CrudUtil.executeUpdate("UPDATE Car_Category SET  Type=? WHERE Id=?",
                carCategoryEntity.getType(),carCategoryEntity.getId());
    }

    @Override
    public boolean delete(Integer s) throws Exception {
        return CrudUtil.executeUpdate("DELETE FROM Car_Category WHERE Id=?",s);
    }

    @Override
    public CarCategoryEntity get(int s) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("Select * FROM Car_Category WHERE Id = ?", s);

        while (rst.next()) {
            CarCategoryEntity entity = new CarCategoryEntity();
            entity.setId(rst.getInt("Id"));
            entity.setType(rst.getString("Type"));

            return entity;
        }
        return null;
    }

    @Override
    public List<CarCategoryEntity> getAll() throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Car_Category");
        List<CarCategoryEntity> carCategoryEntities = new ArrayList<>();

        while (rst.next()) {
            CarCategoryEntity entity = new CarCategoryEntity();
            entity.setId(rst.getInt("Id"));
            entity.setType(rst.getString("Type"));
            carCategoryEntities.add(entity);
        }

        return carCategoryEntities;

    }
}
