package de.jonashackt.springbootvuejs.repositorium;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import de.jonashackt.springbootvuejs.domain.Post;
import de.jonashackt.springbootvuejs.domain.User;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Repo {
    public static ArrayList<User> users;
    public static ArrayList<Post> posts;

    public static void writeToFile(ArrayList list, String filePath) throws JsonProcessingException, FileNotFoundException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Object x : list) {
            if (list.indexOf(x) == list.toArray().length - 1) {
                sb.append(ow.writeValueAsString(x));
                break;
            }
            sb.append(ow.writeValueAsString(x));
            sb.append(",");
        }
        sb.append("]");
        PrintWriter writer = new PrintWriter(filePath);
        writer.print(sb);
        writer.close();
    }

}

