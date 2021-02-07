package com.sunnah.BookStore.business.domain.Dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;

@Data
@NoArgsConstructor
public class UserDto {

    private String firstName;
    private String lastName;
    private String street;
    private int homeNumber;
    private int zip;
    @Email
    private String contactEmail;
    private String state;
}
