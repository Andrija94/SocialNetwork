package de.jonashackt.springbootvuejs.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.jonashackt.springbootvuejs.constants.Constants;
import de.jonashackt.springbootvuejs.domain.Post;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class PostService {

    public static ArrayList<Post> loadPosts() throws IOException {
        ArrayList<Post> posts;
        BufferedReader reader = null;
        StringBuilder sb = new StringBuilder();
        try {
            reader = new BufferedReader(new FileReader(Constants.POSTS_FILE_PATH));
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
        posts = om.readValue(sb.toString(), new TypeReference<ArrayList<Post>>() {
        });
        return posts;
    }
 }
