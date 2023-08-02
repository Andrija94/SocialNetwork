package de.jonashackt.springbootvuejs.dto;

import de.jonashackt.springbootvuejs.domain.Post;

import java.util.ArrayList;

public class PostDto {

    private String userID;
    private String picture;
    private String text;
    private ArrayList<String> comments;


    public PostDto () {

    }

    public PostDto (Post p) {
        this.userID = p.getUserID();
        this.picture = p.getPicture();
        this.text = p.getText();
        this.comments = (ArrayList<String>) p.getComments();
    }


    public ArrayList<String> getComments() {
        return comments;
    }
    public void setComments(ArrayList<String> comments) {
        this.comments = comments;
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
