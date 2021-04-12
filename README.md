# How to run application?
Run the following command from the project root directory

Dev mode

    $ java -jar bin/myretail.jar

Prod profile mode

    $ java -Dspring.profiles.active=prod -jar bin/myretail.jar

# How to compile/build with source code ?
Prerequisites-
- maven 3.5.4
- jdk 11.0.3
  
      $ mvn clean install
  
# To run the existing test cases(Unit and Integration tests)
     $ mvn clean test
    
# Test GET endpoint("/products/{productId}") with $productId = {13860428, 54456119, 13264003, 12954218}
After running application  -

    $ docker-compose up

To initialise data(productIds) on db for local testing - 

    $ docker exec -it mongo_db bash

    $ mongo -uadmin -ppassword < docker-entrypoint-initdb.d/mongo-init.js

Example curl for GET request -

    $ curl --request GET 'http://localhost:8080/products/12954218'


#Test PUT endpoint ("/products/{productId}") - this will update the existing product prices with productIds = {13860428, 54456119, 13264003, 12954218}
Example curl for PUT request -

    $ curl  --request PUT 'http://localhost:8080/products/12954218' --header 'Content-Type:application/json' --data-raw '{"value": 25.90,"currencyCode": "USD"}'


    
 