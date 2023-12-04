package lk.ijse.carhire.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name="Car")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CarID", length = 6, nullable = false)
    private int id;

    @Column(name = "PlateNo", length = 10, nullable = false)
    private String plateNo;

    @Column(name = "Brand", length = 10, nullable = false)
    private String brand;

    @Column(name = "Model", length = 10, nullable = false)
    private String model;

    @Column(name="Year", length = 4, nullable = false)
    private int year;

    @Column(name="PriceperDay", length = 10,nullable = false)
    private Double priceperday;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Id", nullable = false)
    private CarCategoryEntity carCategoryEntity;

    @OneToMany(mappedBy = "carEntity", targetEntity = RentEntity.class)
    private List<RentEntity> rentEntities;

    public String getUpdate() {
        return null;
    }

    public String getDelete() {
        return null;
    }
}

