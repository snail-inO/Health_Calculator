package project.ood.healthcalculator.service.CRUD;

import org.springframework.stereotype.Component;
import project.ood.healthcalculator.dao.NutrientCounterDAO;
import project.ood.healthcalculator.entity.NutrientCounter;
import project.ood.healthcalculator.entity.UserRecipe;

import java.util.List;

@Component
public class NutrientCounterServiceImpl extends CRUDServiceImpl<NutrientCounter, NutrientCounterDAO> implements NutrientCounterService {

    public NutrientCounterServiceImpl(NutrientCounterDAO nutrientCounterDAO) {
        super(nutrientCounterDAO);
    }

    @Override
    public List<NutrientCounter> retrieveAll(UserRecipe userRecipe) {
        return super.getBaseDAO().findByUserRecipe(userRecipe);
    }
}
