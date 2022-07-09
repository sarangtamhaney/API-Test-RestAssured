package tests.Day1;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class Day1Test {

    @Test
    public void verifyGetFriends() {
        given().contentType(JSON).when().get("http://localhost:3000/posts").then().assertThat().statusCode(200);
    }

    @Test
    public void verifyGetPosts() {
        given().contentType(JSON).when().get("http://localhost:3000/posts").then().assertThat().statusCode(200);
    }

    @Test
    public void verifyGetComments() {
        given().contentType(JSON).when().get("http://localhost:3000/comments").then().assertThat().statusCode(200);
    }

    @Test
    public void verifyGetProfile() {
        given().contentType(JSON).when().get("http://localhost:3000/profile").then().assertThat().statusCode(200);
    }

}