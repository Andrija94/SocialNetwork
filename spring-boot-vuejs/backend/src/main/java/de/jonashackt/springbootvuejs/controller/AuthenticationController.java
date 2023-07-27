package de.jonashackt.springbootvuejs.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.jonashackt.springbootvuejs.domain.User;
import de.jonashackt.springbootvuejs.dto.LoginDto;
import de.jonashackt.springbootvuejs.dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static de.jonashackt.springbootvuejs.service.UserService.loadsUsers;

@RestController
@RequestMapping("api/authentication")
public class AuthenticationController {
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginDto loginDto) throws IOException {
        ArrayList<User> users;
        users = loadsUsers();
        for (User user : users) {
            if (loginDto.getUsername().equals(user.getUsername()) && loginDto.getPassword().equals(user.getPassword())) {
                UserDto udto = new UserDto(user.getUsername(), user.getFirstName(), user.getLastName(), user.getBirthday(),
                        user.getProfilePicture(), user.getGender(), user.getUserType(), user.getaccountType());
                return new ResponseEntity<>(udto, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("incorrect username or password!", HttpStatus.UNAUTHORIZED);
    }


}
