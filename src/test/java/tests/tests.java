package tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import models.Post;
import models.User;

import org.testng.Assert;
import org.testng.annotations.Test;

import org.testng.asserts.SoftAssert;
import utils.ApiUtils;
import utils.ConfigReader;

import java.io.File;
import java.io.IOException;


public class tests {

    private static Response response;
    private static final ApiUtils.GetRequest getApiUtils = new ApiUtils.GetRequest();
    private static final ApiUtils.PostRequest postApiUtils = new ApiUtils.PostRequest();

    ConfigReader configReader = ConfigReader.getInstance();
    ObjectMapper objectMapper = new ObjectMapper();
    SoftAssert softAssert = new SoftAssert();

    @Test
    public void test1(){
        response = getApiUtils.getRequest(configReader.allPosts());
        softAssert.assertEquals(response.getStatusCode(), 200);


        Post[] Posts = response.jsonPath().getObject("", Post[].class);

        int i = 0;
        for (Post post : Posts) {
            i++;
            int id = post.id;
            softAssert.assertEquals(id, i);
        }
    }

    @Test
    public void  test2(){
        response = getApiUtils.getRequest(configReader.postNumber("/99"));
        Assert.assertEquals(response.getStatusCode(), 200);

        Post actual = response.jsonPath().getObject("", Post.class);
        Post addpost = new Post(10, 99, "", "");

        softAssert.assertEquals(actual.id, addpost.id);
        softAssert.assertEquals(actual.userId, addpost.userId);
        softAssert.assertNotEquals(actual.title, null);
        softAssert.assertNotEquals(actual.body, null);

    }

    @Test
    public void test3(){
        response = getApiUtils.getRequest(configReader.postNumber("/150"));
        String body = response.body().asString();

        softAssert.assertEquals(response.getStatusCode(), 404);
        softAssert.assertEquals(body, "{}");
    }

   @Test
    public void test4(){
        Post newPost = new Post(1, 101, "random x", "random y");
        Post expected = new Post(1, 101, "random x", "random y");

        response = postApiUtils.postRequest(configReader.getBaseUrl(), "Content-Type", "application/json", "/posts", newPost);
        JsonPath actual = new JsonPath(response.asString());

        response.then().statusCode(201);
        softAssert.assertEquals(expected.body, actual.getString("body"));
        softAssert.assertEquals(expected.title, actual.getString("title"));
        softAssert.assertEquals(expected.id.longValue(), actual.getInt("id"));
    }

    @Test
    public void test5() throws IOException {
        response = getApiUtils.getRequest(configReader.allUsers());
        softAssert.assertEquals(response.getStatusCode(), 200);

        response = getApiUtils.getRequest(configReader.userNumber("/5"));

        User actualUser = response.body().as(User.class);
        User expectedUser = objectMapper.readValue(new File(configReader.test5route()), User.class);

        softAssert.assertEquals(actualUser.toString(), expectedUser.toString());
    }

    @Test
    public void test6() throws IOException {
        response = getApiUtils.getRequest(configReader.userNumber("/5"));

        User actualUser = response.getBody().as(User.class);
        User expectedUser = objectMapper.readValue(new File(configReader.test5route()), User.class);

        softAssert.assertEquals(response.getStatusCode(), 200);
        softAssert.assertEquals(actualUser.toString(), expectedUser.toString());
    }
}
