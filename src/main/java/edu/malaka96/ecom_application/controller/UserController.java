package edu.malaka96.ecom_application.controller;

import edu.malaka96.ecom_application.model.dto.UserRequest;
import edu.malaka96.ecom_application.model.dto.UserResponse;
import edu.malaka96.ecom_application.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping("/api/users")
    public ResponseEntity<List<UserResponse>> getAll(){
        //return ResponseEntity.ok(userService.getAllUsers());
        log.debug("Debug message");
        log.info("Info message");
        log.error("Error message");
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.I_AM_A_TEAPOT);
    }

    @GetMapping("/api/user/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id){
        return userService.getUser(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/api/adduser")
    public String addUser(@RequestBody UserRequest user){
        userService.addUser(user);

        return "User added successfully";
    }

    @PutMapping("/api/updateuser/{id}")
    public ResponseEntity<?> updateUser(@RequestBody UserRequest userRequest, @PathVariable Long id){
        boolean updated = userService.updateUser(id,userRequest);
        if (updated)
            return ResponseEntity.ok("User updated successfully" );
        else
            return ResponseEntity.notFound().build();
    }

}
