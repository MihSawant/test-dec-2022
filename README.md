# Test Dec 2022
In this project, the major focus is on Test Containers, as they make writing 
integration tests easier and we can test using any datastore like postgres, mongodb, neo4j etc.

All the thing we need to do is :
1. Add the dependency of the datastore you would like to use, In my case I have used
   MySQL database for testing.

        <dependency>
            <groupId>org.testcontainers</groupId>
            <artifactId>mysql</artifactId>
            <scope>test</scope>
        </dependency>
    
    
2. After that in your test class you can create an instance of MySQLContainer class and in
   the constructor provide the image name which you would like to use from docker
   
    **Now the interesting thing is that if you are using Spring Boot 3.1 then you don't need to specify 
 the dynamic properties for container like in earlier, Just the @ServiceConnection annotation, and it will take care 
 of all the properties and configs and spring will do rest work.**

Now later In my case I have used TestRestTemplate to write all test cases for CRUD.

Using TestContainers really helps to code tests in much easier way, also kind of seperates the two layers of development
and testing.


### Some Useful links:
I have reffered from here, and this blog article and video are very helpful
1. https://piotrminkowski.com/2023/05/26/spring-boot-development-mode-with-testcontainers-and-docker/
2. https://www.youtube.com/watch?v=UuLD9gZmiZU