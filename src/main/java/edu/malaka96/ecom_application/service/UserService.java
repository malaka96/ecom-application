package edu.malaka96.ecom_application.service;

import edu.malaka96.ecom_application.enums.UserRole;
import edu.malaka96.ecom_application.model.Address;
import edu.malaka96.ecom_application.model.User;
import edu.malaka96.ecom_application.model.dto.AddressDTO;
import edu.malaka96.ecom_application.model.dto.UserRequest;
import edu.malaka96.ecom_application.model.dto.UserResponse;
import edu.malaka96.ecom_application.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::mapToUserResponse)
                .toList();
    }

    public Optional<UserResponse> getUser(Long id) {
        return userRepository.findById(id)
                .map(this::mapToUserResponse);
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
                    existingUser.setRole(updatedUser.getRole());
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

    private UserResponse mapToUserResponse(User user){

        AddressDTO addressDTO = null;
        if(user.getAddress() != null){
            addressDTO = AddressDTO.builder()
                    .street(user.getAddress().getStreet())
                    .city(user.getAddress().getCity())
                    .state(user.getAddress().getState())
                    .country(user.getAddress().getCountry())
                    .zipcode(user.getAddress().getZipcode())
                    .build();
        }

        return UserResponse.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .role(user.getRole())
                .address(addressDTO)
                .build();
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
