package project.ood.healthcalculator.service.CRUD;

import org.springframework.stereotype.Service;
import project.ood.healthcalculator.dao.UserFoodsDAO;
import project.ood.healthcalculator.entity.User;
import project.ood.healthcalculator.entity.UserFoods;

import java.util.List;

@Service
public class UserFoodServiceImpl extends CRUDServiceImpl<UserFoods, UserFoodsDAO> implements UserFoodService {

    public UserFoodServiceImpl(UserFoodsDAO userFoodsDAO) {
        super(userFoodsDAO);
    }

    public List<UserFoods> retrieveAll(User user) {
        return super.getBaseDAO().findByUser(user);
    }
}
