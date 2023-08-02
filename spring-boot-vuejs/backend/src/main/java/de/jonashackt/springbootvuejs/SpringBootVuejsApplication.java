package de.jonashackt.springbootvuejs;

import de.jonashackt.springbootvuejs.repositorium.Repo;

import de.jonashackt.springbootvuejs.service.PostService;
import de.jonashackt.springbootvuejs.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class SpringBootVuejsApplication {

	public static void main(String[] args) throws IOException {
		Repo.users = UserService.loadUsers();
		Repo.posts = PostService.loadPosts();
		SpringApplication.run(SpringBootVuejsApplication.class, args);
	}
}
