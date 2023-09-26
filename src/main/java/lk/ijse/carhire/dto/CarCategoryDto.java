package lk.ijse.carhire.dto;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CarCategoryDto {

    private int id;
    private String type;
}
