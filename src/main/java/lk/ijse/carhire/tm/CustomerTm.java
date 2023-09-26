package lk.ijse.carhire.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerTm {
    private int id;
    private String address;
    private int mobile;
    private String name;
    private String nic;
    private String update;
    private String delete;
}
