package project.ood.healthcalculator.service.factory;

import org.springframework.stereotype.Service;
import project.ood.healthcalculator.dao.UserMealDAO;
import project.ood.healthcalculator.entity.User;
import project.ood.healthcalculator.entity.UserMeal;
import project.ood.healthcalculator.service.UserMealServiceImpl;
import project.ood.healthcalculator.service.factory.CRUDCreatorService;

import java.util.List;

@Service
public class UserMealCRUDCreatorServiceImpl extends CRUDCreatorService<UserMeal, UserMealServiceImpl> {
    private final UserMealDAO userMealDAO;
    private User user;

    public UserMealCRUDCreatorServiceImpl(UserMealDAO userMealDAO) {
        this.userMealDAO = userMealDAO;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void createCRUDService() {
        if(super.getCRUDService() == null)
            super.setCRUDService(new UserMealServiceImpl(userMealDAO, user));
    }

    public List<UserMeal> retrieveAll() {
        return super.getCRUDService().retrieveAll();
    }
}
