package lk.ijse.carhire.dao.custom;

import lk.ijse.carhire.dao.CrudDao;
import lk.ijse.carhire.entity.RentEntity;

public interface RentDao extends CrudDao<RentEntity,String> {
    void updateReturnStatus(int rentalId, boolean isReturned);
    double rentCost(int carId);
}
