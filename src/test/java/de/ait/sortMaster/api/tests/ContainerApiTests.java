package de.ait.sortMaster.api.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ContainerApiTests extends de.ait.sortMaster.api.config.TestBase {

    @Test
    public void getAllContainersTest() {
        RestAssured.baseURI = "http://localhost:5175";
        Response response = given().when().get("/api/containers").then().extract().response();

        Assert.assertEquals(response.getStatusCode(), 200, "Status code is not 200");

        int count = response.jsonPath().getList("name").size();
        Assert.assertTrue(count > 0, "No containers found via API");
    }

    @Test
    public void createContainerViaApiTest() {
        RestAssured.baseURI = "http://localhost:5175";

        String jsonBody = "{ \"name\": \"Organic\", \"color\": \"#8B4513\", \"description\": \"Brown container for organic waste\" }";

        Response response = given()
                .contentType("application/json")
                .body(jsonBody)
                .post("/api/containers")
                .then()
                .extract().response();

        Assert.assertEquals(response.getStatusCode(), 200, "Status code is not 200");

    }
}

