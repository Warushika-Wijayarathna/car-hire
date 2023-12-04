package lk.ijse.carhire.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="Car_Category")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CarCategoryEntity implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id", length = 5, nullable = false)
    private int id;

    @Column(name = "Type", length = 10, nullable = false)
    private String type;

    @ToString.Exclude
    @OneToMany(mappedBy = "carCategoryEntity", targetEntity = CarEntity.class)
    private List<CarEntity> carEntities;

    public String getUpdate() {
        return null;
    }

    public String getDelete() {
        return null;
    }
}
