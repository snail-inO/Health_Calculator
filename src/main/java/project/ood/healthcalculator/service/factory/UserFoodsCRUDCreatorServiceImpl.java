package project.ood.healthcalculator.service.factory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import project.ood.healthcalculator.entity.User;
import project.ood.healthcalculator.entity.UserFoods;
import project.ood.healthcalculator.service.CRUD.UserFoodService;
import project.ood.healthcalculator.service.CRUD.UserFoodServiceImpl;

import java.util.List;

@Slf4j
@Service
public class UserFoodsCRUDCreatorServiceImpl extends CRUDCreatorService<UserFoods, UserFoodServiceImpl> implements UserFoodService {
    private final UserCRUDCreatorServiceImpl userService;

    public UserFoodsCRUDCreatorServiceImpl(UserFoodServiceImpl userFoodService, UserCRUDCreatorServiceImpl userService) {
        super(userFoodService);
        this.userService = userService;
    }

    @Override
    public UserFoods create(UserFoods userFoods) {
        UserFoods re = super.create(userFoods);
        User user = re.getUser();
        log.info("uid: {}, user name: {}, user password: {}", user.getUid(), user.getUserName(), user.getPassword());
        user.setFoodCount(user.getFoodCount() + 1);
        userService.update(user);

        return re;
    }

    @Override
    public void delete(long id) {
        User user = super.getCRUDService().retrieve(id).getUser();
        super.delete(id);
        user.setFoodCount(user.getFoodCount() - 1);
        userService.update(user);
    }

    @Override
    public List<UserFoods> retrieveAll(User user) {
        return super.getCRUDService().retrieveAll(user);
    }
}
