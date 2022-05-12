package project.ood.healthcalculator.service.factory;

import project.ood.healthcalculator.service.CRUD.CRUDService;

public class CRUDCreatorService<T1, T2 extends CRUDService<T1>> implements CRUDService<T1>{
    private T2 CRUDService;

    public CRUDCreatorService(T2 CRUDService) {
        this.CRUDService = CRUDService;
    }

    @Override
    public T1 create(T1 t1) {
        return CRUDService.create(t1);
    }

    @Override
    public <S extends T1> Iterable<S> createAll(Iterable<S> entities) {
        return CRUDService.createAll(entities);
    }

    @Override
    public T1 retrieve(long id) {
        return CRUDService.retrieve(id);
    }

    @Override
    public T1 update(T1 t1) {
        return CRUDService.update(t1);
    }

    @Override
    public void delete(long id) {
        CRUDService.delete(id);
    }

    public T2 getCRUDService() {
        return CRUDService;
    }

}
