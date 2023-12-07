package lk.ijse.carhire.dao.custom.Impl;


import lk.ijse.carhire.dao.CrudUtil;
import lk.ijse.carhire.dao.SessionFactoryConfiguration;
import lk.ijse.carhire.dao.custom.RentDao;
import lk.ijse.carhire.entity.RentEntity;
import org.hibernate.Session;

import java.sql.ResultSet;
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
    public RentEntity get(int s) throws Exception {
        return null;
    }

    @Override
    public List<RentEntity> getAll() throws Exception {
        return null;
    }

    @Override
    public void updateReturnStatus(int rentalId, boolean isReturned) {
        try {
            CrudUtil.executeUpdate("UPDATE Rent SET Availability = ? WHERE RentId = ?", isReturned, rentalId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public double rentCost(int carId) {
        try {
            ResultSet resultSet = CrudUtil.executeQuery("SELECT (DATEDIFF(Rent.To_Date, Rent.From_Date) * Car.PriceperDay) AS TotalRent FROM Rent JOIN Car ON Rent.CarID = Car.CarID WHERE Rent.CarID = ?", carId);

            if (resultSet.next()) {
                double rentCost = resultSet.getDouble("TotalRent");
                System.out.println("Total Rent: " + rentCost);
            } else {
                System.out.println("No rental information found for CarID " + carId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
