package de.ait.sortMaster.api.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ContainerApiNegativeTests extends de.ait.sortMaster.api.config.TestBase {

    @Test
    public void createContainerWithEmptyDataTest() {
        RestAssured.baseURI = "http://localhost:5175/#/";

        String jsonBody = "{ \"name\": \"\", \"color\": \"invalid_color\", \"description\": \"\" }";

        Response response = given()
                .contentType("application/json")
                .body(jsonBody)
                .post("/api/containers")
                .then()
                .extract().response();

        Assert.assertEquals(response.getStatusCode(), 400, "Expected 400 Bad Request for invalid data");
    }
}
