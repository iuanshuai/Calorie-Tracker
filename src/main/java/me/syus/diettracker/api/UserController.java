package me.syus.diettracker.api;
import me.syus.diettracker.Service.UserService;
import me.syus.diettracker.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = {"/api/users", "/api/user"}) // all the requests from "/api/users & user" will be listened
@Controller
@ResponseBody // return a data format
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(getClass());


    @Autowired
    private UserService userService;

    // /api/users GET
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List getUserList() {
        logger.debug("list users");
        return userService.findAll();
    }

    // /api/users POST
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public User addUser(@RequestBody User u) {
        userService.createUser(u);
        return u;
    }


    // /api/users/5  /object/object_id
    @RequestMapping(value = "/{Id}", method = RequestMethod.GET)
    public User getUserById(@PathVariable("Id") Long Id) {
        logger.debug("find users id: " + Id);
        return userService.findById(Id);
    }

    // /api/users?username=seany
    @RequestMapping(value = "", method = RequestMethod.GET, params = "id")
    public User getUserByUsername(@RequestParam("id") Long id) {
        logger.debug("find users by id: " + id);
        return userService.findById(id);
    }

    // /api/users?firstname=seany
    @RequestMapping(value = "", method = RequestMethod.GET, params = "first_name")
    public List<User> getUserByFirstName(@RequestParam("first_name") String firstName) {
        logger.debug("find users by firstname: " + firstName);
        return userService.findByFirstName(firstName);

    }

    // /api/users?lastname=seany
    @RequestMapping(value = "", method = RequestMethod.GET, params = "last_name")
    public List<User> getUserByLastName(@RequestParam("last_name") String lastName) {
        logger.debug("find users by lastname: " + lastName);
        return userService.findByLastName(lastName);

    }

}
