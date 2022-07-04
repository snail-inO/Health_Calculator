# Use cases
Actors: User, System

## Use case 1: User searching for food data by name
User Goal: Get the food data by name
Scenario:
1. User inputs the food name and requests for search
2. System searches the food name in external USDA database
3. System stores the results of the search in local database
4. System returns the search results

## Use case 2: User searching for food data by id
User Goal: Get the food data by id
Scenario:
1. User inputs the food id and requests for search
2. System searches the food id in local database
3. System returns the search result

## Use case 3: User login
User Goal: Login user account
Scenario:
1. User inputs account and password and requests login
2. System validates the user’s input
3. System logins
    a. (Success Login) System returns user info and records user online status
    b. (Failure Login) System returns the failure reason and goes back to step 1

## Use case 4: User logout
User Goal: Logout current user account
Scenario:
1. User requests logout
2. System confirms user status and logouts user account

## Use case 5: User adding food
User Goal: Store food data into user repository
Scenario:
1. User requests inputs the food id and amount
2. System confirms user’s online status
3. System searches food data in local database
4. System stores the food data

## Use case 6: User constructing their meals
User Goal: Construct meals with certain food combinations
Scenario:
1. User inputs the user food id(s) and other info of the meal
2. System searches user food(s) in user repository
3. System stores the meal data

## Use case 7: User constructing their recipes
User Goal: Construct recipes with certain meals/foods combinations
Scenario:
1. User inputs the user meal id(s) and other info of the recipe
2. System searches user meal(s) in user repository
3. System stores the recipe data

## Use case 8: User setting user information
User Goal: Set user’s information
Scenario:
1. User inputs new user information
2. System confirms user’s online status
3. System updates user’s information

## Use case 9: User requesting analysis for their food/meal/recipe
User Goal: Analyze user’s food/meal/recipe
Scenario:
1. User inputs analyzing type and id
2. System confirms user’s online status
3. System analyzes the recipe
4. System returns the analysis

## Use case 10: User requesting daily recommended dietary intake
User Goal: Get daily recommended dietary intake based on user type
Scenario:
1. User sending the request
2. System confirms user’s online status
3. System searches for the daily recommended dietary intake based on user type
4. System returns the result
## Use case 11: User sharing their meal/recipe
User Goal: Share the target meal/recipe
Scenario:
1. User selects a meal/recipe and requests sharing
2. System confirms user’s online status
3. System sets the meal/recipe sharing status into true

## Use case 12: User browsing shared meal(s)/recipe(s)
User Goal: Get current shared meal/recipe list
Scenario:
1. User requests for the shared meal/recipe list
2. System searches for the meal/recipe in database whose sharing status is true
3. System returns the qualified meals/recipes

## Use case 13: User importing meal/recipe
User Goal: Import shared meal/recipe into user repository
Scenario:
1. User inputs the shared meal/recipe id
2. System confirms user’s online status
3. System searches the meal/recipe in database
4. System checks the sharing status of the meal/recipe
    a. (Sharing status is true) System stores the meal/recipe into user repository
    b. (Sharing status is false) System throws an exception

## Use case 14: User requesting for food/meal/recipe recommendations
User Goal: Get meal recommendations that fit their situation
Scenario:
1. User selects a recipe from their repository
2. User requests for meal recommendations
3. System confirms user’s online status
4. System analyzes the recipes based on user’s information
5. System searches suitable meal based on the analysis
6. System returns the search results
