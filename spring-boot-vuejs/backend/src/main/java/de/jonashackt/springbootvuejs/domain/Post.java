package de.jonashackt.springbootvuejs.domain;

import de.jonashackt.springbootvuejs.dto.PostDto;

import java.util.ArrayList;
import java.util.List;

public class Post {

    private String userID;
    private String id;
    private String picture;
    private String text;

    public List<String> getComments() {
        return comments;
    }



    private final List<String> comments = new ArrayList<>();


    public Post () {

    }
     public Post (String userID, String id, String picture, String text) {
        this.userID = userID;
        this.id = id;
        this.picture = picture;
        this.text = text;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
