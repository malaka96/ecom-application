package edu.malaka96.ecom_application.service;

import edu.malaka96.ecom_application.enums.UserRole;
import edu.malaka96.ecom_application.model.Address;
import edu.malaka96.ecom_application.model.User;
import edu.malaka96.ecom_application.model.dto.UserRequest;
import edu.malaka96.ecom_application.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUser(Long id) {
        return userRepository.findById(id);
    }

    public void addUser(UserRequest userRequest) {
        userRepository.save(mapToUserEntity(userRequest));
    }


    public Boolean updateUser(Long id, UserRequest updatedUser) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    existingUser
                            .setFirstName(updatedUser.getFirstName());
                    existingUser.setLastName(updatedUser.getLastName());
                    existingUser.setEmail(updatedUser.getEmail());
                    existingUser.setPhone(updatedUser.getPhone());
                    existingUser.setRole(updatedUser.getUserRole());
                    if(updatedUser.getAddressDTO() != null){
                        existingUser.setAddress(Address.builder()
                                .street(updatedUser.getAddressDTO().getStreet())
                                .city(updatedUser.getAddressDTO().getCity())
                                .state(updatedUser.getAddressDTO().getState())
                                .country(updatedUser.getAddressDTO().getCountry())
                                .zipcode(updatedUser.getAddressDTO().getZipcode())
                                .build());
                    }
                    userRepository.save(existingUser);
                    return true;
                })
                .orElse(false);

    }


    private User mapToUserEntity(UserRequest userRequest){

        Address address = null;

        if (userRequest.getAddressDTO() != null) {
            address = Address.builder()
                    .state(userRequest.getAddressDTO().getState())
                    .city(userRequest.getAddressDTO().getCity())
                    .street(userRequest.getAddressDTO().getStreet())
                    .country(userRequest.getAddressDTO().getCountry())
                    .zipcode(userRequest.getAddressDTO().getZipcode())
                    .build();
        }

        return User.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .email(userRequest.getEmail())
                .phone(userRequest.getPhone())
                .role(UserRole.CUSTOMER)
                .address(address) // can be null
                .build();
    }

}
