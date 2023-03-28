package apiAppRequest;

import com.google.gson.Gson;
import io.restassured.response.Response;

import utils.ApiUtils;
import models.*;
import utils.ConfigReader;

import java.util.Arrays;
import java.util.List;


public class ApiAppRequest {


    private static final String Base_URI = ConfigReader.getInstance().getBaseUrl();
    static ConfigReader configReader = ConfigReader.getInstance();
    public static Response createPost(Object object) {
        Response response = ApiUtils.post(configReader.allPosts(), object, "Content-Type", "application/json");
        return response;
    }
    public static Post getPost(int postId) {
        String path = String.format(configReader.allPosts()+"%d", postId);
        Response response = ApiUtils.get(path);
        return new Gson().fromJson(response.asString(), Post.class);
    }

    public static List<Post> getAllPosts() {
        Response response = ApiUtils.get(configReader.allPosts());
        return Arrays.asList(new Gson().fromJson(response.asString(), Post[].class));
    }
    public static User getUser(int userId) {
        String path = String.format(configReader.allUsers()+"%d", userId);
        Response response = ApiUtils.get(path);
        return new Gson().fromJson(response.asString(), User.class);
    }
    public static List<User> getAllUsers() {
        Response response = ApiUtils.get(configReader.allUsers());
        return Arrays.asList(new Gson().fromJson(response.asString(), User[].class));
    }
}
