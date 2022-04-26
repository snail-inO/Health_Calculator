package project.ood.healthcalculator.service.factory;

public interface CRUDService<T> {
    T create(T t);
    T retrieve(long id);
    T update(T t);
    void delete(long id);
}
