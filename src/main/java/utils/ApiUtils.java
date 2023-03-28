package utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiUtils {
    private static String BASE_URI = ConfigReader.getInstance().getBaseUrl();

   public static Response get(String path) {
        return RestAssured.given()
                .baseUri(BASE_URI)
                .when()
                .get(path)
                .then()
                .extract()
                .response();
    }

    public static Response post(String path, Object body, String headerName, String headerType) {
        return RestAssured.given()
                .baseUri(BASE_URI)
                .header(headerName, headerType)
                .body(body)
                .when()
                .post(path)
                .then()
                .extract()
                .response();
    }
    public static RequestSpecification getRequestSpecification() {
        return RestAssured.given()
                .baseUri(BASE_URI);
    }
}


