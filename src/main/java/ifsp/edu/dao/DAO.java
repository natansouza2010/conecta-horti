package ifsp.edu.dao;

import java.util.List;

public interface DAO <T,K>{
    boolean insert (T obj);
    T findOne(K key);
    List<T> listAll();
    boolean update(T obj);
    boolean delete(K key);


}

