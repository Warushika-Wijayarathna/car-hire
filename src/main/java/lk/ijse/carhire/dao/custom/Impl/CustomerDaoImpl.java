package lk.ijse.carhire.dao.custom.Impl;


import lk.ijse.carhire.dao.CrudUtil;
import lk.ijse.carhire.dao.custom.CustomerDao;
import lk.ijse.carhire.entity.CustomerEntity;


import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {

    @Override
    public boolean add(CustomerEntity t) throws Exception {
        return CrudUtil.executeUpdate("INSERT INTO Customer VALUES(?,?,?,?,?,?);",
                t.getId(),t.getAddress(),t.getEmail(),t.getMobile(),t.getName(),t.getNic());
    }

    @Override
    public boolean update(CustomerEntity t) throws Exception {
        return CrudUtil.executeUpdate("UPDATE Customer SET CustAddress =?,  Email=?, MobileNo = ?, CustName=?,  NIC =? WHERE CustID=?",
                t.getAddress(),t.getEmail(),t.getMobile(),t.getName(),t.getNic(),t.getId());
    }

    @Override
    public boolean delete(Integer s) throws Exception {
        return CrudUtil.executeUpdate("DELETE FROM Customer WHERE CustID=?", s);
    }

    @Override
    public CustomerEntity get(int s) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("Select * FROM Customer WHERE CustID = ?", s);

        while (rst.next()) {
            CustomerEntity entity = new CustomerEntity();
            entity.setId(rst.getInt("CustID"));
            entity.setName(rst.getString("CustName"));
            entity.setMobile(rst.getInt("MobileNo"));
            entity.setAddress(rst.getString("CustAddress"));
            entity.setNic(rst.getString("NIC"));
            entity.setEmail(rst.getString("Email"));

            return entity;
        }
        return null;
    }
    @Override
    public List<CustomerEntity> getAll() throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Customer");
        List<CustomerEntity> customerEntities = new ArrayList<>();

        while (rst.next()) {
            CustomerEntity entity = new CustomerEntity();
            entity.setId(rst.getInt("CustID"));
            entity.setName(rst.getString("CustName"));
            entity.setMobile(rst.getInt("MobileNo"));
            entity.setAddress(rst.getString("CustAddress"));
            entity.setNic(rst.getString("NIC"));
            entity.setEmail(rst.getString("Email"));
            customerEntities.add(entity);
        }

        return customerEntities;
    }


}
