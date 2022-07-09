package tests.Day5;

import dtos.Friend;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static java.util.Arrays.asList;

public class Day5Test {

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
    public void verifyGetFriends() {
        List<Friend> friends = asList(given().spec(getRequestSpecification).log().all()
                .when().get().as(Friend[].class));
//        System.out.println(friends);
    }

    @Test
    public void verifyAddFriendsNegative() {
        List<Friend> friends = asList(given().spec(getRequestSpecification).log().all()
                .when().get().as(Friend[].class));

        Friend friend = new Friend();
        friend.setFirstname("duplicate");
        friend.setLastname("friend");
        friend.setAge("20");

        friend.setId(friends.get(0).getId());

        given().contentType(JSON).spec(postRequestSpecification).body(friend)
                .when().post()
                .then().assertThat().statusCode(500);
    }

}