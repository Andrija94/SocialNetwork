package de.jonashackt.springbootvuejs.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import de.jonashackt.springbootvuejs.constants.Constants;
import de.jonashackt.springbootvuejs.domain.Post;
import de.jonashackt.springbootvuejs.domain.User;
import de.jonashackt.springbootvuejs.dto.CreatePostDto;
import de.jonashackt.springbootvuejs.dto.PostDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

import static de.jonashackt.springbootvuejs.repositorium.Repo.*;

@RestController
@RequestMapping("/api/post")
public class PostController {

    @GetMapping("/get-all-posts")
    public ResponseEntity getAllPosts() {
        ArrayList<PostDto> postList = new ArrayList<>();
        for (Post post : posts) {
            PostDto postDto = new PostDto(post);
            postList.add(postDto);
        }
        return new ResponseEntity<>(postList, HttpStatus.OK);
    }

    @GetMapping("/get-user-posts/{username}")
    public ResponseEntity getCurrentUserPosts(@PathVariable String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                ArrayList<PostDto> postList = new ArrayList<>();
                for (Post post : posts) {
                    if (post.getUserID().equals(username)) {
                        PostDto postDto = new PostDto(post);
                        postList.add(postDto);
                    }
                }
                return new ResponseEntity<>(postList, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("User does not exist!", HttpStatus.NOT_FOUND);
    }

//    @PostMapping("/create-post")
//    public ResponseEntity createPost(@RequestBody CreatePostDto dto) throws JsonProcessingException, FileNotFoundException {
//        MultipartFile file = dto.getPicture();
//        String uniqueID = UUID.randomUUID().toString();
//        try {
//            // Get the file and save it somewhere
//            byte[] bytes = file.getBytes();
//
//            // Convert byte array back to BufferedImage
//            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
//            BufferedImage bufferedImage = ImageIO.read(byteArrayInputStream);
//            File outputFile = new File("C:\\Users\\Stoja\\Desktop\\spring-boot-vuejs\\backend\\src\\main\\db\\images\\" + file.getOriginalFilename());
//            ImageIO.write(bufferedImage, "jpg", outputFile);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Post post = new Post(dto.getUsername(), uniqueID, file.getOriginalFilename(), dto.getText());
//        posts.add(post);
//        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
//        StringBuilder sb = new StringBuilder();
//        sb.append("[");
//        for (Post x : posts) {
//            if (posts.indexOf(x) == posts.toArray().length - 1) {
//                sb.append(ow.writeValueAsString(x));
//                break;
//            }
//            sb.append(ow.writeValueAsString(x));
//            sb.append(",");
//        }
//        sb.append("]");
//        PrintWriter writer = new PrintWriter(Constants.POSTS_FILE_PATH);
//        writer.print(sb);
//        writer.close();
//
//        return new ResponseEntity<>("Uspesno", HttpStatus.CREATED);
//    }


    @PostMapping("/create-post")
    public ResponseEntity handleFileUpload(@RequestParam(value = "file", required = false) MultipartFile file,
                                   @RequestParam("username") String username,
                                   @RequestParam(value = "text", required = false) String text) throws FileNotFoundException, JsonProcessingException {

        String uniqueID = UUID.randomUUID().toString();
        if (file == null && text.equals("")) {
            return new ResponseEntity("Enter post text", HttpStatus.BAD_REQUEST);
        }

        try {
            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();

            // Convert byte array back to BufferedImage
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
            BufferedImage bufferedImage = ImageIO.read(byteArrayInputStream);
            File outputFile = new File("C:\\Users\\Stoja\\Desktop\\spring-boot-vuejs\\backend\\src\\main\\db\\images\\" + file.getOriginalFilename());
            ImageIO.write(bufferedImage, "jpg", outputFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
        Post post = new Post(username, uniqueID, file.getOriginalFilename(), text);
        posts.add(post);
        writeToFile(posts, Constants.POSTS_FILE_PATH);

        return new ResponseEntity<>("Uspesno", HttpStatus.CREATED);
    }



}

