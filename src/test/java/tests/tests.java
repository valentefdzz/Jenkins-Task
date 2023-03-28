package tests;

import apiAppRequest.ApiAppRequest;

import io.restassured.response.Response;

import lombok.SneakyThrows;
import models.Post;
import models.User;

import org.testng.Assert;
import org.testng.annotations.Test;

import org.testng.asserts.SoftAssert;
import utils.ApiUtils;
import utils.ConfigReader;
import utils.JsonUtils;

import java.io.File;
import java.util.List;
import java.util.Map;


public class tests extends ApiAppRequest {

    private Response response;
    ConfigReader configReader = ConfigReader.getInstance();
    SoftAssert softAssert = new SoftAssert();

    @SneakyThrows
    @Test
    public void test() {
        //FIRST TEST
        response = ApiUtils.get(configReader.allPosts());
        softAssert.assertEquals(response.getStatusCode(), 200);

        List<Post> posts = getAllPosts();
        softAssert.assertEquals(posts.size(), 100);

        int i = 0;
        for (Post post : posts) {
            i++;
            int id = post.id;
            softAssert.assertEquals(id, i);
        }

        //SECOND TEST
        response = ApiUtils.get(configReader.postNumber("99"));

        Post actualPost = ApiAppRequest.getPost(99);
        Post newPost = new Post(10, 99, "", "");

        softAssert.assertEquals(actualPost.id, newPost.id);
        softAssert.assertEquals(actualPost.userId, newPost.userId);
        softAssert.assertNotEquals(actualPost.title, null);
        softAssert.assertNotEquals(actualPost.body, null);
        softAssert.assertEquals(response.getStatusCode(), 200);

        //THIRD TEST
        response = ApiUtils.get(configReader.postNumber("150"));

        softAssert.assertEquals(response.getStatusCode(), 404);

        Post post = response.getBody().as(Post.class);
        softAssert.assertEquals(post.body, null);

        //FOURTH TEST
        Post newPost1 = new Post(1, 101, "random x", "random y");
        Post actualPost1 = new Post(1, 101, "random x", "random y");

        response = ApiAppRequest.createPost(actualPost1);
        actualPost1 = response.as(Post.class);

        softAssert.assertEquals(response.getStatusCode(), 201);
        softAssert.assertEquals(actualPost1.toString(), newPost1.toString());

        //FIFTH TEST
        response = ApiUtils.get(configReader.allUsers());
        softAssert.assertEquals(response.getStatusCode(), 200);

        List<User> users = ApiAppRequest.getAllUsers();
        User actualUser = users.get(4);
        User expectedUser = JsonUtils.readJsonFile(configReader.test5route(), User.class);

        softAssert.assertEquals(actualUser.toString(), expectedUser.toString());

        //SIXTH TEST
        response = ApiUtils.get(configReader.userNumber("5"));

        expectedUser = ApiAppRequest.getUser(5);

        softAssert.assertEquals(response.getStatusCode(), 200);
        softAssert.assertEquals(actualUser.toString(), expectedUser.toString());

        softAssert.assertAll();
    }
}
