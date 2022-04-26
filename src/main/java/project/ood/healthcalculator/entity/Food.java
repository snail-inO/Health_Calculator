package project.ood.healthcalculator.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Food {
    @Id
    private long fdcId;
    private String description;
    private String lowercaseDescription;
    private String gtinUpc;
    private float servingSize;
    private String servingSizeUnit;
    private String packageWeight;
    @Column(columnDefinition = "TEXT")
    private String ingredients;
    private String dataType;
    private String brandName;
    private String brandOwner;
    @OneToMany(mappedBy = "food", cascade = {CascadeType.REMOVE})
    private List<FoodNutrients> foodNutrients;
    @OneToMany(mappedBy = "food", cascade = {CascadeType.REMOVE})
    private List<UserFoods> userFoods;

    public Food(long fdcId, String description, String lowercaseDescription, String gtinUpc, float servingSize,
                String servingSizeUnit, String packageWeight, String ingredients, String dataType, String brandName,
                String brandOwner, List<FoodNutrients> foodNutrients) {
        this.fdcId = fdcId;
        this.description = description;
        this.lowercaseDescription = lowercaseDescription;
        this.gtinUpc = gtinUpc;
        this.servingSize = servingSize;
        this.servingSizeUnit = servingSizeUnit;
        this.packageWeight = packageWeight;
        this.ingredients = ingredients;
        this.dataType = dataType;
        this.brandName = brandName;
        this.brandOwner = brandOwner;
        this.foodNutrients = foodNutrients;
    }

    public Food() {

    }

    public long getFdcId() {
        return fdcId;
    }

    public void setFdcId(long fdcId) {
        this.fdcId = fdcId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLowercaseDescription() {
        return lowercaseDescription;
    }

    public void setLowercaseDescription(String lowercaseDescription) {
        this.lowercaseDescription = lowercaseDescription;
    }

    public String getGtinUpc() {
        return gtinUpc;
    }

    public void setGtinUpc(String gtinUpc) {
        this.gtinUpc = gtinUpc;
    }

    public float getServingSize() {
        return servingSize;
    }

    public void setServingSize(float servingSize) {
        this.servingSize = servingSize;
    }

    public String getServingSizeUnit() {
        return servingSizeUnit;
    }

    public void setServingSizeUnit(String servingSizeUnit) {
        this.servingSizeUnit = servingSizeUnit;
    }

    public String getPackageWeight() {
        return packageWeight;
    }

    public void setPackageWeight(String packageWeight) {
        this.packageWeight = packageWeight;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandOwner() {
        return brandOwner;
    }

    public void setBrandOwner(String brandOwner) {
        this.brandOwner = brandOwner;
    }

    public List<FoodNutrients> getFoodNutrients() {
        return foodNutrients;
    }

    public void setFoodNutrients(List<FoodNutrients> foodNutrients) {
        this.foodNutrients = foodNutrients;
    }
}
