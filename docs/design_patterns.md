# Applied Design Patterns
## Bridge: Different DAO methods invoked by different CRUD services
![Bridge](./images/Picture1.png)  
![Bridge](./images/Picture2.png)

## Adapter: Json objects convert into java entity objects
In FoodSearchServiceImpl class:  
![Adapter](./images/Picture3.png)

## Singleton: Enum

## Builder: Entity builds object with any parameters
In UserFoodController class:  
![Builder](./images/Picture4.png)

## Composite: JPA entity contains subcomponents
In User class:  
![Composite](./images/Picture5.png)

## Factory: CRUD services (deprecated)
Decorator fits better with CRUD services.

## Decorator: User repository service and share repository services
In UserRecipeController class:  
![Decorator](./images/Picture6.png)

## Facade: services hide complexity from controller
In NutrientCounterController class:  
Using NutrientCalculationService class to hide complexity  
![Facade](./images/Picture7.png)

## Chain of responsibility: login interceptor register in interceptor chain
In MvcConfig class:  
![Chain of responsibility](./images/Picture8.png)

## Strategy: recommendation can apply different kinds of algorithms
![Strategy](./images/Picture9.png)  

## MVC: the whole project is designed with MVC design pattern