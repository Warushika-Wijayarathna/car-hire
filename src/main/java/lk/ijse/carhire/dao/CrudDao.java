package lk.ijse.carhire.dao;



import java.util.List;

public interface CrudDao <T,ID>extends SuperDao{
    boolean add(T t) throws Exception;
    boolean update(T t) throws Exception;
    boolean delete(ID id) throws Exception;
    T get(int id) throws Exception;
    List<T> getAll() throws Exception;
}
