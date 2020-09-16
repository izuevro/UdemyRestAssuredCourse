import config.TestConfig;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;
import java.util.Map;

import static constants.Constants.Actions.swapiGetPeople;
import static constants.Constants.Paths.swapiPath;
import static constants.Constants.Servers.swapiUrl;
import static io.restassured.RestAssured.given;
import static io.restassured.matcher.RestAssuredMatchers.matchesXsd;
import static io.restassured.matcher.RestAssuredMatchers.matchesXsdInClasspath;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

public class FirstTest extends TestConfig {

    @Test
    public void myFirstTest() {
        given().log().uri()
                .when().baseUri(swapiUrl).basePath(swapiPath).get(swapiGetPeople + "1")
                .then().log().body().statusCode(200);
    }

    @Test
    public void getSomeFiledInResponseAssertion() {
        given().log().uri()
                .when().baseUri(swapiUrl).get(swapiPath)
                .then().body("people", equalTo("http://swapi.dev/api/people/")).log().body();
    }

    @Test
    public void getSomeFiledInResponseWithIndexAssertion() {
        given().log().uri()
                .when().baseUri(swapiUrl).get(swapiPath + swapiGetPeople)
                .then()
                .body("count", equalTo(82))
                .body("results.name[0]", equalTo("Luke Skywalker"))
                .log().body();
    }

    @Test
    public void getAllDataFromRequest() {
        Response response = given().log().uri()
                .when().baseUri(swapiUrl).get(swapiPath)
                .then().extract().response();

        String jsonResponseAsString = response.asString();
        System.out.println(jsonResponseAsString);
    }

    @Test
    public void getCookieFromResponse() {
        Response response = given().log().uri()
                .when().baseUri(swapiUrl).get(swapiPath)
                .then().extract().response();

        Map<String, String> allCookie = response.getCookies();
        System.out.println(allCookie);

        String someCookie = response.getCookie("");
        System.out.println(someCookie);
    }

    @Test
    public void getHeadersFromResponse() {
        Response response = given().log().uri()
                .when().baseUri(swapiUrl).get(swapiPath)
                .then().extract().response();

        Headers headers = response.getHeaders();
        System.out.println(headers);
        String contentTypeHeader = response.getHeader("Content-Type");
        System.out.println(contentTypeHeader);
    }

    @Test
    public void validateXmlSchemaFirstType() {

        File file = new File("/Users/romanzuev/IdeaProjects/UdemyRestAssuredCourse/src/main/resources/xmlSchema.xsd");
        given().queryParam("key", "AIzaSyB8BUT1J7hn78FHdx15BUu7JNxxSOunqJU")
                .queryParam("input", "New York")
                .queryParam("inputtype", "textquery")
                .queryParam("fields", "business_status,formatted_address,geometry,icon,name,photos,place_id,plus_code,types")
                .queryParam("language", "ru")
                .log().uri()
                .when().get("https://maps.googleapis.com/maps/api/place/findplacefromtext/xml")
                .then().body(matchesXsd(file)).log().body();
    }

    @Test
    public void validateXmlSchemaSecondType() {

        given().queryParam("key", "AIzaSyB8BUT1J7hn78FHdx15BUu7JNxxSOunqJU")
                .queryParam("input", "New York")
                .queryParam("inputtype", "textquery")
                .queryParam("fields", "business_status,formatted_address,geometry,icon,name,photos,place_id,plus_code,types")
                .queryParam("language", "ru")
                .log().uri()
                .when().get("https://maps.googleapis.com/maps/api/place/findplacefromtext/xml")
                .then().body(matchesXsdInClasspath("xmlSchema.xsd")).log().body();
    }

    @Test
    public void validateJsonSchemaFirstType() {
        File file = new File("/Users/romanzuev/IdeaProjects/UdemyRestAssuredCourse/src/main/resources/jsonSchema.json");
        given().queryParam("key", "AIzaSyB8BUT1J7hn78FHdx15BUu7JNxxSOunqJU")
                .queryParam("input", "New York")
                .queryParam("inputtype", "textquery")
                .queryParam("fields", "business_status,formatted_address,geometry,icon,name,photos,place_id,plus_code,types")
                .queryParam("language", "ru")
                .log().uri()
                .when().get("https://maps.googleapis.com/maps/api/place/findplacefromtext/json")
                .then().body(matchesJsonSchema(file)).log().body();
    }

    @Test
    public void validateJsonSchemaSecondType() {
        given().queryParam("key", "AIzaSyB8BUT1J7hn78FHdx15BUu7JNxxSOunqJU")
                .queryParam("input", "New York")
                .queryParam("inputtype", "textquery")
                .queryParam("fields", "business_status,formatted_address,geometry,icon,name,photos,place_id,plus_code,types")
                .queryParam("language", "ru")
                .log().uri()
                .when().get("https://maps.googleapis.com/maps/api/place/findplacefromtext/json")
                .then().body(matchesJsonSchemaInClasspath("jsonSchema.json")).log().body();
    }

    @Test
    public void getMapOfElementsWithSomeKey() {
        Response response = given().baseUri(swapiUrl).log().uri().
                when().get(swapiPath + swapiGetPeople);
        System.out.println(response.asString());
        Map<String, ?> someObject = response.path("results.find {it.name = 'Luke Skywalker'}");
        System.out.println(someObject);
    }

    @Test
    public void getSingleElementWithSomeKey() {
        Response response = given().baseUri(swapiUrl).log().uri().
                when().get(swapiPath + swapiGetPeople);
        String url = response.path("results.find {it.name = 'Luke Skywalker'}.url");
        System.out.println(url);
    }

    @Test
    public void getAllElementsWithSomeKey() {
        Response response = given().baseUri(swapiUrl).log().uri().
                when().get(swapiPath + swapiGetPeople);
        List films = response.path("results.findAll {it.films}.name");
        System.out.println(films);
    }
}
