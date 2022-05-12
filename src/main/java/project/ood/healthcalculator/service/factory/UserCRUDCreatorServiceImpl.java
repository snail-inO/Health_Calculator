package project.ood.healthcalculator.service.factory;

import org.springframework.stereotype.Service;
import project.ood.healthcalculator.entity.User;
import project.ood.healthcalculator.service.CRUD.UserServiceImpl;

@Service
public class UserCRUDCreatorServiceImpl extends CRUDCreatorService<User, UserServiceImpl> {

    public UserCRUDCreatorServiceImpl(UserServiceImpl userService) {
        super(userService);
    }

    public User retrieve(String name) {
        return super.getCRUDService().retrieve(name);
    }
}
