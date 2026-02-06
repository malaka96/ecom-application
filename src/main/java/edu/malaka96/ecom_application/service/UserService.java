package edu.malaka96.ecom_application.service;

import edu.malaka96.ecom_application.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private List<User> usersList = new ArrayList<User>();
    private int addCount = 0;

    public List<User> getAllUsers(){
        return usersList;
    }

    public void addUser(User user){
        user.setId(String.valueOf(addCount));
        usersList.add(user);
        addCount++;
    }

}
