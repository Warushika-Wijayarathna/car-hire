package lk.ijse.carhire.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@Table(name="Rent")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="RentId", length=6,nullable = false)
    private int id;
    @Column(name="CarId", length = 6,nullable = false)
    private String carId;
    @Column(name="Availability",nullable = false)
    private boolean availability;
    @Column(name="CustomerId",length =6,nullable = false)
    private String customerId;
    @Column(name="From_Date", columnDefinition = "Date")
    private Date fromDate;
    @Column(name="To_Date", columnDefinition = "Date")
    private Date toDate;
    @Column(name="Per_Day_Rent", length=10, nullable = false)
    private Double perDayRent;
    @Column(name="Advanced_Payment", length = 10,nullable = false)
    private Double advancedPayment;
    @Column(name="Refundable_Deposit", length = 10,nullable = false)
    private Double refundablePayment;
    @Column(name="Total",length = 10,nullable = false)
    private Double total;
    @Column(name="Balance",length = 10,nullable = false)
    private Double balance;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CustID", nullable = false)
    private CustomerEntity customerEntity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CarID", nullable = false)
    private CarEntity carEntity;
}
