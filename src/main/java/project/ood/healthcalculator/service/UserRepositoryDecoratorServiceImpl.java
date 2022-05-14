package project.ood.healthcalculator.service;

import project.ood.healthcalculator.entity.EntityInterface;
import project.ood.healthcalculator.entity.User;
import project.ood.healthcalculator.service.CRUD.CRUDService;

import java.util.Collection;

public class UserRepositoryDecoratorServiceImpl<T extends EntityInterface> extends BaseDecoratorServiceImpl {
    private final CRUDService<T> repositoryService;

    public UserRepositoryDecoratorServiceImpl(CRUDService<User> wrappee, CRUDService<T> repositoryService) {
        super(wrappee);
        this.repositoryService = repositoryService;
    }

    @Override
    public T create(Object o) {
        T re = repositoryService.create((T) o);
        long uid = ((T) o).uid();
        User user = (User) super.retrieve(uid);
        user.increaseCount(re.entityType(), 1);
        super.update(user);

        return re;
    }

    @Override
    public void delete(long id) {
        T t = repositoryService.retrieve(id);
        super.delete(id);
        User user = (User) super.retrieve(t.uid());
        user.increaseCount(t.entityType(), -1);
        super.update(user);
    }

    @Override
    public T update(Object o) {
        return repositoryService.update((T) o);
    }

    @Override
    public T retrieve(long id) {
        return repositoryService.retrieve(id);
    }

    @Override
    public Iterable createAll(Iterable entities) {
        T t = ((T) entities.iterator().next());
        int value = ((Collection<?>) entities).size();

        Iterable re = repositoryService.createAll(entities);
        User user = (User) super.retrieve(t.uid());
        user.increaseCount(t.entityType(), value);
        super.update(user);

        return re;
    }
}
