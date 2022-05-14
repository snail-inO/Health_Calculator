package project.ood.healthcalculator.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import project.ood.healthcalculator.dao.FoodDAO;
import project.ood.healthcalculator.entity.Food;
import project.ood.healthcalculator.entity.Nutrient;
import project.ood.healthcalculator.enums.FoodQueryEnum;
import project.ood.healthcalculator.enums.RestEnum;
import project.ood.healthcalculator.utils.CustomException;

import java.util.List;

@Slf4j
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

    private final FoodDAO foodDAO;

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

        JSONArray foods = res.getJSONArray(FoodQueryEnum.FOODS.getJSONKeyName());
        List<Food> foodRes = foods.toJavaList(Food.class);

        for (int i = 0; i < foods.size(); i++) {
            JSONObject foodsJSONObject = foods.getJSONObject(i);
            Food food = foodRes.get(i);
            JSONArray nutrients = foodsJSONObject.getJSONArray(FoodQueryEnum.NUTRIENTS.getJSONKeyName());
            List<Nutrient> nutrientList = nutrients.toJavaList(Nutrient.class);
            for(int j = 0; j < food.getFoodNutrients().size(); j++) {
                food.getFoodNutrients().get(j).setNutrient(nutrientList.get(j));
            }
        }

        return foodRes;
    }
}
