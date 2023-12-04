package lk.ijse.carhire.service.custom;

import lk.ijse.carhire.dto.CarDto;
import lk.ijse.carhire.service.SuperService;

import java.util.List;

public interface CarService extends SuperService {
    String addCar(CarDto carDto) throws Exception;
    String updateCar(CarDto carDto) throws Exception;
    String deleteCar(String id) throws Exception;
    CarDto getCar(int id) throws Exception;
    List<CarDto> getAllCar()throws Exception;
}
