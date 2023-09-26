package lk.ijse.carhire.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RentDto {
    private int id;
    private String carId;
    private boolean availability;
    private String customerId;
    private Date fromDate;
    private Date toDate;
    private Double perDayRent;
    private Double advancedPayment;
    private Double refundablePayment;
    private Double total;
    private Double balance;

}

