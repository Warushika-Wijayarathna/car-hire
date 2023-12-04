package lk.ijse.carhire.service.custom.Impl;

import javafx.collections.FXCollections;
import lk.ijse.carhire.dao.DaoFactory;
import lk.ijse.carhire.dao.custom.CarCategoryDao;
import lk.ijse.carhire.dto.CarCategoryDto;
import lk.ijse.carhire.dto.CustomerDto;
import lk.ijse.carhire.entity.CarCategoryEntity;
import lk.ijse.carhire.entity.CustomerEntity;
import lk.ijse.carhire.service.custom.CarCategoryService;

import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CarCategoryServiceImpl implements CarCategoryService {
    CarCategoryDao carCategoryDao=(CarCategoryDao) DaoFactory.getInstance().getDao(DaoFactory.DaoTypes.CARCATEGORY);
    @Override
    public String addCarCategory(CarCategoryDto carCategoryDto) throws Exception {
        try {
            CarCategoryEntity entity = new CarCategoryEntity();
            entity.setId(carCategoryDto.getId());
            entity.setType(carCategoryDto.getType());

            carCategoryDao.add(entity);

            String successMessage = "Car category added successfully with ID: " + entity.getId();
            JOptionPane.showMessageDialog(null, successMessage, "Success", JOptionPane.INFORMATION_MESSAGE);

            return successMessage;
        } catch (Exception e) {
            String errorMessage = "Error adding car category: " + e.getMessage();
            JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
            throw new Exception(errorMessage, e);
        }
    }

    @Override
    public String updateCarCategory(CarCategoryDto carCategoryDto) throws Exception {

        try {
            CarCategoryEntity entity = new CarCategoryEntity();
            entity.setId(carCategoryDto.getId());
            entity.setType(carCategoryDto.getType());

            carCategoryDao.update(entity);

            String successMessage = "Car category updated successfully with ID: " + entity.getId();
            JOptionPane.showMessageDialog(null, successMessage, "Success", JOptionPane.INFORMATION_MESSAGE);

            return successMessage;
        } catch (Exception e) {
            String errorMessage = "Error updating car category: " + e.getMessage();
            JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
            throw new Exception(errorMessage, e);
        }
    }

    @Override
    public String deleteCarCategory(int id) throws Exception {
        return carCategoryDao.delete(id) ? "Success" : "Fail";
    }

    @Override
    public CarCategoryDto getCarCategory(String id) throws Exception {
        try {
            CarCategoryEntity entity = carCategoryDao.get(Integer.parseInt(id));

            CarCategoryDto carCategoryDto = new CarCategoryDto();
            carCategoryDto.setId(entity.getId());
            carCategoryDto.setType(entity.getType());

            return carCategoryDto;
        } catch (Exception e) {
            String errorMessage = "Error getting car category: " + e.getMessage();
            JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
            throw new Exception(errorMessage, e);
        }
    }

    @Override
    public List<CarCategoryDto> getAllCarCategory() throws Exception {
        ArrayList<CarCategoryEntity>carCategoryEntities= (ArrayList<CarCategoryEntity>) carCategoryDao.getAll();
        ArrayList<CarCategoryDto> carCategoryDtoArrayList= new ArrayList<>();

        for (CarCategoryEntity entity:carCategoryEntities){
            carCategoryDtoArrayList.add(new CarCategoryDto(entity.getId(), entity.getType(), entity.getUpdate(), entity.getDelete()));
        }
        return carCategoryDtoArrayList;
    }
}

