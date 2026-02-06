package edu.malaka96.ecom_application.controller;

import edu.malaka96.ecom_application.model.User;
import edu.malaka96.ecom_application.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/api/adduser")
    public String addUser(@RequestBody User user){
        userService.addUser(user);
        return "User added successfully";
    }

}
