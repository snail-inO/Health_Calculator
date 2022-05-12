//package project.ood.healthcalculator.service.factory;
//
//import org.springframework.stereotype.Service;
//import project.ood.healthcalculator.entity.User;
//import project.ood.healthcalculator.entity.UserMeal;
//import project.ood.healthcalculator.service.CRUD.UserMealService;
//import project.ood.healthcalculator.service.CRUD.UserMealServiceImpl;
//
//import java.util.List;
//
//@Service
//public class UserMealCRUDCreatorServiceImpl extends CRUDCreatorService<UserMeal, UserMealServiceImpl> implements UserMealService {
//
//    public UserMealCRUDCreatorServiceImpl(UserMealServiceImpl userMealService) {
//        super(userMealService);
//    }
//
//    public List<UserMeal> retrieveAll(User user) {
//        return super.getCRUDService().retrieveAll(user);
//    }
//}
