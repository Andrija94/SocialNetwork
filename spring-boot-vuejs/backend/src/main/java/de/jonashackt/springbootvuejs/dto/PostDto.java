package de.jonashackt.springbootvuejs.dto;

import de.jonashackt.springbootvuejs.domain.Post;

import java.util.ArrayList;

public class PostDto {

    private String userID;
    private String picture;
    private String text;
    private  final ArrayList<String> comments = new ArrayList<>();


    public PostDto () {

    }

    public PostDto (String userID, String picture, String text) {
        this.userID = userID;
        this.picture = picture;
        this.text = text;
    }


    public ArrayList<String> getComments() {
        return comments;
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

    public String getUserID() {
        return userID;
    }
    public void setUserID(String username) {
        this.userID = username;
    }

}
