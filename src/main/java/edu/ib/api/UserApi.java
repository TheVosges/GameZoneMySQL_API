package edu.ib.api;

import edu.ib.manager.UserManager;
import edu.ib.repo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserApi {

    private UserManager userManager;

    @Autowired
    public UserApi(UserManager userManager) {
        this.userManager = userManager;
    }

    @GetMapping("/user/all")
    public Iterable<User> getAll() {
        return userManager.findAll();
    }

    @GetMapping("/user/login")
    public Integer check_login(@RequestParam String login, String password) {
        return userManager.loginUser(login, password);
    }

    @PostMapping("/user")
    public void addUser(@RequestParam String email, String password) {
        userManager.addUser(email, password);
    }
}
