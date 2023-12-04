package lk.ijse.carhire.TableModel;

import lk.ijse.carhire.entity.CarCategoryEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CarTm {
    private int id;
    private String brand;
    private String model;
    private String plateNo;
    private Double priceperday;
    private int year;
    private CarCategoryEntity carCategoryEntity;
    private String update;
    private String delete;
}
