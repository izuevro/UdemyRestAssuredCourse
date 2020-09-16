import config.TestConfig;
import org.testng.annotations.Test;

import static constants.Constants.Actions.*;
import static io.restassured.RestAssured.given;

public class JsonPlaceHolderTest extends TestConfig {


    @Test
    public void myGetTest() {
        given().queryParam("postId", "1").log().uri()
                .when().get(jsonPlaceHolderGet)
                .then().log().body().statusCode(200);
    }

    @Test
    public void myPutTest() {

        String putBodyJson = "{\n" +
                "\"id\":1,\n" +
                "\"title\":\"foo\",\n" +
                "\"body\":\"bar\",\n" +
                "\"userId\":1\n" +
                "}";

        given().body(putBodyJson).log().uri()
                .when().put(jsonPlaceHolderPut)
                .then().log().body().statusCode(200);
    }

    @Test
    public void myDeleteTest() {
        given().log().uri()
                .when().delete(jsonPlaceHolderDelete)
                .then().log().body();
    }

    @Test
    public void myPostWithJsonTest() {

        String postBodyJson = "{\n" +
                "      \"title\": \"foo\",\n" +
                "      \"body\": \"bar\",\n" +
                "      \"userId\": 1\n" +
                "    }";

        given().body(postBodyJson).log().uri()
                .when().post(jsonPlaceHolderPost)
                .then().spec(responseSpecificationPost).log().body();
    }

    @Test
    public void myPostWithXmlTest() {

        String postBodyXml = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                "<title>foo</title>\n" +
                "<body>bar</body>\n" +
                "<userId>1</userId>";

        given().spec(requestSpecificationXml).body(postBodyXml).log().uri()
                .when().post("")
                .then().spec(responseSpecificationPost).log().body();
    }
}
