package lk.ijse.carhire.dao.custom.Impl;

import lk.ijse.carhire.dao.CrudUtil;
import lk.ijse.carhire.dao.SessionFactoryConfiguration;
import lk.ijse.carhire.dao.custom.CarDao;
import lk.ijse.carhire.entity.CarCategoryEntity;
import lk.ijse.carhire.entity.CarEntity;
import lk.ijse.carhire.entity.CustomerEntity;
import org.hibernate.Session;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarDaoImpl implements CarDao {
    @Override
    public boolean add(CarEntity t) throws Exception {
        return CrudUtil.executeUpdate("INSERT INTO Car VALUES(?,?,?,?,?,?,?);",
                t.getId(), t.getBrand(), t.getModel(), t.getPlateNo(), t.getPriceperday(), t.getYear(), t.getCarCategoryEntity());
    }

    @Override
    public boolean update(CarEntity carEntity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(Integer s) throws Exception {
        return false;
    }

    @Override
    public CarEntity get(int s) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("Select * FROM Customer WHERE CarID = ?", s);

        while (rst.next()) {
            CarEntity entity = new CarEntity();
            entity.setId(rst.getInt("CarID"));
            entity.setPlateNo(rst.getString("PlateNo"));
            entity.setBrand(rst.getString("Brand"));
            entity.setModel(rst.getString("Model"));
            entity.setYear(rst.getInt("Year"));
            int carCategoryId = rst.getInt("Id");
            CarCategoryEntity carCategoryEntity = getCarCategoryById(carCategoryId);
            entity.setCarCategoryEntity(carCategoryEntity);
            entity.setPriceperday(rst.getDouble("PriceperDay"));

            return entity;
        }
        return null;
    }

    @Override
    public List<CarEntity> getAll() throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Car");
        List<CarEntity> carEntities = new ArrayList<>();


        while (rst.next()) {
            CarEntity entity = new CarEntity();
            entity.setId(rst.getInt("CarID"));
            entity.setPlateNo(rst.getString("PlateNo"));
            entity.setBrand(rst.getString("Brand"));
            entity.setModel(rst.getString("Model"));
            entity.setYear(rst.getInt("Year"));
            int carCategoryId = rst.getInt("Id");
            CarCategoryEntity carCategoryEntity = getCarCategoryById(carCategoryId);
            entity.setCarCategoryEntity(carCategoryEntity);
            entity.setPriceperday(rst.getDouble("PriceperDay"));
            carEntities.add(entity);
        }
        return carEntities;
    }

    private CarCategoryEntity getCarCategoryById(int carCategoryId) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Car_Category WHERE Id = ?", carCategoryId);
        if (rst.next()) {
            CarCategoryEntity entity = new CarCategoryEntity();
            entity.setId(rst.getInt("Id"));
            return entity;
        } else {
            throw new Exception("No CarCategoryEntity found with ID: " + carCategoryId);
        }
    }


}
