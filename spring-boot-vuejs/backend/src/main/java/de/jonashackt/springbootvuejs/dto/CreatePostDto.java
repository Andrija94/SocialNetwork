package de.jonashackt.springbootvuejs.dto;

import org.springframework.web.multipart.MultipartFile;

public class CreatePostDto {


    private String username;
    private MultipartFile picture;
    private String text;

    public CreatePostDto () {

    }

    public CreatePostDto (String username, MultipartFile picture, String text) {
        this.username = username;
        this.picture = picture;
        this.text = text;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public MultipartFile getPicture() {
        return picture;
    }

    public void setPicture(MultipartFile picture) {
        this.picture = picture;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
