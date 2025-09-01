# Interview Questions and Answers: Java, Spring Boot, SQL


## Java
1. **What is the difference between JDK, JRE, and JVM?**
     - **Answer:** JVM (Java Virtual Machine) runs Java bytecode. JRE (Java Runtime Environment) includes JVM and libraries to run Java applications. JDK (Java Development Kit) includes JRE plus development tools like compiler and debugger.
     - **Example:** When you write and compile Java code, you use the JDK. When you run a Java application, you use the JRE. The JVM executes the bytecode.

2. **What are the main features of Java?**
     - **Answer:** Object-oriented, platform-independent, robust, secure, multithreaded, distributed, portable, high performance.
     - **Example:** Java code written on Windows can run on Linux without modification (platform independence).

3. **Explain OOP concepts in Java.**
     - **Answer:** Encapsulation, Inheritance, Polymorphism, Abstraction.
     - **Example:** A `Car` class can inherit from a `Vehicle` class (inheritance), have private fields (encapsulation), override methods (polymorphism), and expose only necessary methods (abstraction).

4. **What is encapsulation?**
     - **Answer:** Wrapping data and code together as a single unit. Achieved using private variables and public getters/setters.
     - **Example:**
         ```java
         public class Person {
                 private String name;
                 public String getName() { return name; }
                 public void setName(String name) { this.name = name; }
         }
         ```

5. **What is inheritance?**
     - **Answer:** Mechanism where one class acquires properties and behaviors of another. Promotes code reuse.
     - **Example:**
         ```java
         public class Animal {}
         public class Dog extends Animal {}
         ```

6. **What is polymorphism?**
     - **Answer:** Ability of an object to take many forms. Achieved via method overloading and overriding.
     - **Example:**
         ```java
         class Animal { void sound() { System.out.println("Animal sound"); } }
         class Dog extends Animal { void sound() { System.out.println("Bark"); } }
         ```

7. **What is abstraction?**
     - **Answer:** Hiding implementation details and showing only functionality. Achieved using abstract classes and interfaces.
     - **Example:**
         ```java
         abstract class Shape { abstract void draw(); }
         class Circle extends Shape { void draw() { System.out.println("Draw Circle"); } }
         ```

8. **Difference between abstract class and interface?**
     - **Answer:** Abstract class can have method implementations, interfaces cannot (until Java 8 default methods). A class can implement multiple interfaces but extend only one abstract class.
     - **Example:**
         ```java
         interface Flyable { void fly(); }
         abstract class Bird { void eat() { } }
         class Sparrow extends Bird implements Flyable { public void fly() { } }
         ```

9. **What is a constructor?**
     - **Answer:** Special method used to initialize objects. Has same name as class, no return type.
     - **Example:**
         ```java
         public class Car {
                 public Car() { System.out.println("Car created"); }
         }
         ```

10. **What is the difference between == and .equals()?**
        - **Answer:** == compares references, .equals() compares values.
        - **Example:**
            ```java
            String a = new String("hello");
            String b = new String("hello");
            System.out.println(a == b); // false
            System.out.println(a.equals(b)); // true
            ```

11. **What is a static variable and method?**
        - **Answer:** Belongs to the class, not instances. Shared among all objects.
        - **Example:**
            ```java
            class Counter {
                    static int count = 0;
                    Counter() { count++; }
            }
            ```

12. **What is final keyword?**
        - **Answer:** Used to restrict modification. Final variable cannot change, final method cannot be overridden, final class cannot be extended.
        - **Example:**
            ```java
            final int MAX = 100;
            final class A {}
            class B extends A {} // Error
            ```

13. **What is the use of super keyword?**
        - **Answer:** Refers to parent class object. Used to access parent methods/constructors.
        - **Example:**
            ```java
            class Animal { void eat() { } }
            class Dog extends Animal { void eat() { super.eat(); } }
            ```

14. **What is method overloading and overriding?**
        - **Answer:** Overloading: same method name, different parameters. Overriding: subclass provides specific implementation for parent method.
        - **Example:**
            ```java
            class Math {
                    int add(int a, int b) { return a + b; }
                    double add(double a, double b) { return a + b; }
            }
            class Child extends Math {
                    int add(int a, int b) { return a + b + 1; }
            }
            ```

15. **What is exception handling?**
        - **Answer:** Mechanism to handle runtime errors. Uses try, catch, finally, throw, throws.
        - **Example:**
            ```java
            try { int a = 5/0; } catch (ArithmeticException e) { System.out.println("Error"); }
            ```

16. **Difference between checked and unchecked exceptions?**
        - **Answer:** Checked exceptions are checked at compile time, unchecked at runtime.
        - **Example:**
            - Checked: IOException, SQLException
            - Unchecked: NullPointerException, ArithmeticException

17. **What is a thread?**
        - **Answer:** Smallest unit of execution. Java supports multithreading.
        - **Example:**
            ```java
            class MyThread extends Thread { public void run() { System.out.println("Running"); } }
            ```

18. **How to create a thread in Java?**
        - **Answer:** Extend Thread class or implement Runnable interface.
        - **Example:**
            ```java
            new Thread(new Runnable() { public void run() { System.out.println("Runnable"); } }).start();
            ```

19. **What is synchronization?**
        - **Answer:** Mechanism to control access to shared resources in multithreading.
        - **Example:**
            ```java
            synchronized void increment() { /* thread-safe code */ }
            ```

20. **What is the difference between ArrayList and LinkedList?**
        - **Answer:** ArrayList is backed by array, fast for random access. LinkedList is backed by nodes, fast for insert/delete.
        - **Example:** Use ArrayList for storing user IDs, LinkedList for implementing a queue.

21. **What is HashMap?**
        - **Answer:** Stores key-value pairs. Allows one null key and multiple null values. Not thread-safe.
        - **Example:**
            ```java
            HashMap<String, Integer> map = new HashMap<>();
            map.put("apple", 1);
            ```

