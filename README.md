# settlements

## Building
### Using maven
Checkout the project and navigate to the root of the project using an appropriate terminal and execute the following command
> mvn clean install

## Running
### Spring boot run
> mvn spring-boot:run

## Setting up some test data
### Using swagger ui
1. Open following URL in web browser
> http://localhost:8080/swagger-ui/index.html
2. Send a POST request for User with any name (dont provide " in the name, else its part of the name)
3. Send GET request for User to see the user created and note the id for the same

## Playing around
1. Send POST request for Order with approparite value for the json attributes (using customer id same as noted above)
2. Send POST request for Payment with approparite value for the json attributes (using order id same as provided above)
3. Send GET request for userLedgerBalance with appropraite customer id to get the current balance for the user
4. Send GET request for orderBalance with appropraite order id to get the current balance for the order
