package ma.uiass.eia.pds.gihBackEnd.dao;

import java.util.List;

public interface Dao<T> {
    void create(T t);
    T getById(int id);
    List<T> getAll();
    void delete(int id);
    void update(T t, int id);
}