22. **What is the difference between HashMap and Hashtable?**
        - **Answer:** Hashtable is synchronized, does not allow null keys/values. HashMap is not synchronized, allows nulls.
        - **Example:** Use HashMap for non-threaded applications, Hashtable for legacy thread-safe code.

23. **What is the use of Collections API?**
        - **Answer:** Provides classes and interfaces for storing and manipulating groups of data.
        - **Example:** List, Set, Map, Queue are part of Collections API.

24. **What is garbage collection?**
        - **Answer:** Automatic memory management. Removes unused objects.
        - **Example:** When an object is no longer referenced, Java’s garbage collector frees its memory.

25. **What is the purpose of the transient keyword?**
        - **Answer:** Prevents serialization of a variable.
        - **Example:**
            ```java
            class User implements Serializable {
                    transient String password;
            }
            ```

### Additional Java Questions (with examples)
1) What makes String immutable and why does it matter?
- Answer: String’s value is stored in a final char[] (since Java 9, a byte[] with coder) and String itself is final; immutability enables string interning, safe sharing, and security (e.g., for class loading, URLs).
- Example: Using the string pool avoids duplicate objects: String a = "hello"; String b = "hello"; (a == b) is true.

2) StringBuilder vs StringBuffer?
- Answer: Both build strings efficiently; StringBuffer is synchronized (thread-safe) and slower; StringBuilder is not synchronized and faster.
- Example: Prefer StringBuilder when concatenating in single-threaded code inside loops.

3) Why must equals and hashCode be consistent?
- Answer: Collections like HashMap/HashSet rely on hashCode for bucket selection; if equals says two objects are equal, they must have the same hashCode.
- Example:
```java
class ProductKey { String sku; 
    public boolean equals(Object o){ /* compare sku */ }
    public int hashCode(){ return Objects.hash(sku); }
}
```

4) Comparable vs Comparator?
- Answer: Comparable defines natural ordering in the class; Comparator defines external ordering.
- Example: Products sort by price using Comparator.comparing(Products::getPrice).

5) Generics: PECS rule (Producer Extends, Consumer Super)
- Answer: Use ? extends T when reading (producer), ? super T when writing (consumer).
- Example:
```java
void copy(List<? extends Number> src, List<? super Number> dst){ for (Number n: src) dst.add(n); }
```

6) What is type erasure?
- Answer: Generic type information is erased at runtime; only raw types remain.
- Example: You cannot do `if (list instanceof List<String>)` — use `instanceof List` then check elements.

7) When to use Optional and when not to?
- Answer: Use as a return type to indicate absence; avoid in fields, parameters, and collections.
- Example: `Optional<Users> findByEmail(String email)` in repository API.

8) Streams map/filter/reduce basics
- Answer: Declarative processing over collections with internal iteration.
- Example: Average product price: `double avg = list.stream().mapToDouble(Products::getPrice).average().orElse(0);`

9) Caveats of parallel streams
- Answer: Not always faster; overhead and contention; avoid mutating shared state.
- Example: Use parallel streams for CPU-bound, large datasets with stateless operations.

10) Functional interfaces and method references
- Answer: Interfaces with a single abstract method (SAM). Method references are shorthand for lambdas.
- Example: `Runnable r = this::runTask;`

11) BigDecimal for money
- Answer: Avoid double for currency due to precision issues; use BigDecimal with correct MathContext and scale.
- Example: `price.multiply(new BigDecimal("0.90")).setScale(2, RoundingMode.HALF_UP);`

12) java.time API over legacy Date
- Answer: Immutable, thread-safe, clearer types (Instant, LocalDateTime, ZonedDateTime).
- Example: `ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));`

13) volatile vs Atomic types
- Answer: volatile gives visibility but not atomicity for compound ops; AtomicInteger provides atomic operations.
- Example: `AtomicInteger counter = new AtomicInteger(); counter.incrementAndGet();`

14) ConcurrentHashMap vs HashMap
- Answer: ConcurrentHashMap is thread-safe with segmenting/striped locks; HashMap is not thread-safe.
- Example: Use ConcurrentHashMap for caches in multi-threaded services.

15) ExecutorService and CompletableFuture
- Answer: ExecutorService manages thread pools; CompletableFuture composes async tasks.
- Example:
```java
CompletableFuture.supplyAsync(() -> repo.findById(1L))
        .thenApply(opt -> opt.orElseThrow())
        .thenAccept(service::process);
```

16) Try-with-resources
- Answer: Automatically closes resources implementing AutoCloseable.
- Example:
```java
try (BufferedReader br = Files.newBufferedReader(Path.of("file.txt"))) { br.lines().forEach(System.out::println); }
```

17) Reflection and annotations
- Answer: Inspect/modify classes at runtime; annotations provide metadata.
- Example: Custom @Mask annotation to hide PII in logs processed via reflection.

18) Enum strategy pattern
- Answer: Use enum constants implementing behavior.
- Example:
```java
enum Discount { NONE{BigDecimal apply(BigDecimal p){return p;}}, FESTIVE{BigDecimal apply(BigDecimal p){return p.multiply(new BigDecimal("0.9"));}}; abstract BigDecimal apply(BigDecimal p); }
```

## Spring Boot
26. **What is Spring Boot?**
    - **Answer:** Framework for rapid development of Spring applications with minimal configuration.
    - **Example:** Building a REST API for products with embedded Tomcat and auto-configured JPA to connect to MySQL in minutes.

27. **What is @SpringBootApplication annotation?**
    - **Answer:** Combines @Configuration, @EnableAutoConfiguration, and @ComponentScan.
    - **Example:** In `InApplication`, this single annotation auto-discovers your `controller`, `service`, `repository` packages.

28. **What is dependency injection?**
    - **Answer:** Design pattern where dependencies are injected by the framework, not created by the class itself.
    - **Example:** Inject `ProductRepo` into `ProductService` via constructor instead of `new ProductRepo()`.

