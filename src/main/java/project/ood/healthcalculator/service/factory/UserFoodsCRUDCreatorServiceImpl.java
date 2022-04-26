package project.ood.healthcalculator.service.factory;

import org.springframework.stereotype.Service;
import project.ood.healthcalculator.dao.UserFoodsDAO;
import project.ood.healthcalculator.entity.User;
import project.ood.healthcalculator.entity.UserFoods;
import project.ood.healthcalculator.service.UserFoodServiceImpl;
import project.ood.healthcalculator.service.UserServiceImpl;
import project.ood.healthcalculator.service.factory.CRUDCreatorService;

import java.util.List;

@Service
public class UserFoodsCRUDCreatorServiceImpl extends CRUDCreatorService<UserFoods, UserFoodServiceImpl> {
    private final UserFoodsDAO userFoodsDAO;
    private final UserServiceImpl userService;
    private User user;

    public UserFoodsCRUDCreatorServiceImpl(UserFoodsDAO userFoodsDAO, UserServiceImpl userService) {
        this.userService = userService;
        this.userFoodsDAO = userFoodsDAO;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void createCRUDService() {
        if (super.getCRUDService() == null)
            super.setCRUDService(new UserFoodServiceImpl(userFoodsDAO, user));
    }

    @Override
    public UserFoods create(UserFoods userFoods) {
        UserFoods re = super.create(userFoods);
        user.setFoodCounts(user.getFoodCounts() + 1);
        userService.update(user);

        return re;
    }

    @Override
    public void delete(long id) {
        super.delete(id);
        user.setFoodCounts(user.getFoodCounts() - 1);
        userService.update(user);
    }

    public List<UserFoods> retrieveAll() {
        return super.getCRUDService().retrieveAll();
    }
}
