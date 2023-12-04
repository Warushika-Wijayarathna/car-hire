package lk.ijse.carhire.service.custom;


import lk.ijse.carhire.dto.CarCategoryDto;
import lk.ijse.carhire.dto.CustomerDto;
import lk.ijse.carhire.service.SuperService;

import java.util.ArrayList;
import java.util.List;

public interface CarCategoryService extends SuperService {
    String addCarCategory(CarCategoryDto carCategoryDto) throws Exception;
    String updateCarCategory(CarCategoryDto carCategoryDto) throws Exception;
    String deleteCarCategory(int id) throws Exception;
    CarCategoryDto getCarCategory(String id) throws Exception;
    List<CarCategoryDto> getAllCarCategory() throws Exception;
}