29. **What is @Autowired?**
    - **Answer:** Used for automatic dependency injection.
    - **Example:** `@Autowired private EmailService emailService;` to send registration emails without manual instantiation.

30. **What is a Bean in Spring?**
    - **Answer:** Object managed by Spring IoC container.
    - **Example:** A `PasswordEncoder` bean defined with `@Bean` in `SecurityConfig` reused across auth logic.

31. **What is the difference between @Component, @Service, @Repository?**
    - **Answer:** All are stereotypes for beans. @Service for service layer, @Repository for data access, @Component is generic.
    - **Example:** Annotate `ProductService` with `@Service` and `ProductRepo` with `@Repository` to get proper exception translation and semantics.

32. **What is application.properties?**
    - **Answer:** Configuration file for Spring Boot application settings.
    - **Example:** Store DB URL, credentials, and `spring.jpa.hibernate.ddl-auto=update` to auto-sync schema in dev.

33. **How do you handle exceptions in Spring Boot?**
    - **Answer:** Using @ControllerAdvice or @RestControllerAdvice.
    - **Example:** A `GlobalExceptionHandler` converting `EntityNotFoundException` to 404 with a JSON body.

34. **What is Spring Data JPA?**
    - **Answer:** Abstraction over JPA for database operations using repositories.
    - **Example:** `ProductRepo extends JpaRepository<Products, Long>` gives `findAll`, `save`, `findById` without writing SQL.

35. **What is @Entity annotation?**
    - **Answer:** Marks a class as a JPA entity (table).
    - **Example:** `@Entity class Users { @Id Long id; }` maps to `users` table.

36. **What is @Id annotation?**
    - **Answer:** Marks a field as primary key.
    - **Example:** `@Id @GeneratedValue(strategy = IDENTITY) private Long id;`

37. **What is @RequestMapping?**
    - **Answer:** Maps HTTP requests to handler methods.
    - **Example:** `@RequestMapping("/api/v1/products")` prefixes all endpoints in `ProductController`.

38. **What is @RestController?**
    - **Answer:** Combines @Controller and @ResponseBody for REST APIs.
    - **Example:** Returns `Products` as JSON directly without View templates.

39. **How do you secure a Spring Boot application?**
    - **Answer:** Using Spring Security, JWT, OAuth2, etc.
    - **Example:** Add a JWT filter to validate tokens and secure `/admin/**` endpoints.

40. **What is JWT?**
    - **Answer:** JSON Web Token, used for stateless authentication.
    - **Example:** After login, server returns a JWT; client sends it in `Authorization: Bearer <token>` for each request.

41. **How do you test a Spring Boot application?**
    - **Answer:** Using JUnit, Mockito, and @SpringBootTest.
    - **Example:** Mock `ProductRepo` to test `ProductService` logic without hitting the DB.

42. **What is the use of @Transactional?**
    - **Answer:** Manages transactions automatically for methods.
    - **Example:** In an order placement method, either save order and reduce inventory both succeed or both roll back.

43. **What is the difference between PUT and POST?**
    - **Answer:** PUT is idempotent (replaces resource), POST creates new resource.
    - **Example:** `PUT /products/10` replaces product 10; `POST /products` creates a new product.

44. **How do you connect Spring Boot to a database?**
    - **Answer:** Configure datasource in application.properties and use JPA repositories.
    - **Example:** MySQL config + `spring-boot-starter-data-jpa` dependency for automatic `EntityManager` setup.

45. **What is the use of ModelMapper?**
    - **Answer:** Maps DTOs to entities and vice versa.
    - **Example:** Convert `Register` DTO to `Users` entity before saving.

46. **What is CORS?**
    - **Answer:** Cross-Origin Resource Sharing, controls resource sharing between different domains.
    - **Example:** Allow your React frontend on `http://localhost:3000` to call Spring API on `http://localhost:8080`.

47. **How do you schedule tasks in Spring Boot?**
    - **Answer:** Using @Scheduled annotation.
    - **Example:** Nightly job to expire stale user sessions: `@Scheduled(cron = "0 0 2 * * *")`.

48. **What is the use of profiles in Spring Boot?**
    - **Answer:** Allows different configurations for different environments (dev, prod).
    - **Example:** `application-dev.properties` uses H2; `application-prod.properties` uses MySQL.

49. **How do you externalize configuration in Spring Boot?**
    - **Answer:** Using application.properties, YAML files, environment variables.
    - **Example:** Read SMTP password from environment variable in production instead of hardcoding.

50. **What is actuator in Spring Boot?**
    - **Answer:** Provides production-ready features like monitoring, metrics, health checks.
    - **Example:** Expose `/actuator/health` for k8s liveness/readiness probes.

### Additional Spring Boot Questions (with examples)
1) Bean scopes?
- Answer: singleton (default), prototype, request, session.
- Example: `@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)` for stateful helpers.

2) Lifecycle hooks
- Answer: `@PostConstruct` after dependency injection; `@PreDestroy` before bean destruction.
- Example: Warm up caches in `@PostConstruct` of a service.

3) @ConfigurationProperties
- Answer: Binds hierarchical properties to POJOs; supports validation.
- Example:
```java
@ConfigurationProperties(prefix="app.mail")
@Validated
class MailProps { @NotBlank String host; int port = 25; }
```

4) Conditional beans
- Answer: Create beans only when conditions match (`@ConditionalOnProperty`).
- Example: Enable mock payment gateway in `dev` only.

5) Profiles
- Answer: Switch config/beans per environment using `@Profile("dev")`.
- Example: Use H2 in `dev`, MySQL in `prod`.

6) Validation with @Valid
- Answer: Validate request DTOs using Bean Validation annotations.
- Example: `public ResponseEntity<?> create(@Valid @RequestBody Register dto)`.

7) Caching
- Answer: `@Cacheable`, `@CacheEvict` with cache manager (e.g., Redis) to speed up reads.
- Example: `@Cacheable("product") Products getById(Long id)`.

