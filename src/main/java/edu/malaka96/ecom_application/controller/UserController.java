package edu.malaka96.ecom_application.controller;

import edu.malaka96.ecom_application.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    private List<User> users = new ArrayList<User>();

    @GetMapping("/api/users")
    public List<User> getAll(){
        return users;
    }

}
