package com.agents.java_book_library.domains;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Address {

    @NotBlank(message = "Street and number is mandatory")
    private String streetAndNumber;

    @NotBlank(message = "Postal code is mandatory")
    private String postalCode;

    @NotBlank(message = "State is mandatory")
    private String state;

    @NotBlank(message = "City is mandatory")
    private String city;

    @NotBlank(message = "Country is mandatory")
    private String country;
}
