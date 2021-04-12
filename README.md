# How to run application?
Run the following command from the project root directory

Dev mode

    $ java -jar bin/myretail.jar

Prod profile mode

    $ java -Dspring.profiles.active=prod -jar bin/myretail.jar

# How to compile/build with source code ?
Prerequisites:
- Install and configure jdk 11.0.3
- Install and configure maven 3.5.4

Run maven command to build

      $ mvn clean install

# Run the existing test cases(Unit and Integration tests)

Note: Integration test requires some data to be pre-initialized in Mongo DB.
Initialize the database with following commands:

    $ docker-compose up -d
    $ docker exec -it mongo_db bash
    $ mongo -uroot -ppassword < docker-entrypoint-initdb.d/mongo-init.js

Run Unit and Integration tests

     $ mvn clean test



# Testing GET endpoint("/products/{productId}") 
- Run application the Applicatioon
- Initialize data in the database needed for the integration test from the above steps
- Copy paste the below url in the browser or in postman or run curl command for the GET request, replace the productId to reterive different product details.
  

    $ curl --request GET 'http://localhost:8080/products/12954218'

Available productIds choices in the Database

     productId = [13860428, 54456119, 13264003, 12954218]


#Test PUT endpoint ("/products/{productId}") - 
- Use postman or execute curl command for the PUT request with the following payload:


    $ curl  --request PUT 'http://localhost:8080/products/12954218' --header 'Content-Type:application/json' --data-raw '{"value": 25.90,"currencyCode": "USD"}'