package lk.ijse.carhire.dto;

import lk.ijse.carhire.entity.CarCategoryEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CarDto {
    private int id;
    private String brand;
    private String model;
    private String plateNo;
    private Double priceperday;
    private int year;
    private CarCategoryEntity carCategoryEntity;
    private String Update;
    private String Delete;
}
