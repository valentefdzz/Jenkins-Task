package utils;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ApiUtils {
    public static class GetRequest {

        public Response getRequest(String route) {

            return given()
                    .baseUri(route)
                    .when()
                    .get();
        }
    }
    public static class PostRequest {

        public Response postRequest(String baseUrl, String headerName, String headerType, String argument, Object object) {

            return given().
                    baseUri(baseUrl).
                    header(headerName, headerType).
                    body(object).
                    when().
                    post(argument);
        }
    }
}
