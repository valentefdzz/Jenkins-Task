package models;

import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class Post {

    public Integer userId;
    public Integer id;
    public String title;
    public String body;

    /**
     * No args constructor for use in serialization
     *
     */
    public Post() {
    }

    /**
     *
     * @param id
     * @param title
     * @param body
     * @param userId
     */
    public Post(Integer userId, Integer id, String title, String body) {
        super();
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
    }

}
