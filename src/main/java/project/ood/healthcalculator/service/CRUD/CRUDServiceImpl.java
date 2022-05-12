package project.ood.healthcalculator.service.CRUD;

import org.springframework.data.repository.CrudRepository;

public class CRUDServiceImpl<T1, T2 extends CrudRepository<T1, Long>> implements CRUDService<T1>{
    private T2 baseDAO;

    public CRUDServiceImpl(T2 baseDAO) {
        this.baseDAO = baseDAO;
    }

    @Override
    public T1 create(T1 t1) {
        return baseDAO.save(t1);
    }

    @Override
    public T1 retrieve(long id) {
        return baseDAO.findById(id).get();
    }

    @Override
    public T1 update(T1 t1) {
        return baseDAO.save(t1);
    }

    @Override
    public void delete(long id) {
        baseDAO.deleteById(id);
    }

    @Override
    public <S extends T1> Iterable<S> createAll(Iterable<S> entities) {
        return baseDAO.saveAll(entities);
    }

    protected T2 getBaseDAO() {
        return baseDAO;
    }
}