8) Async methods
- Answer: `@EnableAsync` and `@Async` to run methods in a thread pool.
- Example: Send email asynchronously after user registration.

9) Events
- Answer: Publish domain events with `ApplicationEventPublisher`; handle with `@EventListener`.
- Example: `UserRegisteredEvent` triggers welcome email and analytics logging.

10) WebClient vs RestTemplate
- Answer: WebClient (reactive, non-blocking) replaces RestTemplate (blocking) for new apps.
- Example: `webClient.get().uri(url).retrieve().bodyToMono(Foo.class)`.

11) Feign client
- Answer: Declarative HTTP client integrated with Spring Cloud OpenFeign.
- Example: `@FeignClient(name="catalog") interface CatalogClient { @GetMapping("/items/{id}") Item get(@PathVariable Long id); }`

12) Test slices
- Answer: `@WebMvcTest` for controller layer with mocked services; `@DataJpaTest` for JPA with in-memory DB.
- Example: Fast tests without loading the whole context.

13) Flyway/Liquibase migrations
- Answer: Versioned SQL/changesets applied at startup to evolve schema safely.
- Example: `V1__init.sql` creates tables; `V2__add_index.sql` adds indexes.

14) Logging configuration
- Answer: Tune levels via `application.properties` or `logback-spring.xml`.
- Example: `logging.level.com.newbuy.in=DEBUG` for local debugging.

15) Actuator security
- Answer: Restrict actuator endpoints; don’t expose sensitive endpoints publicly.
- Example: `management.endpoints.web.exposure.include=health,info` and secure with Spring Security.

16) Error handling customization
- Answer: Implement `ErrorController` or customize `ErrorAttributes` for standard error format.
- Example: Return `{code,message,traceId}` for errors.

17) OpenAPI/Swagger
- Answer: Use springdoc-openapi to generate docs.
- Example: Add dependency `springdoc-openapi-starter-webmvc-ui` and open `/swagger-ui.html`.

18) Global CORS config
- Answer: Configure via `WebMvcConfigurer#addCorsMappings` or Spring Security.
- Example: Allow specific origins and methods for your frontend domain.

### Spring Boot: Testing, Scaling, and Caching (with examples)
1) Unit vs Integration vs Slice tests?
- Answer: Unit tests isolate a class with mocks; integration tests boot the context; slice tests load only a layer.
- Example:
```java
@WebMvcTest(ProductController.class)
class ProductControllerTest { @Autowired MockMvc mvc; @MockBean ProductService svc; }
```

2) MockMvc vs TestRestTemplate vs WebTestClient?
- Answer: MockMvc for MVC slice without server; TestRestTemplate for full HTTP with random port; WebTestClient for reactive.
- Example:
```java
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApiIT { @Autowired TestRestTemplate rest; }
```

3) Mockito basics in Spring tests?
- Answer: Use `@MockBean` to replace a bean in the context; stub with `when(...).thenReturn(...)`.
- Example:
```java
@WebMvcTest
class CtlTest { @MockBean ProductService svc; @Autowired MockMvc mvc; }
```

4) Testcontainers for real DB integration?
- Answer: Spin up throwaway DB containers for repeatable tests.
- Example:
```java
@Testcontainers class RepoIT {
 @Container static PostgreSQLContainer<?> db = new PostgreSQLContainer<>("postgres:15");
}
```

5) H2 for fast JPA tests?
- Answer: In-memory DB via `@DataJpaTest` speeds repository testing.
- Example: `@DataJpaTest class ProductRepoTest { @Autowired ProductRepo repo; }`

6) WireMock for stubbing external APIs?
- Answer: Simulate HTTP services in tests to avoid real calls.
- Example:
```java
WireMockServer wm = new WireMockServer(8089); wm.start();
wm.stubFor(get(urlEqualTo("/users/1")).willReturn(aResponse().withStatus(200)));
```

7) Spring Cloud Contract for consumer‑driven tests?
- Answer: Generate stubs from contracts ensuring providers meet consumer expectations.

8) Caching with @Cacheable/@CacheEvict?
- Answer: Cache method results; evict on updates to avoid stale data.
- Example:
```java
@Cacheable("product") public Products get(Long id){ return repo.findById(id).orElseThrow(); }
@CacheEvict(value="product", key="#p0.id") public Products save(Products p){ return repo.save(p); }
```

9) Redis cache configuration?
- Answer: Add Redis starter, configure connection, enable caching with `@EnableCaching`.
- Example (properties): `spring.data.redis.host=localhost` `spring.cache.type=redis`.

10) Cache TTL and eviction policy?
- Answer: Set per‑cache TTL; choose LRU/LFU depending on library.
- Example (Java config):
```java
RedisCacheConfiguration conf = RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(10));
```

11) Cache‑aside vs write‑through vs write‑behind?
- Answer: Cache‑aside is most common: app loads from DB then puts to cache; write‑through writes to cache+DB; write‑behind queues DB writes.

12) Prevent cache stampede?
- Answer: Use jittered TTLs, mutex locking, or stale‑while‑revalidate.

13) Horizontal scaling a Spring app?
- Answer: Make services stateless; externalize session (Spring Session), files (S3), and cache (Redis); run multiple instances behind a load balancer.

14) Managing HTTP sessions across nodes?
- Answer: Use Spring Session with Redis to share sessions or go fully stateless with JWT.

15) API rate limiting?
- Answer: Use Bucket4j/Resilience4j; throttle by IP/user.
- Example:
```java
// Bucket4j filter sketch
Bucket bucket = Bucket4j.builder().addLimit(Bandwidth.simple(100, Duration.ofMinutes(1))).build();
```

16) Circuit breaker, retries, timeouts?
- Answer: Use Resilience4j annotations.
- Example:
```java
@Retry(name="catalog") @CircuitBreaker(name="catalog")
public Product fetch(){ return client.get(); }
```

