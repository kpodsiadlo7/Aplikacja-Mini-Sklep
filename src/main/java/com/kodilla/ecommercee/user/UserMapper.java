package com.kodilla.ecommercee.user;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserMapper {

    public User mapToUser(UserDto userDto){
        return new User(
                userDto.getLogin(),
                userDto.getPassword(),
                userDto.getName(),
                userDto.getSurname(),
                userDto.getAddress(),
                userDto.getCity(),
                userDto.getPhoneNumber(),
                userDto.getEmail()
        );
    }

    public UserDto mapToUserDto(User user){
        return new UserDto(
                user.getId(),
                user.getLogin(),
                user.getPassword(),
                user.getName(),
                user.getSurname(),
                user.getAddress(),
                user.getCity(),
                user.getPhoneNumber(),
                user.getEmail(),
                user.isBlockStatus()
        );
    }

    public List<UserDto> mapToUserDtoList(final List<User> users){
        return users.stream()
                .map(this::mapToUserDto)
                .collect(Collectors.toList());
    }
}