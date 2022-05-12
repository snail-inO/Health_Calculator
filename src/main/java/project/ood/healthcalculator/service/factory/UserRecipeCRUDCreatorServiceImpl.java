//package project.ood.healthcalculator.service.factory;
//
//import org.springframework.stereotype.Service;
//import project.ood.healthcalculator.entity.User;
//import project.ood.healthcalculator.entity.UserRecipe;
//import project.ood.healthcalculator.service.CRUD.UserRecipeService;
//import project.ood.healthcalculator.service.CRUD.UserRecipeServiceImpl;
//
//import java.util.List;
//
//@Service
//public class UserRecipeCRUDCreatorServiceImpl extends CRUDCreatorService<UserRecipe, UserRecipeServiceImpl> implements UserRecipeService {
//
//
//    public UserRecipeCRUDCreatorServiceImpl(UserRecipeServiceImpl userRecipeService) {
//        super(userRecipeService);
//    }
//
//    @Override
//    public List<UserRecipe> retrieveAll(User user) {
//        return super.getCRUDService().retrieveAll(user);
//    }
//}