17) Connection pool tuning (HikariCP)?
- Answer: Tune `maximumPoolSize`, `connectionTimeout`, match DB capacity.
- Example: `spring.datasource.hikari.maximum-pool-size=20`.

18) Tomcat thread pool tuning?
- Answer: Increase accept/count for high concurrency; monitor saturation.
- Example: `server.tomcat.threads.max=200` `server.tomcat.accept-count=100`.

19) Async processing for throughput?
- Answer: Use `@Async` or message queues (Kafka) to decouple slow work.

20) Pagination and keyset pagination?
- Answer: Offset pagination with page/size; use keyset (WHERE id < lastId) for deep pages.

21) API versioning strategy?
- Answer: URI version `/v1/...`, header‑based, or content negotiation.

22) Response compression?
- Answer: Enable gzip to reduce payload size.
- Example: `server.compression.enabled=true`.

23) ETags and HTTP caching headers?
- Answer: Add `ETag`/`Cache-Control` to leverage browser/CDN cache; handle 304.

24) Observability (Micrometer + Prometheus)?
- Answer: Export metrics, traces; dashboard latencies and error rates.
- Example: `management.endpoints.web.exposure.include=health,metrics,prometheus`.

25) Centralized config (Spring Cloud Config)?
- Answer: Externalize properties; refresh via `/actuator/refresh` or Bus.

26) Feature flags at scale?
- Answer: Integrate Togglz/Unleash; target users/percentages.

27) Blue/green & canary deployments?
- Answer: Shift traffic gradually; monitor; rollback quickly.

28) Kubernetes probes and readiness?
- Answer: Map to `/actuator/health/liveness` and `/actuator/health/readiness`.

29) Prevent N+1 queries?
- Answer: Use fetch joins, `@EntityGraph`, DTO projections, batch size.
- Example JPA: `@EntityGraph(attributePaths = {"images"}) Optional<Products> findById(Long id);`

30) Securing secrets and key rotation?
- Answer: Store in Azure Key Vault/AWS Secrets Manager; rotate JWT signing keys (kid, JWKS).

## SQL
51. **What is SQL?**
    - **Answer:** Structured Query Language, used to manage and query relational databases.
    - **Example:** Query your `products` table to list items under a price: `SELECT * FROM products WHERE price < 1000;`

52. **What is a primary key?**
    - **Answer:** Unique identifier for a record in a table.
    - **Example:** `id` column in `users` ensures each user is uniquely identified.

53. **What is a foreign key?**
    - **Answer:** Field in one table that refers to primary key in another table.
    - **Example:** `orders.user_id` references `users.id` to know who placed the order.

54. **What is normalization?**
    - **Answer:** Process of organizing data to reduce redundancy.
    - **Example:** Split user addresses into a separate `addresses` table to avoid repeating address fields.

55. **What is denormalization?**
    - **Answer:** Process of combining tables to improve read performance.
    - **Example:** Store `product_name` in `order_items` to avoid extra join in reporting queries.

56. **What is an index?**
    - **Answer:** Data structure that improves query speed.
    - **Example:** Add an index on `email` in `users` to speed up login lookups.

57. **What is a JOIN?**
    - **Answer:** Combines rows from two or more tables based on related columns.
    - **Example:** Get orders with user names: `SELECT o.id, u.name FROM orders o JOIN users u ON o.user_id = u.id;`

58. **Types of JOINs?**
    - **Answer:** INNER JOIN, LEFT JOIN, RIGHT JOIN, FULL JOIN.
    - **Example:** LEFT JOIN to list all users even if they have no orders.

59. **What is GROUP BY?**
    - **Answer:** Groups rows sharing a property so aggregate functions can be applied.
    - **Example:** Total sales per product: `SELECT product_id, SUM(amount) FROM order_items GROUP BY product_id;`

60. **What is HAVING clause?**
    - **Answer:** Used to filter groups after GROUP BY.
    - **Example:** Products with sales over 10k: `... GROUP BY product_id HAVING SUM(amount) > 10000;`

61. **What is a subquery?**
    - **Answer:** Query nested inside another query.
    - **Example:** Find users who made an order: `SELECT * FROM users WHERE id IN (SELECT user_id FROM orders);`

62. **What is a view?**
    - **Answer:** Virtual table based on result of a query.
    - **Example:** A `active_products_view` that filters out discontinued products for analytics tools.

63. **What is a stored procedure?**
    - **Answer:** Precompiled collection of SQL statements.
    - **Example:** A procedure to close a month’s sales and archive data.

64. **What is a transaction?**
    - **Answer:** Sequence of operations performed as a single logical unit.
    - **Example:** Deduct wallet balance and create order in one transaction so both succeed or both fail.

65. **What is ACID property?**
    - **Answer:** Atomicity, Consistency, Isolation, Durability.
    - **Example:** Bank transfer between accounts must be atomic and durable across restarts.

66. **What is the difference between DELETE and TRUNCATE?**
    - **Answer:** DELETE removes rows one by one, can use WHERE. TRUNCATE removes all rows, cannot use WHERE, faster.
    - **Example:** Use TRUNCATE to clear a temp table; use DELETE to remove a specific user.

67. **What is the difference between UNION and UNION ALL?**
    - **Answer:** UNION removes duplicates, UNION ALL does not.
    - **Example:** Combining product lists from two sources preserving duplicates with UNION ALL.

68. **How do you prevent SQL injection?**
    - **Answer:** Use prepared statements, parameterized queries.
    - **Example:** In JPA: `@Query("select u from Users u where u.email = :email")` with `@Param("email")`.

69. **What is a constraint?**
    - **Answer:** Rule applied to columns (e.g., NOT NULL, UNIQUE, PRIMARY KEY).
    - **Example:** UNIQUE constraint on `users.email` to prevent duplicate accounts.

70. **What is the difference between CHAR and VARCHAR?**
    - **Answer:** CHAR is fixed length, VARCHAR is variable length.
    - **Example:** Use CHAR(2) for state codes; VARCHAR for names.

