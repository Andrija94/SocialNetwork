package de.jonashackt.springbootvuejs.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.jonashackt.springbootvuejs.domain.User;
import de.jonashackt.springbootvuejs.dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static de.jonashackt.springbootvuejs.service.UserService.loadsUsers;

@RestController
@RequestMapping("api/user")
public class UserConroller {

    private static final Logger LOG = LoggerFactory.getLogger(UserConroller.class);

    @GetMapping("/get-all")
    public ResponseEntity getAllUsers() throws IOException {
        ArrayList<User> users;
        ArrayList<UserDto> userDtoList = new ArrayList<>();
        users = loadsUsers();
        for (User user : users) {
            UserDto userDto = new UserDto(user.getUsername(), user.getFirstName(), user.getLastName(),
                    user.getBirthday(), user.getProfilePicture(), user.getGender(), user.getUserType(), user.getaccountType());
            userDtoList.add(userDto);
        }
        return new ResponseEntity<>(userDtoList, HttpStatus.OK);
    }
}
