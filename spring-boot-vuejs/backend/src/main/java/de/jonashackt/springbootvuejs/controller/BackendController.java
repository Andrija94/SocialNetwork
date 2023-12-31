package de.jonashackt.springbootvuejs.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api")
public class BackendController {

    private static final Logger LOG = LoggerFactory.getLogger(BackendController.class);

    public static final String HELLO_TEXT = "Get all users methods!";
    public static final String SECURED_TEXT = "Hello from the secured resource!";

//    @Autowired
//    private UserRepository userRepository;

    @ResponseBody
    @RequestMapping(path = "/hello")
    public String sayHello() {
        LOG.info("GET called on /hello resource");
        return HELLO_TEXT;
    }
//
//    @ResponseBody
//    @RequestMapping(path = "/user/{lastName}/{firstName}", method = RequestMethod.POST)
//    @ResponseStatus(HttpStatus.CREATED)
//    public long addNewUser (@PathVariable("lastName") String lastName, @PathVariable("firstName") String firstName) {
//        User savedUser = userRepository.save(new User(firstName, lastName));
//
//        LOG.info(savedUser.toString() + " successfully saved into DB");
//
//        return savedUser.getId();
//    }
//
//    @ResponseBody
//    @GetMapping(path = "/user/{id}")
//    public User getUserById(@PathVariable("id") long id) {
//
//        return userRepository.findById(id).map(user -> {
//            LOG.info("Reading user with id " + id + " from database.");
//            return user;
//        }).orElseThrow(() -> new UserNotFoundException("The user with the id " + id + " couldn't be found in the database."));
//    }
//
//    @ResponseBody
//    @RequestMapping(path="/secured", method = RequestMethod.GET)
//    public String getSecured() {
//        LOG.info("GET successfully called on /secured resource");
//        return SECURED_TEXT;
//    }
}
