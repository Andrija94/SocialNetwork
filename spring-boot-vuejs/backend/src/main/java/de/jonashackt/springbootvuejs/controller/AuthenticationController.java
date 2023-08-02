package de.jonashackt.springbootvuejs.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import de.jonashackt.springbootvuejs.constants.Constants;
import de.jonashackt.springbootvuejs.domain.User;
import de.jonashackt.springbootvuejs.dto.LoginDto;
import de.jonashackt.springbootvuejs.dto.RegisterDto;
import de.jonashackt.springbootvuejs.dto.UserDto;
import de.jonashackt.springbootvuejs.repositorium.Repo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;

import static de.jonashackt.springbootvuejs.repositorium.Repo.users;
import static de.jonashackt.springbootvuejs.repositorium.Repo.writeToFile;
import static de.jonashackt.springbootvuejs.service.HashPassword.hashWith256;


@RestController
@RequestMapping("api/authentication")
public class AuthenticationController {
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginDto loginDto) throws IOException, NoSuchAlgorithmException {
        for (User user : users) {
            if (loginDto.getUsername().equals(user.getUsername()) && hashWith256(loginDto.getPassword()).equals(user.getPassword())) {
                UserDto udto = new UserDto(user);
                return new ResponseEntity<>(udto, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("incorrect username or password!", HttpStatus.UNAUTHORIZED);
    }
    @PostMapping("/register")
    public ResponseEntity registerUser (@RequestBody RegisterDto registerDto) throws JsonProcessingException, FileNotFoundException, NoSuchAlgorithmException {
        for (User user : users) {
            if (registerDto.getUsername().equals(user.getUsername())) {
                return  new ResponseEntity<>("korisnik postoji", HttpStatus.BAD_REQUEST);
            }
            else {
                User newUser = new User(registerDto);
                users.add(newUser);
                writeToFile(users, Constants.USERS_FILE_PATH);
                return new ResponseEntity("Uspesno", HttpStatus.CREATED);
            }
        }
        return null;
    }


}
