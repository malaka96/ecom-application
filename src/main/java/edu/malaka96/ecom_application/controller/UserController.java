package edu.malaka96.ecom_application.controller;

import edu.malaka96.ecom_application.model.User;
import edu.malaka96.ecom_application.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/api/users")
    public ResponseEntity<List<User>> getAll(){
        //return ResponseEntity.ok(userService.getAllUsers());
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.I_AM_A_TEAPOT);
    }

    @GetMapping("/api/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id){
        return userService.getUser(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/api/adduser")
    public String addUser(@RequestBody User user){
        userService.addUser(user);
        return "User added successfully";
    }

    @PutMapping("/api/updateuser")
    public ResponseEntity<?> updateUser(@RequestBody User user){
        boolean updated = userService.updateUser(user);
        if (updated)
            return ResponseEntity.ok("User updated successfully" );
        else
            return ResponseEntity.notFound().build();
    }

}
