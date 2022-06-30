package tests.Day2;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;

import java.util.List;

import static org.hamcrest.Matchers.*;

public class Day2Test {

    @Test
    public void verifyGetAllBookings() {
        Response response = given().log().all().when().get("https://restful-booker.herokuapp.com/booking");
        response.then().assertThat().statusCode(200).log().body();

        JsonPath jsonPath = response.jsonPath();
        List<Integer> bookingId = jsonPath.getList("bookingid");
        for (Integer id : bookingId) {
            System.out.println(id);
        }

        response.then().assertThat().body("bookingid", hasItem(2470));
    }

    @Test
    public void verifyGetBookingsDetails() {
        Response response = given().pathParam("id", 2470).log().all().when().get("https://restful-booker.herokuapp" +
                ".com/booking/{id}");
        response.then().assertThat().statusCode(200).log().body();

        JsonPath jsonPath = response.jsonPath();
        System.out.println(jsonPath.getString("firstname"));
        System.out.println(jsonPath.getString("lastname"));
        System.out.println(jsonPath.getString("totalprice"));

        response.then().assertThat().body("firstname", equalTo("Sally"));
        response.then().assertThat().body("lastname", equalTo("Brown"));
        response.then().assertThat().body("totalprice", equalTo(111));
    }
}
