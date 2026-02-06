package edu.malaka96.ecom_application.controller;

import edu.malaka96.ecom_application.model.User;
import edu.malaka96.ecom_application.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/api/users")
    public List<User> getAll(){
        return userService.getAllUsers();
    }

    @GetMapping("/api/user/{id}")
    public User getUser(@PathVariable String id){
        return userService.getUser(id);
    }

    @PostMapping("/api/adduser")
    public String addUser(@RequestBody User user){
        userService.addUser(user);
        return "User added successfully";
    }

}
