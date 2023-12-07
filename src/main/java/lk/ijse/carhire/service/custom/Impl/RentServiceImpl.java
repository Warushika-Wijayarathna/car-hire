package lk.ijse.carhire.service.custom.Impl;

import lk.ijse.carhire.dao.DaoFactory;
import lk.ijse.carhire.dao.custom.RentDao;
import lk.ijse.carhire.dto.RentDto;
import lk.ijse.carhire.entity.RentEntity;
import lk.ijse.carhire.service.custom.RentService;

import javax.swing.JOptionPane;
import javafx.collections.FXCollections;

import java.util.List;

public class RentServiceImpl implements RentService {
    RentDao rentDao=(RentDao) DaoFactory.getInstance().getDao(DaoFactory.DaoTypes.RENT);

    @Override
    public String addRent(RentDto rentDto) throws Exception {
        try {
            RentEntity entity = new RentEntity();

            entity.setId(rentDto.getId());
            entity.setAvailability(rentDto.isAvailability());
            entity.setFromDate(rentDto.getFromDate());
            entity.setToDate(rentDto.getToDate());
            entity.setPerDayRent(rentDto.getPerDayRent());
            entity.setAdvancedPayment(rentDto.getAdvancedPayment());
            entity.setRefundablePayment(rentDto.getRefundablePayment());
            entity.setTotal(rentDto.getTotal());
            entity.setBalance(rentDto.getBalance());

            rentDao.add(entity);

            String successMessage = "Rent added successfully with ID: " + entity.getId();
            JOptionPane.showMessageDialog(null, successMessage, "Success", JOptionPane.INFORMATION_MESSAGE);

            return successMessage;
        } catch (Exception e) {
            String errorMessage = "Error adding rent: " + e.getMessage();
            JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
            throw new Exception(errorMessage, e);
        }
    }

    @Override
    public String updateRent(RentDto rentDto) throws Exception {
        try {
            RentEntity entity = new RentEntity();

            entity.setId(rentDto.getId());
            entity.setAvailability(rentDto.isAvailability());
            entity.setFromDate(rentDto.getFromDate());
            entity.setToDate(rentDto.getToDate());
            entity.setPerDayRent(rentDto.getPerDayRent());
            entity.setAdvancedPayment(rentDto.getAdvancedPayment());
            entity.setRefundablePayment(rentDto.getRefundablePayment());
            entity.setTotal(rentDto.getTotal());
            entity.setBalance(rentDto.getBalance());

            rentDao.update(entity);

            String successMessage = "Rent updated successfully with ID: " + entity.getId();
            JOptionPane.showMessageDialog(null, successMessage, "Success", JOptionPane.INFORMATION_MESSAGE);

            return successMessage;
        } catch (Exception e) {
            String errorMessage = "Error updating rent: " + e.getMessage();
            JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
            throw new Exception(errorMessage, e);
        }
    }

    @Override
    public String deleteRent(String id) throws Exception {
        try {
            rentDao.delete(id);

            int choice = JOptionPane.showConfirmDialog(
                    null,
                    "Are you sure you want to delete the rent with ID: " + id + "?",
                    "Confirm Deletion",
                    JOptionPane.YES_NO_OPTION
            );

            if (choice == JOptionPane.YES_OPTION) {
                String successMessage = "Rent deleted successfully with ID: " + id;
                JOptionPane.showMessageDialog(null, successMessage, "Success", JOptionPane.INFORMATION_MESSAGE);
                return successMessage;
            } else {
                return "Rent deletion canceled.";
            }
        } catch (Exception e) {
            String errorMessage = "Error deleting rent: " + e.getMessage();
            JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
            throw new Exception(errorMessage, e);
        }
    }

    @Override
    public RentDto getRent(String id) throws Exception {
        try {
            RentEntity entity = rentDao.get(Integer.parseInt(id));

            RentDto rentDto = new RentDto();
            rentDto.setId(entity.getId());
            rentDto.setAvailability(entity.isAvailability());
            rentDto.setFromDate(entity.getFromDate());
            rentDto.setToDate(entity.getToDate());
            rentDto.setPerDayRent(entity.getPerDayRent());
            rentDto.setAdvancedPayment(entity.getAdvancedPayment());
            rentDto.setRefundablePayment(entity.getRefundablePayment());
            rentDto.setTotal(entity.getTotal());
            rentDto.setBalance(entity.getBalance());

            return rentDto;
        } catch (Exception e) {
            String errorMessage = "Error getting rent: " + e.getMessage();
            JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
            throw new Exception(errorMessage, e);
        }
    }


    @Override
    public List<RentDto> getAllRent() throws Exception {
        return null;
    }

    @Override
    public void updateReturnStatus(int rentId, boolean isReturned) {
        rentDao.updateReturnStatus(rentId, isReturned);
    }

    @Override
    public double rentCost(int carId) {
        try {
            double rentCost = rentDao.rentCost(carId);

            return rentCost;
        } catch (Exception e) {
            e.printStackTrace();
            // Handle any exceptions or log them
            return 0.0; // or throw an exception depending on your application's error handling strategy
        }
    }

}

