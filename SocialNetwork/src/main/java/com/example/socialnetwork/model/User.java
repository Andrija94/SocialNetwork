package com.example.socialnetwork.model;

import com.example.socialnetwork.enums.Gender;
import com.example.socialnetwork.enums.AccountType;
import com.example.socialnetwork.enums.UserType;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String username;
    private String password;
    private String email;
    private UserType userType;
    private String firstName;
    private String lastName;
    private String birthDay;
    private Gender gender;
    private AccountType accountType;
    private String profilePicture;
    private final List<Post> listOfPosts = new ArrayList<>();
    private final List<String> listOfPictures = new ArrayList<>();
    private final List<String> frendRequestList = new ArrayList<>();
    private final List<User> friends = new ArrayList<>();

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public UserType getUserType() {
        return userType;
    }
    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthDay() {
        return birthDay;
    }
    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public Gender getGender() {
        return gender;
    }
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public AccountType getAccountType() {
        return accountType;
    }
    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public String getProfilePicture() {
        return profilePicture;
    }
    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public List<Post> getListOfPosts() {
        return listOfPosts;
    }
    public List<String> getListOfPictures() {
        return listOfPictures;
    }

    public List<String> getFrendRequestList() {
        return frendRequestList;
    }

    public List<User> getFriends() {
        return friends;
    }



//    public static void main(String[] args) {
//        User user = new User();
//        user.getListOfPictures().add("smile.jpg");
//        System.out.println(user.getListOfPictures());
//    }

}


