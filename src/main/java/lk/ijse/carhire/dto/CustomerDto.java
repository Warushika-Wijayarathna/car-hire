package lk.ijse.carhire.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Data
public class CustomerDto {
    private int id;
    private String address;
    private int mobile;
    private String name;
    private String nic;
    private String update;
    private String delete;
}
