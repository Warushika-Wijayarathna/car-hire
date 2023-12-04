package lk.ijse.carhire.service.custom;


import lk.ijse.carhire.dto.CustomerDto;
import lk.ijse.carhire.service.SuperService;

import java.util.List;

public interface CustomerService extends SuperService {

    String addCustomer(CustomerDto customerDto) throws Exception;

    String updateCustomer(CustomerDto customerDto) throws Exception;

    String deleteCustomer(int id) throws Exception;

    List<CustomerDto> getAllCustomers() throws Exception;

    CustomerDto getCustomer(int id)throws Exception;
}
