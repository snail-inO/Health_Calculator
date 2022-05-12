package project.ood.healthcalculator.service;

import project.ood.healthcalculator.service.CRUD.CRUDService;

public class BaseDecoratorServiceImpl implements CRUDService {
    private CRUDService wrappee;

    public BaseDecoratorServiceImpl(CRUDService wrappee) {
        this.wrappee = wrappee;
    }

    @Override
    public Object create(Object o) {
        return wrappee.create(o);
    }

    @Override
    public Object retrieve(long id) {
        return wrappee.retrieve(id);
    }

    @Override
    public Object update(Object o) {
        return wrappee.update(o);
    }

    @Override
    public void delete(long id) {
        wrappee.delete(id);
    }

    @Override
    public Iterable createAll(Iterable entities) {
        return wrappee.createAll(entities);
    }
}
