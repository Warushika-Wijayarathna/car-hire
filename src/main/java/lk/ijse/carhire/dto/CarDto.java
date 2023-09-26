package lk.ijse.carhire.dto;

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
    private String plateNo;
    private String brand;
    private String model;
    private int year;
    private String type;
    private Double priceperday;

}
