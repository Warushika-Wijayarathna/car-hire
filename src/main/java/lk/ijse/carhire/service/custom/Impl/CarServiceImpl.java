package lk.ijse.carhire.service.custom.Impl;

import lk.ijse.carhire.dao.DaoFactory;
import lk.ijse.carhire.dao.custom.CarDao;
import lk.ijse.carhire.dto.CarCategoryDto;
import lk.ijse.carhire.dto.CarDto;
import lk.ijse.carhire.entity.CarCategoryEntity;
import lk.ijse.carhire.entity.CarEntity;
import lk.ijse.carhire.service.custom.CarService;

import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;

public class CarServiceImpl implements CarService {
    CarDao carDao=(CarDao) DaoFactory.getInstance().getDao(DaoFactory.DaoTypes.CAR);

    @Override
    public String addCar(CarDto carDto) throws Exception {
        try {
            CarEntity entity = new CarEntity();
            entity.setId(carDto.getId());
            entity.setPlateNo(carDto.getPlateNo());
            entity.setBrand(carDto.getBrand());
            entity.setModel(carDto.getModel());
            entity.setYear(carDto.getYear());
            entity.setCarCategoryEntity(carDto.getCarCategoryEntity());
            entity.setPriceperday(carDto.getPriceperday());

            carDao.add(entity);

            String successMessage = "Car added successfully with ID: " + entity.getId();
            JOptionPane.showMessageDialog(null, successMessage, "Success", JOptionPane.INFORMATION_MESSAGE);

            return successMessage;
        } catch (Exception e) {
            String errorMessage = "Error adding car: " + e.getMessage();
            JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
            throw new Exception(errorMessage, e);
        }
    }


    @Override
    public String updateCar(CarDto carDto) throws Exception {
        try {

            CarEntity entity = new CarEntity();
            entity.setId(carDto.getId());
            entity.setPlateNo(carDto.getPlateNo());
            entity.setBrand(carDto.getBrand());
            entity.setModel(carDto.getModel());
            entity.setYear(carDto.getYear());

            entity.setPriceperday(carDto.getPriceperday());

            carDao.update(entity);

            String successMessage = "Car updated successfully with ID: " + entity.getId();
            JOptionPane.showMessageDialog(null, successMessage, "Success", JOptionPane.INFORMATION_MESSAGE);

            return successMessage;
        } catch (Exception e) {
            String errorMessage = "Error updating car: " + e.getMessage();
            JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
            throw new Exception(errorMessage, e);
        }
    }

    @Override
    public String deleteCar(String id) throws Exception {
        try {

            carDao.delete(Integer.valueOf(id));


            int choice = JOptionPane.showConfirmDialog(
                    null,
                    "Are you sure you want to delete the car with ID: " + id + "?",
                    "Confirm Deletion",
                    JOptionPane.YES_NO_OPTION
            );

            if (choice == JOptionPane.YES_OPTION) {
                String successMessage = "Car deleted successfully with ID: " + id;
                JOptionPane.showMessageDialog(null, successMessage, "Success", JOptionPane.INFORMATION_MESSAGE);
                return successMessage;
            } else {

                return "Car deletion canceled.";
            }
        } catch (Exception e) {

            String errorMessage = "Error deleting car: " + e.getMessage();
            JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
            throw new Exception(errorMessage, e);
        }
    }

    @Override
    public CarDto getCar(int id) throws Exception {
        try {
            CarEntity entity = carDao.get(Integer.parseInt(String.valueOf(id)));

            if (entity == null) {
                String errorMessage = "Car with ID " + id + " not found!";
                JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
                return null;
            }

            CarDto carDto = new CarDto(
                    entity.getId(),
                    entity.getBrand(),
                    entity.getModel(),
                    entity.getPlateNo(),
                    entity.getPriceperday(),
                    entity.getYear(),
                    entity.getCarCategoryEntity(),
                    entity.getUpdate(),
                    entity.getDelete()
            );

            return carDto;
        } catch (Exception e) {
            String errorMessage = "Error getting car: " + e.getMessage();
            JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
            throw new Exception(errorMessage, e);
        }
    }


    @Override
    public List<CarDto> getAllCar() throws Exception {
        ArrayList<CarEntity> carEntities= (ArrayList<CarEntity>) carDao.getAll();
        ArrayList<CarDto> carDtoArrayList= new ArrayList<>();

        for (CarEntity entity:carEntities){
            carDtoArrayList.add(new CarDto(entity.getId(), entity.getBrand(), entity.getModel(), entity.getPlateNo(), entity.getPriceperday(), entity.getYear(), entity.getCarCategoryEntity(), entity.getUpdate(), entity.getDelete()));
        }
        return carDtoArrayList;
    }
}

