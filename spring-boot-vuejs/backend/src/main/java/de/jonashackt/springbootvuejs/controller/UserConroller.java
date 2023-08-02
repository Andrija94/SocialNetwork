package de.jonashackt.springbootvuejs.controller;

import de.jonashackt.springbootvuejs.domain.User;
import de.jonashackt.springbootvuejs.dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

import static de.jonashackt.springbootvuejs.repositorium.Repo.users;


@RestController
@RequestMapping("api/user")
public class UserConroller {

    private static final Logger LOG = LoggerFactory.getLogger(UserConroller.class);

    @GetMapping("/get-all")
    public ResponseEntity getAllUsers() {
        ArrayList<UserDto> userDtoList = new ArrayList<>();
        for (User user : users) {
            UserDto userDto = new UserDto(user.getUsername(), user.getFirstName(), user.getLastName(),
                    user.getBirthday(), user.getProfilePicture(), user.getGender(), user.getUserType(), user.getaccountType());
            userDtoList.add(userDto);
        }
        return new ResponseEntity<>(userDtoList, HttpStatus.OK);
    }

    @GetMapping("/get-user/{username}")
    public ResponseEntity getUser(@PathVariable String username) {
        User x = null;
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                x = user;
                break;
            }
        }
        if (x == null) return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        UserDto userDto = new UserDto(x);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
}
