package de.jonashackt.springbootvuejs.dto;

public class UserDto {
    private String username;
    private String firstName;
    private String lastName;
    private String birhtday;
    private String profilePicture;
    private String gender;
    private String userType;
    private String accountType;

    public UserDto() {

    }
    public UserDto(String username, String firstName, String lastName, String birhtday, String profilePicture, String gender,
    String userType, String accountType) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birhtday = birhtday;
        this.profilePicture = profilePicture;
        this.gender = gender;
        this.userType = userType;
        this.accountType = accountType;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getBirhtday() {
        return birhtday;
    }

    public void setBirhtday(String birhtday) {
        this.birhtday = birhtday;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
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

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

}
