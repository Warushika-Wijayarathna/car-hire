package lk.ijse.carhire.service.custom;


import lk.ijse.carhire.dto.RentDto;
import lk.ijse.carhire.service.SuperService;

import java.util.List;

public interface RentService extends SuperService {
    String addRent(RentDto rentDto) throws Exception;
    String updateRent(RentDto rentDto) throws Exception;
    String deleteRent(String id) throws Exception;
    RentDto getRent(String id) throws Exception;
    List<RentDto> getAllRent()throws Exception;

    void updateReturnStatus(int rentId, boolean isReturned);
    double rentCost(int carId);
}
