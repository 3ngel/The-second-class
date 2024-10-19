package ru.panfilova.Project8.service;
import ru.panfilova.Project8.dto.UserDto;
import ru.panfilova.Project8.entity.User;

import java.util.List;
public interface UserService {

    void saveUser(UserDto userDto);

    User findUserByEmail(String email);

    List<UserDto> findAllUsers();
}