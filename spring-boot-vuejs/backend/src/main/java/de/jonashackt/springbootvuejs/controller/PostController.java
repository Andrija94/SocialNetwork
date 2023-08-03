package de.jonashackt.springbootvuejs.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import de.jonashackt.springbootvuejs.constants.Constants;
import de.jonashackt.springbootvuejs.domain.Post;
import de.jonashackt.springbootvuejs.domain.User;
import de.jonashackt.springbootvuejs.dto.PostDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.UUID;

import static de.jonashackt.springbootvuejs.repositorium.Repo.*;

@RestController
@RequestMapping("/api/post")
public class PostController {

    @GetMapping("/get-all-posts")
    public ResponseEntity getAllPosts() throws IOException {
        ArrayList<PostDto> postList = new ArrayList<>();


        for (Post post : posts) {
            String pic = "";
            if (!post.getPicture().equals("")) {
                BufferedImage img = ImageIO.read(new File("C:\\Users\\Stoja\\Desktop\\project\\spring-boot-vuejs\\backend\\src\\main\\db\\images\\" + post.getPicture()));
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ImageIO.write(img, "jpg", bos);
                byte[] bytes = bos.toByteArray();
                pic = Base64.getEncoder().encodeToString(bytes);
            }
            PostDto postDto = new PostDto(post.getUserID(), pic, post.getText());
            postList.add(postDto);
        }
        return new ResponseEntity<>(postList, HttpStatus.OK);
    }

    @GetMapping("/get-user-posts/{username}")
    public ResponseEntity getCurrentUserPosts(@PathVariable String username) throws IOException {
        ArrayList<PostDto> postList = new ArrayList<>();
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                for (Post post : posts) {
                    String pic = "";
                    if (post.getUserID().equals(username)) {
                        if (!post.getPicture().equals("")) {
                            BufferedImage img = ImageIO.read(new File("C:\\Users\\Stoja\\Desktop\\project\\spring-boot-vuejs\\backend\\src\\main\\db\\images\\" + post.getPicture()));
                            ByteArrayOutputStream bos = new ByteArrayOutputStream();
                            ImageIO.write(img, "jpg", bos);
                            byte[] bytes = bos.toByteArray();
                            pic = Base64.getEncoder().encodeToString(bytes);
                        }
                        PostDto postDto = new PostDto(post.getUserID(), pic, post.getText());
                        postList.add(postDto);
                    }
                }
                return new ResponseEntity<>(postList, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("User does not exist!", HttpStatus.NOT_FOUND);
    }


    @PostMapping("/create-post")
    public ResponseEntity createPost(@RequestParam(value = "file", required = false) MultipartFile file,
                                           @RequestParam("username") String username,
                                           @RequestParam(value = "text", required = false) String text) throws FileNotFoundException, JsonProcessingException {

        String uniqueID = UUID.randomUUID().toString();
        String fileName = "";
        if (file == null && text.equals("")) {
            return new ResponseEntity("Enter post text", HttpStatus.BAD_REQUEST);
        }
        if (file != null) {
            try {
                // Get the file and save it somewhere
                byte[] bytes = file.getBytes();

                // Convert byte array back to BufferedImage
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
                BufferedImage bufferedImage = ImageIO.read(byteArrayInputStream);
                File outputFile = new File("C:\\Users\\Stoja\\Desktop\\project\\spring-boot-vuejs\\backend\\src\\main\\db\\images\\" + file.getOriginalFilename());
                ImageIO.write(bufferedImage, "jpg", outputFile);
                fileName = file.getOriginalFilename();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Post post = new Post(username, uniqueID, fileName, text);
        posts.add(post);
        writeToFile(posts, Constants.POSTS_FILE_PATH);

        return new ResponseEntity<>("Uspesno", HttpStatus.CREATED);
    }


}

