package tests.Day4;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.equalTo;

public class Day4Test {

    private static RequestSpecification getRequestSpecification;
    private static RequestSpecification postRequestSpecification;
    private static ResponseSpecification getResponseSpecification;
    private static ResponseSpecification postResponseSpecification;

    @BeforeClass
    public static void createSpecs() {
        postRequestSpecification = new RequestSpecBuilder().setBaseUri("http://localhost:3000/friends").build();
        getRequestSpecification = new RequestSpecBuilder().setBaseUri("http://localhost:3000/friends").build();

        getResponseSpecification = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(JSON).build();
        postResponseSpecification = new ResponseSpecBuilder().expectStatusCode(201).expectContentType(JSON).build();
    }

    @Test
    public void verifyFriendsJSONObject() {
        JSONObject friend = new JSONObject();
        friend.put("firstname", "Shubham");
        friend.put("lastname", "Butoliya");
        friend.put("id", 70433);
        friend.put("age", 27);

        given().contentType(JSON).body(friend.toString()).spec(postRequestSpecification)
                .when().post()
                .then().assertThat().statusCode(201);

        given().pathParam("id", 70433).spec(getRequestSpecification)
                .when().get("/{id}")
                .then().spec(getResponseSpecification).assertThat().body("lastname", equalTo("Butoliya"));
    }

    @Test
    public void verifyAddDataJSONFile() {
        File file = new File("./src/test/resources/Friends.json");

        given().contentType(JSON).body(file).spec(postRequestSpecification)
                .when().post()
                .then().assertThat().statusCode(201);

        given().pathParam("id", "smita").spec(getRequestSpecification)
                .when().get("/{id}")
                .then().spec(getResponseSpecification).assertThat().body("lastname", equalTo("Shewale"));
    }
}