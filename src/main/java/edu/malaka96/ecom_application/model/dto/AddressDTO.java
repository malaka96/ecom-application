package edu.malaka96.ecom_application.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class AddressDTO {

    private String street;
    private String city;
    private String state;
    private String country;
    private String zipcode;

}
