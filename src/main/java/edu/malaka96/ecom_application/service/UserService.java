package edu.malaka96.ecom_application.service;

import edu.malaka96.ecom_application.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private List<User> usersList = new ArrayList<User>();
    private int addCount = 0;

    public List<User> getAllUsers() {
        return usersList;
    }

    public Optional<User> getUser(String id) {
        return usersList.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }

    public void addUser(User user) {
        user.setId(String.valueOf(addCount));
        usersList.add(user);
        addCount++;
    }


    public Boolean updateUser(User updatedUser) {
        return usersList.stream()
                .filter(user -> user.getId().equals(updatedUser.getId()))
                .findFirst()
                .map(existingUser -> {
                    existingUser
                            .setEmail(updatedUser.getEmail());
                    existingUser.setPassword(updatedUser.getPassword());
                    return true;
                })
                .orElse(false);

    }
}
