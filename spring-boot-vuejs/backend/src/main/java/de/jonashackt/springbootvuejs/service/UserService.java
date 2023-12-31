package de.jonashackt.springbootvuejs.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.jonashackt.springbootvuejs.constants.Constants;
import de.jonashackt.springbootvuejs.domain.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class UserService {
    public static ArrayList<User> loadUsers() throws IOException {
        ArrayList<User> users;
        BufferedReader reader = null;
        StringBuilder sb = new StringBuilder();
        try {
            reader = new BufferedReader(new FileReader(Constants.USERS_FILE_PATH));
            String line = reader.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (reader != null) reader.close();
        }
        ObjectMapper om = new ObjectMapper();
        users = om.readValue(sb.toString(), new TypeReference<ArrayList<User>>() {
        });
        return users;
    }
}
