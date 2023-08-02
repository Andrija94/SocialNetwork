package de.jonashackt.springbootvuejs.domain;

import de.jonashackt.springbootvuejs.dto.RegisterDto;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import static de.jonashackt.springbootvuejs.service.HashPassword.hashWith256;

public class User {
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String birthday;
    private String gender;
    private String userType;
    private String profilePicture;
    private final List<String> pictures = new ArrayList<>();
    private final List<String> friendRequests = new ArrayList<>();
    private final List<String> friends = new ArrayList<>();
    private String accountType;

    public User () {
    }

    public User(String username, String password, String firstName, String lastName, String birthday, String gender, String userType, String profilePicture, String accountType) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.gender = gender;
        this.userType = userType;
        this.profilePicture = profilePicture;
        this.accountType = accountType;
    }

    public User(RegisterDto registerDto) throws NoSuchAlgorithmException {
        this.username = registerDto.getUsername();
        this.password = hashWith256(registerDto.getPassword());
        this.email = registerDto.getEmail();
        this.firstName = registerDto.getFirstName();
        this.lastName = registerDto.getLastName();
        this.birthday = registerDto.getBirthday();
        this.gender = registerDto.getGender();
        this.userType = "USER";
        this.profilePicture = "user.jpg";
        this.accountType = "PUBLIC";
    }

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

    public String getFirstName(){
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

    public String getBirthday() {
        return birthday;
    }
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUserType() {
        return userType;
    }
    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getProfilePicture() {
        return profilePicture;
    }
    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }
    public List<String> getPictures() {
        return pictures;
    }
    public List<String> getFriendRequests () {
        return friendRequests;
    }
    public List<String> getFriends () {
        return friends;
    }
    public String getaccountType() {
        return accountType;
    }

    public void setaccountType(String accountType) {
        this.accountType = accountType;
    }
}
