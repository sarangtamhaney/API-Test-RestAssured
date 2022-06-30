package tests.Day3;

import org.json.JSONObject;
import org.testng.annotations.Test;
import utility.ExcelUtility;

import java.io.File;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.equalTo;

public class Day3Test {

    @Test(dataProvider = "getApiEndPointData", dataProviderClass = ExcelUtility.class)
    public void verifyFriendsLastName_FriendsTest(String methodName, String serviceEndpoint, Map<String,String> headerMap, Map<String,String> queryParamMap,
                                                  Map<String,Object> pathParamMap, int statusCode, String responseMessage) {
        given().
                pathParams(pathParamMap).
                when().
                get(serviceEndpoint).
                then().
                assertThat().
                statusCode(statusCode).
                body("lastname", equalTo(responseMessage));
    }

    @Test
    public void verifyFriendsJSONObject() {
        JSONObject friend = new JSONObject();
        friend.put("firstname", "Shubham");
        friend.put("lastname", "Butoliya");
        friend.put("id", 70433);
        friend.put("age", 27);

        given().contentType(JSON).body(friend.toString())
                .when().post("http://localhost:3000/friends")
                .then().assertThat().statusCode(201);

        given().pathParam("id", 70433)
                .when().get("http://localhost:3000/friends/{id}")
                .then().assertThat().statusCode(200).body("lastname", equalTo("Butoliya"));
    }

    @Test
    public void verifyAddDataJSONFile() {
        File file = new File("./src/test/resources/Friends.json");

        given().contentType(JSON).body(file)
                .when().post("http://localhost:3000/friends")
                .then().assertThat().statusCode(201);

        given().pathParam("id", "smita")
                .when().get("http://localhost:3000/friends/{id}")
                .then().assertThat().statusCode(200).body("lastname", equalTo("Shewale"));
    }
}