71. **How do you fetch the top 5 records from a table?**
    - **Answer:** `SELECT * FROM table LIMIT 5;` (MySQL) or `SELECT TOP 5 * FROM table;` (SQL Server).
    - **Example:** Get five latest products: `SELECT * FROM products ORDER BY created_at DESC LIMIT 5;`

72. **How do you update data in SQL?**
    - **Answer:** `UPDATE table SET column=value WHERE condition;`
    - **Example:** `UPDATE products SET price = price * 0.9 WHERE category = 'SALE';`

73. **How do you delete data in SQL?**
    - **Answer:** `DELETE FROM table WHERE condition;`
    - **Example:** `DELETE FROM users WHERE last_login < '2022-01-01';`

74. **How do you add a new column to a table?**
    - **Answer:** `ALTER TABLE table ADD column datatype;`
    - **Example:** `ALTER TABLE products ADD COLUMN sku VARCHAR(64) UNIQUE;`

75. **How do you create a table in SQL?**
    - **Answer:** `CREATE TABLE table (id INT PRIMARY KEY, name VARCHAR(50));`
    - **Example:** `CREATE TABLE categories (id BIGINT PRIMARY KEY, name VARCHAR(100) NOT NULL UNIQUE);`

76. **How do you create an index in SQL?**
    - **Answer:** `CREATE INDEX idx_name ON table(column);`
    - **Example:** `CREATE INDEX idx_users_email ON users(email);`

77. **How do you drop a table in SQL?**
    - **Answer:** `DROP TABLE table;`
    - **Example:** Drop staging table after ETL: `DROP TABLE user_staging;`

78. **How do you write a query to find duplicate records?**
    - **Answer:** `SELECT column, COUNT(*) FROM table GROUP BY column HAVING COUNT(*) > 1;`
    - **Example:** Find duplicate emails: `SELECT email, COUNT(*) FROM users GROUP BY email HAVING COUNT(*) > 1;`

79. **How do you get the count of records in a table?**
    - **Answer:** `SELECT COUNT(*) FROM table;`
    - **Example:** `SELECT COUNT(*) FROM orders WHERE status = 'PENDING';`

80. **How do you fetch records with a specific pattern?**
    - **Answer:** `SELECT * FROM table WHERE column LIKE 'A%';`
    - **Example:** Usernames starting with `dev`: `SELECT * FROM users WHERE username LIKE 'dev%';`

## More Advanced Questions
81. **Explain the Singleton design pattern.**
    - **Answer:** Ensures a class has only one instance and provides a global point of access.
    - **Example:** Application-wide `Config` or `Logger` used by multiple services.

82. **What is dependency injection in Spring?**
    - **Answer:** Spring injects dependencies at runtime, promoting loose coupling.
    - **Example:** Injecting `UserRepo` into `UserService` using constructor injection.

83. **How do you handle transactions in Spring Boot?**
    - **Answer:** Using @Transactional annotation.
    - **Example:** Wrapping order creation and payment capture in a single method to avoid partial updates.

84. **What is lazy loading in Hibernate?**
    - **Answer:** Objects are loaded only when accessed, not at query time.
    - **Example:** `@OneToMany(fetch = FetchType.LAZY)` for `Product` -> `ProductImage` to avoid pulling images on product list API.

85. **What is eager loading?**
    - **Answer:** Objects are loaded immediately with the query.
    - **Example:** Use `JOIN FETCH` to load product and images in a detail view.

86. **What is the difference between save() and saveAndFlush() in Spring Data JPA?**
    - **Answer:** save() persists entity, saveAndFlush() persists and flushes changes to DB immediately.
    - **Example:** Use `saveAndFlush` before running a native query that depends on the latest insert.

87. **What is the use of Pageable in Spring Data JPA?**
    - **Answer:** Supports pagination and sorting of query results.
    - **Example:** `productRepo.findAll(PageRequest.of(0, 20, Sort.by("createdAt").descending()))` for product listing.

88. **How do you implement custom queries in Spring Data JPA?**
    - **Answer:** Using @Query annotation or method naming conventions.
    - **Example:** `List<Products> findByNameContainingIgnoreCase(String q);`

89. **What is the difference between GET and POST methods?**
    - **Answer:** GET retrieves data, POST submits data to be processed.
    - **Example:** GET `/products/10` vs POST `/products` with a JSON body to create.

90. **What is the use of ResponseEntity in Spring Boot?**
    - **Answer:** Represents HTTP response, allows setting status, headers, and body.
    - **Example:** `return ResponseEntity.status(HttpStatus.CREATED).body(product);`

91. **What is the difference between @RequestParam and @PathVariable?**
    - **Answer:** @RequestParam extracts query parameters, @PathVariable extracts values from URI path.
    - **Example:** `/products/search?q=phone` (RequestParam) vs `/products/42` (PathVariable).

92. **What is the use of Lombok in Java projects?**
    - **Answer:** Reduces boilerplate code by generating getters, setters, constructors, etc.
    - **Example:** `@Data` on `Products` to auto-generate getters/setters.

93. **What is the difference between commit and rollback in SQL?**
    - **Answer:** Commit saves changes, rollback undoes changes since last commit.
    - **Example:** After successfully saving `order` and `order_items`, commit; on error, rollback both.

94. **What is a deadlock in SQL?**
    - **Answer:** Situation where two or more transactions wait for each other to release locks.
    - **Example:** Two services update rows in opposite order, causing circular wait.

95. **How do you optimize SQL queries?**
    - **Answer:** Use indexes, avoid SELECT *, write efficient joins, use proper WHERE clauses.
    - **Example:** Replace `SELECT *` with explicit columns and add composite index `(user_id, created_at)` for feed queries.

96. **What is the use of EXPLAIN in SQL?**
    - **Answer:** Shows query execution plan for optimization.
    - **Example:** `EXPLAIN SELECT ...` reveals if a table scan is happening due to missing index.

