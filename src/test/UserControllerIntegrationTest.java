@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class UserControllerIntegrationTest {
    @Container static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8");

    // Test CRUD, enriched success, fail (set failure_rate env via Testcontainers)
}
