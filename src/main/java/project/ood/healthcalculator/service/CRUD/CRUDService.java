package project.ood.healthcalculator.service.CRUD;

public interface CRUDService<T> {
    T create(T t);
    T retrieve(long id);
    T update(T t);
    void delete(long id);
    <S extends T> Iterable<S> createAll(Iterable<S> entities);
}
