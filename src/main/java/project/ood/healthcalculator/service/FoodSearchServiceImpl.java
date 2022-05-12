package project.ood.healthcalculator.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import project.ood.healthcalculator.dao.FoodDAO;
import project.ood.healthcalculator.entity.Food;
import project.ood.healthcalculator.entity.FoodNutrients;
import project.ood.healthcalculator.entity.Nutrient;
import project.ood.healthcalculator.enums.FoodQueryEnum;
import project.ood.healthcalculator.enums.RestEnum;
import project.ood.healthcalculator.utils.CustomException;

import java.util.ArrayList;
import java.util.List;

@Service
public class FoodSearchServiceImpl implements FoodSearchService {
    private final String URL = "https://api.nal.usda.gov/fdc/v1/foods/";
    private final String KEY = "?api_key=S2MfBCexHuiPBbX197rJlMzadX0zCulIX37ymxd9";
    private final String BY_NAME = "search";
    private final String QUERY = "&query=";
    private final String TYPE = "&dataType=";
    private final String PAGE_SIZE = "&pageSize=25";
    private final String PAGE_NUM = "&pageNumber=";
    private final String SORT = "&sortBy=dataType.keyword";
    private final String ORDER = "&sortOrder=asc";
    private final String TOTAL_PAGES = "totalPages";

    private FoodDAO foodDAO;

    public FoodSearchServiceImpl(FoodDAO foodDAO) {
        this.foodDAO = foodDAO;
    }

    public Food byId(long id) {
        return foodDAO.findById(id).get();
    }

     public List<Food> byName(String name, int pageNum) {
        String url = URL + BY_NAME + KEY + QUERY + name + TYPE + PAGE_SIZE + PAGE_NUM + pageNum + SORT + ORDER;
        RestTemplate restTemplate = new RestTemplate();
        JSONObject res = restTemplate.getForObject(url, JSONObject.class);

        int maxPage = res.getIntValue(TOTAL_PAGES);
        if (pageNum > maxPage)
            throw new CustomException(RestEnum.BAD_REQUEST);
        if (res == null)
            throw new CustomException(RestEnum.NOT_FOUND);
        JSONArray foods = res.getJSONArray(FoodQueryEnum.FOODS.getJSONKeyName());
        if (foods == null)
            throw new CustomException(RestEnum.NOT_FOUND);
        List<Food> foodRes = new ArrayList<>();
        for (int i = 0; i < foods.size(); i++) {
            JSONObject foodsJSONObject = foods.getJSONObject(i);
            Food food = foodsJSONObject.toJavaObject(Food.class);

            JSONArray nutrients = foodsJSONObject.getJSONArray(FoodQueryEnum.NUTRIENTS.getJSONKeyName());
            List<Nutrient> nutrientList = new ArrayList<>();
            List<FoodNutrients> foodNutrientsList = new ArrayList<>();
            for (int j = 0; j < nutrients.size(); j++) {
                JSONObject nutrientJSON = nutrients.getJSONObject(j);
                Nutrient nutrient = nutrientJSON.toJavaObject(Nutrient.class);
                nutrientList.add(nutrient);
                FoodNutrients foodNutrients = nutrientJSON.toJavaObject(FoodNutrients.class);
                foodNutrients.setFood(food);
                foodNutrients.setNutrient(nutrient);
                foodNutrientsList.add(foodNutrients);
            }
            food.setFoodNutrients(foodNutrientsList);
            foodRes.add(food);
        }

        return foodRes;
    }

}
