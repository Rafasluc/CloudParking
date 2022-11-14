package one.digitalinnovationparking.Controller;

import io.restassured.RestAssured;
import one.digitalinnovationparking.Controller.DTO.ParkingCreateDTO;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ParkingControllerTestIT extends AbstractContainerBase {


    @LocalServerPort
    private int randomPort;

    @BeforeEach
    public void setUpTest(){
        System.out.println(randomPort);
        RestAssured.port = randomPort;
    }

    @Test
    void whenFindAllThenCheckResult() {
        RestAssured.given()
                .when()
                .get("/parking")
                .then()
                .statusCode(HttpStatus.OK.value());


    }

    @Test
    void whenCreateThenCheckIsCreated() {

        var createDTO = new ParkingCreateDTO();
        createDTO.setColor("Color OK -- TEST PASS --");
        createDTO.setLicense("License OK -- TEST PASS --");
        createDTO.setModel("Model OK -- TEST PASS --");
        createDTO.setState("State OK -- TEST PASS --");


        RestAssured.given()
                .when()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(createDTO)
                .post("/parking")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("license", Matchers.equalTo("License OK -- TEST PASS --"))
                .body("color", Matchers.equalTo("Color OK -- TEST PASS --"))
                .body("model", Matchers.equalTo("Model OK -- TEST PASS --"))
                .body("state", Matchers.equalTo("State OK -- TEST PASS --"));

    }
}