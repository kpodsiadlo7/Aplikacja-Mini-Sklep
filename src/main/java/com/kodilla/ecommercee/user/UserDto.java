package com.kodilla.ecommercee.user;

import lombok.*;

@Getter
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String login;
    private String password;
    private String name;
    private String surname;
    private String address;
    private String city;
    private String phoneNumber;
    private String email;
    private boolean blockStatus;
}