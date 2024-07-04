package com.codeneeti.technexushub.services;

import com.codeneeti.technexushub.dtos.PageableResponse;
import com.codeneeti.technexushub.dtos.UserDto;

import java.util.List;

public interface UserSerivice {

    UserDto createUser(UserDto userDTO);

    UserDto updateUser(String userId, UserDto userDTO);

    void delteUser(String userId);

    PageableResponse<UserDto> getAllUser(int pageNumber, int pageSize , String sortBy, String sortDir);

    UserDto getUserById(String userId);

    UserDto getUserByEmail(String email);

    List<UserDto> searchUser(String keyword);

}

