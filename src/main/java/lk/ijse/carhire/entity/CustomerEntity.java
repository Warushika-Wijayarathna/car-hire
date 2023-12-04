package lk.ijse.carhire.entity;


import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name="Customer")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CustID", length = 6, nullable = false)
    private int id;
    @Column(name = "CustName", length = 30, nullable = false)
    private String name;
    @Column(name = "CustAddress", length = 30)
    private String address;
    @Column(name = "NIC", length = 12, nullable = false)
    private String nic;
    @Column(name="MobileNo", length = 10, nullable = false)
    private int mobile;
    @Column(name="Email", length = 225)
    private String email;

    @ToString.Exclude
    @OneToMany(mappedBy = "customerEntity", targetEntity = RentEntity.class)
    private List<RentEntity> rentEntities;

    public String getUpdate() {
        return null;
    }

    public String getDelete() {
        return null;
    }
}
