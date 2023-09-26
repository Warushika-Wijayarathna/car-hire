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
        return CrudUtil.executeUpdate("INSERT INTO Customer VALUES(?,?,?,?,?);",
                t.getId(),t.getAddress(),t.getMobile(),t.getName(),t.getNic());
    }

    @Override
    public boolean update(CustomerEntity customerEntity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public CustomerEntity get(String s) throws Exception {
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
            customerEntities.add(entity);
        }

        return customerEntities;
    }


}
