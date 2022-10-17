package com.kodilla.ecommercee.user;

import com.kodilla.ecommercee.exceptions.NoFoundUserException;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    UserService(final UserRepository userRepository, final UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public User getUser(Long userId) throws NoFoundUserException {
        return userRepository.findById(userId).orElseThrow(NoFoundUserException::new);
    }
    public void createUser(UserDto userDto){
        User user = userMapper.mapToUser(userDto);
        userRepository.save(user);
    }
    public void toggleUser(Long userId) throws NoFoundUserException {
        if (!userRepository.existsById(userId))
            throw new NoFoundUserException();
        User toggledUser = userRepository.findById(userId).orElseThrow(NoFoundUserException::new);
        toggledUser.setBlockStatus(!toggledUser.isBlockStatus());
        userRepository.save(toggledUser);
    }
    public boolean generateToken(Long userId, String login, String password) throws NoFoundUserException {
        User user = userRepository.findById(userId).orElseThrow(NoFoundUserException::new);
        if (user.isToken())
            throw new IllegalArgumentException("------------Token exist!------------");
        if (user.getLogin().equals(login) && user.getPassword().equals(password)){
            if (!user.isToken()) {
                user.setTime(LocalTime.of(0,59,59));
                user.setToken(true);
                userRepository.save(user);
                return true;
            }
        }
        throw  new IllegalArgumentException("Wrong login or password");
    }
}
