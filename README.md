
This is playing around with dropwizard, guice and hibernate.  Based on http://dropwizard.codahale.com/getting-started/

Build and run:

 * mvn packag:xe
 * java -jar target/blue-sample-1.0.jar server src/main/config/dev.yml 
   
Things: 
 * A "hello world" at http://localhost:8080/hello and http://localhost:8080/hello?name=Bob 
 * A DAO at http://localhost:8080/customer
 * One part of the app is built by Guice



