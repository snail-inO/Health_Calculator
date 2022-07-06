# Health_Calculator
[Use cases](./docs/use_cases.md)&emsp;&emsp;&emsp;[Models](./docs/models.md)&emsp;&emsp;&emsp;[Design Patterns](./docs/design_patterns.md)
## Introduction
A Web App that orients different types of users to calculate their dietary intake and analyzes the healthiness of their
diet, plus giving them advice that makes their diet more balanced and healthier.

### Purpose
- Help all kinds of people (athletes, average people, pregnant women, patients, etc.) to build a healthy diet.
- The goal of this project is to build an App that will fit users from amateur to expert since most of the dietary Apps are not friendly to users who do not know about a balanced diet or professionals.
- Many dietary Apps are too complex that they include some other functions (like calorie burn, fitness course, etc.) which make their Apps unprofessional.

### Product Scope
- People who are concerned about health
- Dietitians
- Diet-related Researchers

## Overall Description
### Product Perspective and Functions
#### Core functions
- Calculate dietary intake in a different scope (specific food, meal, recipe, or day)
- Give daily recommended dietary intake based on user type
- Recommend suitable meals based on current recipe and user type

#### User experience features
- User basic functions (login, logout, etc.)
- Recipe sharing
- Save foods, meals, recipes data

#### Utilities
- Recipes, meals, foods search

### Design and Implementation Constraints
- Programming Language: Java
- Project Management: Maven
- Backend Framework: Spring Boot
- API data format: RESTful JSON

### Assumptions and Dependencies
#### Assumptions
- The server can connect to the database
- All the required environments for the project are set up

#### Dependencies
##### API Dependencies
- Food Data API: [USDA FoodData Central API](https://fdc.nal.usda.gov/api-guide.html)

##### Maven Dependencies
- fastJSON
- Spring framework series
- JDBC
- Swagger
- JPA
- Junit

## Usage
The project can be deployed with Docker.
1. Replace “localhost” with “mysql_db” in datasource url of application.yml.
2. Using command line terminal in project root directory
3. Run “docker-compose up” command (In Unix-like environment needs root privilege to run this command)
PS: In Unix-like environment, run.sh and mvnw need to be executable (using “chmod 0755 file” command)

## Test Usage
1. Need local MySQL database
2. Configure database information in application.yml file
3. Run tests on IntelliJ IDEA

## API Documentation
The project uses Swagger API for documentation: <http://localhost:8080/swagger-ui/index.html>

## System Architecture
- Application Architecture: Three-Tier Architecture