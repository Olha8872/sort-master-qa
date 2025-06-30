package de.ait.sortMaster.api.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class SearchApiTests extends de.ait.sortMaster.api.config.TestBase {

    @Test
    public void searchItemInContainerTest() {
        RestAssured.baseURI = "http://localhost:5175";

        Response response = given()
                .queryParam("name", "newspaper")
                .when()
                .get("/api/items/search")
                .then()
                .extract().response();

        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");

        String containerName = response.jsonPath().getString("[0].container.name");
        Assert.assertEquals(containerName, "Paper", "Incorrect container returned");
    }
    @Test
    public void searchWithInvalidInputTest() {
        RestAssured.baseURI = "http://localhost:5175";

        Response response = given()
                .queryParam("name", "!@#$%^&*")
                .when()
                .get("/api/items/search")
                .then()
                .extract().response();

                Assert.assertEquals(response.getStatusCode(), 200, "Expected 200 or adjust controller to return 400 on invalid input");
    }
}
