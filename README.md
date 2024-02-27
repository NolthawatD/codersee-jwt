# Setup Project
1. LINK: https://start.spring.io/
2. JVM 17
3. Dependencies [Spring Web, JPA, Postgresql, Validation, H2DB]
4. Open project, checking dependencies ( can install again on gradle รูปช้างด้านซ้าย)

# Create Entity (Table)
1. Create new package in com.example.name, package name data
2. import @Entity
3. Import @Table and define name "tableName" or unique column in array
4. In class define attribute @Id, @GeneratedValue @Column ...

# Create Model 
1. New package in data about model for use mapping and config request and response
2. Create Dto for Map to Response to Client
3. Create for Create Request [Define can't be blank and message for return]
4. Create for Update Request [Define req is can nullable]

# Create Repository 
1. Create new package in com.example.name, package name repository
2. Create new interface in package, name TaskRepository (up to u)
3. Import @Repository 
4. Declare Type of Interface is JpaRepository<NameEntity, Long>
5. Write custom method in JpaRepository 
6. [Such, findTaskById(id:Long): dataReturn, @Query for write SQL with nativeQuery or JpaQuery]

# Create RuntimeException
1. Create new package in com.example.name package name exception
2. Create new data class, Name TaskNotFoundException 
3. Import @ResponseStatus and HttpStatus
4. Declare data class and argument for send to RunTimeException()
5. Create new class for BadRequest likely above topic (2)

# Create Service Class
1. Create new package in com.example.name package name service
2. new class TaskService(private val repository: TaskRepository)
3. Declare mapping DTO to response to client
4. Create assign value to entity
5. New fun for CheckingTask Existing and Throw to CustomException
6. New fun for GetAll then map to Dto and Collect to List

# Create Controller
1. Create new package in com.example.name package name controller
2. new class nameController 
3. Import @RestController
4. Import @RequestMapping("api/v1")
5. Use arg a Service
6. Import @GetMapping, @PostMapping, @PatchMapping in Class @GetMapping("all-task")
7. Declare fun for call service 
8. Define param for get request from client like @Valid @RequestBody, @PathVariable 
9. Call Service in Fun Controller

# Create Cors Config
1. Create new package in com.example.name package name cors
2. new class CorsConfig
3. Import @Configuration
4. In class import @Bean 
5. Declare fun for config allow method can be sending request to API or WebService

# Create Database Setup
1. In package resource at application file 
2. can modify to yml or use existing .properties
3. Declare connect Database
      ```
   spring.datasource.url=${DATASOURCE_URL}   
   spring.datasource.username=${DATASOURCE_USERNAME}   
   spring.datasource.password=${DATASOURCE_PASSWORD}   
   spring.datasource.driver-class-name=org.postgresql.Driver   
   spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect   
   spring.jpa.show-sql=true   
   spring.jpa.hibernate.ddl-auto=update   
   spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect   
   ```
OR   
```
   logging:   
   file:   
   name: app.log   
   server:   
   error:   
   include-binding-errors: always   
   include-message: always   
   include-stacktrace: on_param   
   port: ${SERVER_PORT:8080}   
   spring:   
   datasource:   
   username: ${DATASOURCE_USERNAME:sa}   
   password: ${DATASOURCE_PASSWORD:''}   
   url: ${DATASOURCE_URL:jdbc:h2:mem:testdb}   
   driver-class-name: ${DATASOURCE_DRIVER_CLASS_NAME:org.h2.Driver}   
```

4. Above on Top bar can Edit Configuration for setting ENV,[ Set Name, Set Use Class Path to Main ]

## Create JWT
1. Link: https://mvnrepository.com/search?q=jjwt
2. Select to import [ JJWT::API, JJWT: Impl, JJWT::Extensions::Jackson, Spring Boot Starter Security, Spring Security Test]
3. Put implementation to build.gradle.kts file.
4. Click Fetch Dependencies Gradle
5. Define JWT on application.yaml  
```
   jwt:
     key: ${JWT_KEY}
     access-token-expiration: 3600000
     refresh-token-expiration: 86400000
```
6. new package config on com.example.name 
7. Create data class JwtProperties
8. Import @ConfigurationProperties("jwt")
9. Create class Configuration 
10. Import @Configuration, @EnableConfigurationProperties(JwtProperties::class)
11. In package service, Create new TokenService for Generate token
12. In package service, Create new CustomUserDetailsService for setting payload 
13. In package configuration, in Configuration class, Import @Bean 
14. Encode password on UserRepository
15. 