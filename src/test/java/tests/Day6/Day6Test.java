package tests.Day6;

import dtos.Friend;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static java.util.Arrays.asList;

public class Day6Test {


    @Test
    public void verifyJsonSchema() {
        Response response = given().header("Content-Type", "application/Json").get("http://localhost:3000/friends");
        response.prettyPrint();
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema.json"));

    }

}