package lk.ijse.carhire.service.custom.Impl;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import lk.ijse.carhire.dao.DaoFactory;
import lk.ijse.carhire.dao.custom.CustomerDao;
import lk.ijse.carhire.dto.CustomerDto;
import lk.ijse.carhire.entity.CustomerEntity;
import lk.ijse.carhire.service.custom.CustomerService;
import javax.swing.JOptionPane;

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
    @FXML
    private TextField emailText;



    @Override
    public String addCustomer(CustomerDto customerDto) throws Exception {
        try {
            CustomerEntity entity = new CustomerEntity();
            entity.setId(customerDto.getId());
            entity.setName(customerDto.getName());
            entity.setAddress(customerDto.getAddress());
            entity.setNic(customerDto.getNic());
            entity.setMobile(customerDto.getMobile());
            entity.setEmail(customerDto.getEmail());

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

            CustomerEntity existingCustomer = customerDao.get(Integer.parseInt(String.valueOf(customerDto.getId())));

            if (existingCustomer == null) {
                String errorMessage = "Customer with ID " + customerDto.getId() + " not found!";
                JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
                return errorMessage;
            }


            existingCustomer.setName(customerDto.getName());
            existingCustomer.setAddress(customerDto.getAddress());
            existingCustomer.setNic(customerDto.getNic());
            existingCustomer.setMobile(customerDto.getMobile());
            existingCustomer.setEmail(customerDto.getEmail());

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
    public String deleteCustomer(int id) throws Exception {
        return customerDao.delete(id) ? "Success" : "Fail";
    }

    public List<CustomerDto> getAllCustomers() throws Exception{
        ArrayList<CustomerEntity>customerEntities= (ArrayList<CustomerEntity>) customerDao.getAll();
        ArrayList<CustomerDto> customerDtoList = new ArrayList<>();

        for (CustomerEntity entity:customerEntities){
            customerDtoList.add(new CustomerDto(entity.getId(), entity.getAddress(), entity.getEmail(),entity.getMobile(), entity.getName(), entity.getNic(), entity.getUpdate(),entity.getDelete()));
        }
        return customerDtoList;
    }




    @Override
    public CustomerDto getCustomer(int id) throws Exception {
        try {
            CustomerEntity customerEntity = customerDao.get(id);

            if (customerEntity == null) {

                String errorMessage = "Customer with ID " + id + " not found!";
                JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
                return null;
            }


            return new CustomerDto(
                    customerEntity.getId(),
                    customerEntity.getAddress(),
                    customerEntity.getEmail(),
                    customerEntity.getMobile(),
                    customerEntity.getName(),
                    customerEntity.getNic(),
                    "Update",
                    "Delete"
            );

        } catch (Exception e) {
            String errorMessage = "Error retrieving customer: " + e.getMessage();
            JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
            throw new Exception(errorMessage, e);
        }
    }


}