97. **What is the difference between clustered and non-clustered index?**
    - **Answer:** Clustered index sorts table data, non-clustered index is a separate structure.
    - **Example:** In MySQL InnoDB, PRIMARY KEY is clustered; additional indexes are non-clustered.

98. **What is the use of aggregate functions in SQL?**
    - **Answer:** Perform calculations on multiple rows (SUM, AVG, COUNT, MAX, MIN).
    - **Example:** Average order value: `SELECT AVG(total_amount) FROM orders;`

99. **What is the difference between INNER JOIN and OUTER JOIN?**
    - **Answer:** INNER JOIN returns matching rows, OUTER JOIN returns matching and non-matching rows.
    - **Example:** LEFT JOIN users with orders to include users with zero orders for a marketing campaign.

100. **How do you handle null values in SQL?**
    - **Answer:** Use IS NULL, COALESCE, IFNULL, NVL functions.
    - **Example:** `SELECT COALESCE(discount, 0) FROM products;`

---

## SDE‑1 Strategy and Behavioral Readiness

<!-- DSA patterns section removed as requested -->

### Behavioral (STAR) Must-prepare
- Conflict with teammate: Situation, Task, Action, Result; highlight empathy and resolution.
- Tight deadline delivery: Break down tasks, negotiate scope, delivered MVP, metrics improved.
- Bug in production: On-call, rollback/feature flag, root-cause, preventive actions.
- Ownership example: You picked an unowned area, created docs/automation, measurable impact.

### System Design Lite (SDE‑1 depth)
- Design a URL shortener, rate limiter, or file upload service.
- Focus on API design, data model, bottlenecks, and trade-offs; mention caching and pagination.

### Interview Day Checklist
- Restate problem; define inputs/outputs and constraints.
- Think aloud; propose brute force then optimize with a pattern.
- Write clean code: small functions, good names, handle edge cases, test with examples.
- After coding: time/space complexity, potential improvements, production concerns.

---

<!-- DSA cheat sheet removed as requested -->

## 20 System Design Micro Q&A (SDE‑1 Level)
1) How do you design pagination for a products API?
- Use `GET /products?page=2&size=20&sort=createdAt,desc`; prefer keyset/seek pagination for large offsets.

2) How would you cache a read-heavy product catalog?
- CDN for images; Redis cache for product blobs by ID; set TTL and cache invalidation on updates.

3) How do you implement rate limiting for login?
- Token bucket in Redis per IP/user; 5 requests/min; return 429 when exceeded.

4) How to ensure idempotency for payment APIs?
- Require `Idempotency-Key` header; store processed keys with result; return same response on retries.

5) File upload handling for profile pictures?
- Pre-signed URL to object storage (S3/GCS); virus scan asynchronously; store metadata in DB.

6) Logging and tracing in microservices?
- Correlation ID per request; structured logs (JSON); distributed tracing (OpenTelemetry).

7) Health checks in production?
- Liveness: app responding; Readiness: DB dependencies OK; expose via `/actuator/health`.

8) How to roll out a risky feature?
- Feature flags; canary release; monitor metrics/errors; fast rollback.

9) Prevent hot key issues in cache?
- Shard keys, introduce small random TTL jitter, or use local caching layer.

10) Ensure strong passwords and secure storage?
- Enforce policy; hash with bcrypt/argon2; never store plaintext.

11) Handle spikes on a POST /orders endpoint?
- Queue writes (Kafka/SQS), process asynchronously; apply rate limit/backpressure.

12) Avoid N+1 queries in list endpoints?
- Use JOINs or JPA `@EntityGraph`/fetch join; batch fetching.

13) Choosing database indexes?
- Index frequent WHERE/JOIN columns; avoid over-indexing writes; composite index order matters.

14) Serving search/autocomplete?
- Use prefix tries or Elasticsearch; debounce client input; cache recent queries.

15) Securing APIs?
- HTTPS everywhere, JWT/OAuth2, validate inputs, least-privilege DB users, rotate secrets.

16) Images and static content performance?
- Store on CDN, use WebP/AVIF, responsive sizes, cache headers.

17) Reliable background jobs?
- Persistent queues, idempotent handlers, retries with backoff, dead-letter queues.

18) Data migrations without downtime?
- Backfill with dual-writes/read-compat; deploy in phases; feature flags to switch over.

19) Handling PII and compliance?
- Encrypt at rest, mask in logs, access controls, data retention policies.

20) Monitoring what matters?
- RED/USE metrics; alerts on error rate, latency, saturation; SLOs and dashboards.

---

## 5‑Day SDE‑1 Crash Plan (Follow This Exactly)

General rules:
- Daily routine: 3 x (90 min focused coding + 20 min break), then 2 x (60 min review/mock + 10 min break).
- Every session: write tests, note mistakes, and capture patterns in your own words.
- End of day: quick recap + 5 flashcards from this guide.

Day 1 — Foundations + Arrays/Strings
- DSA: Arrays/Strings, Two Pointers, Sliding Window (easy→medium). Deliverable: solve 6–8 problems; track patterns/templates.
- Java Core: OOP pillars, equals/hashCode, Collections vs Arrays; write small examples.
- Spring Boot: App anatomy, Controllers, DTOs, Exception handler. Build a tiny CRUD endpoint.
- SQL: SELECT, WHERE, ORDER BY, LIMIT, LIKE; 10 query drills.
- Mock: 20‑min debug of a failing test you create.

Day 2 — Hash/Stacks/Queues + Data Access
- DSA: Hashing (two‑sum, anagrams), Stack (valid parentheses), Queue (BFS intro). 6–8 problems.
- Java: Exceptions, try‑with‑resources, immutability; write a small utility properly handling exceptions.
- Spring Data JPA: Entities, Repos, pagination/sorting; add findByNameContaining endpoint.
- SQL: JOINs and GROUP BY/HAVING; 10 query drills including aggregates.
- Deliverable: a small feature branch that lists products with pagination and search.

