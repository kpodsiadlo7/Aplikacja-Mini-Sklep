package com.kodilla.ecommercee.user;

import com.kodilla.ecommercee.exceptions.NoFoundUserException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/users")
public class UserController {

    UserService userService;
    UserController(final UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/create")
    ResponseEntity<Void> createUser(@RequestBody UserDto userDto){
        userService.createUser(userDto);
        return ResponseEntity.ok().build();
    }
    @Transactional
    @PatchMapping(value = "/login/{userId}/{login}/{password}")
    ResponseEntity<Boolean> generateToken(@PathVariable Long userId, @PathVariable String login, @PathVariable String password) throws NoFoundUserException {
        return ResponseEntity.ok(userService.generateToken(userId,login,password));
    }
    @Transactional
    @PatchMapping("/block/{userId}")
    ResponseEntity<Void> toggleUserBlock(@PathVariable Long userId) throws NoFoundUserException {
        userService.toggleUser(userId);
        return ResponseEntity.ok().build();
    }
}
