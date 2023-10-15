import config.TestConfig;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class FirstTest extends TestConfig {

    @Test
    public void firstTest() {
        given()
                .log().all()
        .when()
                .post("/")
        .then()
                .log().all();
    }
}
