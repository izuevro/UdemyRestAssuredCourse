package config;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;

import static constants.Constants.RunVariables.path;
import static constants.Constants.RunVariables.server;
import static constants.Constants.Servers.requestbinUrl;

public class TestConfig {

    protected RequestSpecification requestSpecificationJson = new RequestSpecBuilder()
            .addHeader("Content-Type", "application/json")
            .addCookie("")
            .setBaseUri(server)
            .build();

    protected RequestSpecification requestSpecificationXml = new RequestSpecBuilder()
            .addHeader("Content-Type", "application/xml")
            .addCookie("")
            .setBaseUri(requestbinUrl)
            .build();

    protected ResponseSpecification responseSpecificationGet = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .build();

    protected ResponseSpecification responseSpecificationPost = new ResponseSpecBuilder()
            .expectStatusCode(201)
            .build();

    @BeforeClass
    public void setUp() {
        RestAssured.requestSpecification = requestSpecificationJson;
        RestAssured.responseSpecification = responseSpecificationGet;
        RestAssured.baseURI = server;
        RestAssured.basePath = path;
    }
}
