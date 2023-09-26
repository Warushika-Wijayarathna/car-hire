package lk.ijse.carhire.service.custom.Impl;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import lk.ijse.carhire.Db.DBConnection;
import lk.ijse.carhire.dao.DaoFactory;
import lk.ijse.carhire.dao.custom.CustomerDao;
import lk.ijse.carhire.dto.CustomerDto;
import lk.ijse.carhire.entity.CustomerEntity;
import lk.ijse.carhire.service.custom.CustomerService;
import javax.swing.JOptionPane;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerServiceImpl implements CustomerService {
    CustomerDao customerDao=(CustomerDao) DaoFactory.getInstance().getDao(DaoFactory.DaoTypes.CUSTOMER);

    @FXML
    private TextField customerText;
    @FXML
    private TextField custNameText;
    @FXML
    private TextField custAddressText;
    @FXML
    private TextField nicText;
    @FXML
    private TextField mobileNoText;



    @Override
    public String addCustomer(CustomerDto customerDto) throws Exception {
        try {
            CustomerEntity entity = new CustomerEntity();
            entity.setId(customerDto.getId());
            entity.setName(customerDto.getName());
            entity.setAddress(customerDto.getAddress());
            entity.setNic(customerDto.getNic());
            entity.setMobile(customerDto.getMobile());

            customerDao.add(entity);


            String successMessage = "Customer added successfully with ID: " + entity.getId();
            JOptionPane.showMessageDialog(null, successMessage, "Success", JOptionPane.INFORMATION_MESSAGE);


            return successMessage;
        } catch (Exception e) {



            String errorMessage = "Error adding customer: " + e.getMessage();
            JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);

            throw new Exception(errorMessage, e);
        }
    }



    @Override
    public String updateCustomer(CustomerDto customerDto) throws Exception {
        try {

            CustomerEntity existingCustomer = customerDao.get(String.valueOf(customerDto.getId()));

            if (existingCustomer == null) {
                String errorMessage = "Customer with ID " + customerDto.getId() + " not found!";
                JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
                return errorMessage;
            }


            existingCustomer.setName(customerDto.getName());
            existingCustomer.setAddress(customerDto.getAddress());
            existingCustomer.setNic(customerDto.getNic());
            existingCustomer.setMobile(customerDto.getMobile());

            customerDao.update(existingCustomer);

            String successMessage = "Customer updated successfully with ID: " + existingCustomer.getId();
            JOptionPane.showMessageDialog(null, successMessage, "Success", JOptionPane.INFORMATION_MESSAGE);

            return successMessage;
        } catch (Exception e) {
            String errorMessage = "Error updating customer: " + e.getMessage();
            JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
            throw new Exception(errorMessage, e);
        }
    }


    @Override
    public String deleteCustomer(String id) throws Exception {
        try {

            CustomerEntity existingCustomer = customerDao.get(id);

            if (existingCustomer == null) {

                String errorMessage = "Customer with ID " + id + " not found!";
                JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
                return errorMessage;
            }


            int confirmation = JOptionPane.showConfirmDialog(
                    null,
                    "Are you sure you want to delete customer with ID " + id + "?",
                    "Confirmation",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirmation == JOptionPane.YES_OPTION) {

                customerDao.delete(String.valueOf(existingCustomer));

                String successMessage = "Customer with ID " + id + " deleted successfully";
                JOptionPane.showMessageDialog(null, successMessage, "Success", JOptionPane.INFORMATION_MESSAGE);

                return successMessage;
            } else {

                return "Deletion canceled by user";
            }
        } catch (Exception e) {
            String errorMessage = "Error deleting customer: " + e.getMessage();
            JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
            throw new Exception(errorMessage, e);
        }
    }



    public List<CustomerDto> getAllCustomers() throws Exception{
        ArrayList<CustomerEntity>customerEntities= (ArrayList<CustomerEntity>) customerDao.getAll();
        ArrayList<CustomerDto> customerDtoList = new ArrayList<>();

        for (CustomerEntity entity:customerEntities){
            customerDtoList.add(new CustomerDto(entity.getId(), entity.getAddress(), entity.getMobile(), entity.getName(), entity.getNic(), entity.getUpdate(),entity.getDelete()));
        }
        return customerDtoList;
    }




    @Override
    public CustomerDto getCustomer(String id) throws Exception {
        try {
            CustomerEntity customerEntity = customerDao.get(id);

            if (customerEntity == null) {

                String errorMessage = "Customer with ID " + id + " not found!";
                JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
                return null;
            }


            CustomerDto customerDto = new CustomerDto(Integer.parseInt(customerText.getText()), custAddressText.getText(),Integer.parseInt(mobileNoText.getText()), custNameText.getText(),  nicText.getText(), "Update","Delete");
            customerDto.setId(customerEntity.getId());
            customerDto.setName(customerEntity.getName());
            customerDto.setAddress(customerEntity.getAddress());
            customerDto.setNic(customerEntity.getNic());
            customerDto.setMobile(customerEntity.getMobile());

            return customerDto;
        } catch (Exception e) {
            String errorMessage = "Error retrieving customer: " + e.getMessage();
            JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
            throw new Exception(errorMessage, e);
        }
    }


}