Day 3 — Concurrency Basics + Security
- DSA: Advanced Sliding Window, Prefix Sums; 5–6 problems.
- Java: Threads, Runnable, synchronization; implement a thread‑safe counter demo.
- Spring Security + JWT: Protect an endpoint; write a filter test or walkthrough.
- SQL: Indexing, transactions, isolation levels; write examples and explain when to use which.
- System Design Micro: Design a rate limiter + pagination strategy; write APIs and data model.

Day 4 — Trees/Graphs + Testing/Perf
- DSA: Trees (traversals, LCA), Graphs (BFS/DFS, topo sort). 6–8 problems.
- Java: JVM basics, GC, heap vs stack; note GC tuning basics you can mention.
- Spring Boot: Unit tests with JUnit/Mockito; add a test for a service using a mocked repo.
- SQL: Query optimization with EXPLAIN; rewrite one query 2 ways and compare.
- Mock Interview: 45‑min coding + 15‑min behavioral using STAR.

Day 5 — DP Lite + Final Polish
- DSA: DP (climbing stairs, house robber), Binary Search on answer; 4–5 problems.
- Project Walkthrough: 5‑minute concise story of this repo (auth, layers, notable decisions, trade‑offs).
- Behavioral: Prepare 3 STAR stories (conflict, ownership, on‑call/fix). Practice aloud.
- Resume pass: tighten bullets with metrics, tech stack up top.
- Final drill: 1 easy + 1 medium coding warm‑up; stop 1 hour before interview.

Final 24‑Hour Checklist
- Patterns I can code from memory: two pointers, sliding window, BFS/DFS, stack, basic DP.
- Java: equals/hashCode, collections, exceptions, threading basics.
- Spring Boot: controller→service→repo flow, JPA basics, JWT, error handling.
- SQL: joins, group by/having, indexes, transactions, injection prevention.
- Behavioral: 3 STAR stories, 1 failure story with learnings.

Export to PDF
- If Pandoc is installed, run in PowerShell:
    - `pandoc .\Learning_Summary.md -o .\SDE1_Interview_Revision.pdf`
- Or upload `Learning_Summary.md` to an online Markdown→PDF converter.

# E-commerce Backend Project: Concept-wise Learning Summary

## 1. Spring Boot Fundamentals
**Theory:** Spring Boot is a framework that makes it easy to create stand-alone, production-grade Spring-based applications. It provides auto-configuration, embedded servers (like Tomcat), and reduces boilerplate code, allowing rapid development.

**Complete Example:**
```java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InApplication {
    public static void main(String[] args) {
        SpringApplication.run(InApplication.class, args);
    }
}
```

---
## 2. REST API Development
**Theory:** RESTful APIs use HTTP methods (GET, POST, PUT, DELETE) to perform CRUD operations. Controllers in Spring Boot map URLs to Java methods using annotations like `@RestController`, `@GetMapping`, etc.

**Complete Example:**
```java
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Products> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping
    public Products addProduct(@RequestBody Products product) {
        return productService.saveProduct(product);
    }
}
```

---
## 3. DTOs and Response Handling
**Theory:** DTOs (Data Transfer Objects) are simple objects used to transfer data between layers. They help decouple internal models from external representations and improve security.

**Complete Example:**
```java
public class ApiResponse {
    private boolean success;
    private String message;

    public ApiResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    // Getters and setters
    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
```

---
## 4. Exception Handling
**Theory:** Exception handling ensures that errors are managed gracefully. Spring Boot’s `@RestControllerAdvice` allows centralized handling of exceptions, returning custom error responses.

**Complete Example:**
```java
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleException(Exception ex) {
        ApiResponse response = new ApiResponse(false, ex.getMessage());
        return ResponseEntity.badRequest().body(response);
    }
}
```

---
## 5. Security and Authentication
**Theory:** JWT (JSON Web Token) is used for stateless authentication. Spring Security integrates with JWT to protect endpoints and manage user sessions.

**Complete Example:**
```java
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JwtAuthFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        // Extract JWT from header, validate, set authentication
        // ...existing code...
    }
}
```

---
## 6. Service Layer
**Theory:** The service layer contains business logic and interacts with repositories. It separates business rules from controllers and data access.

**Complete Example:**
```java
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepo productRepo;

    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public List<Products> getAllProducts() {
        return productRepo.findAll();
    }

    public Products saveProduct(Products product) {
        return productRepo.save(product);
    }
}
```

---
## 7. Repository Layer & JPA
**Theory:** Repositories abstract database operations. Spring Data JPA provides CRUD methods and custom queries using interfaces.

**Complete Example:**
```java
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Products, Long> {
    // Custom query methods can be added here
}
```

---
## 8. Model Design
**Theory:** Entity classes represent database tables. Annotations like `@Entity`, `@Id`, and `@GeneratedValue` map fields to columns.

**Complete Example:**
```java
import javax.persistence.*;

@Entity
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}
```

---
## 9. Utility Classes
**Theory:** Utility classes provide reusable static methods for common tasks, improving code organization and reducing duplication.

**Complete Example:**
```java
public class Utils {
    public static String generateRandomString(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int idx = (int) (Math.random() * chars.length());
            sb.append(chars.charAt(idx));
        }
        return sb.toString();
    }
}
```

---
## 10. Application Configuration
**Theory:** The `application.properties` file stores configuration settings for the application, such as database connection, server port, and security settings.

**Complete Example:**
```
spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce
spring.datasource.username=root
spring.datasource.password=yourpassword
server.port=8080
spring.jpa.hibernate.ddl-auto=update
```

---
## 11. Testing
**Theory:** Unit and integration tests ensure code reliability. Spring Boot provides testing support with annotations like `@SpringBootTest` and JUnit.

**Complete Example:**
```java
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class InApplicationTests {
    @Test
    void contextLoads() {
        // Test if application context loads successfully
    }
}
```
