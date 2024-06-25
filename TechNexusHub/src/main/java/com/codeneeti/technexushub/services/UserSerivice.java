package com.codeneeti.technexushub.services;

import com.codeneeti.technexushub.dtos.PageableResponse;
import com.codeneeti.technexushub.dtos.UserDTO;

import java.util.List;

public interface UserSerivice {

    UserDTO createUser(UserDTO userDTO);

    UserDTO updateUser(String userId, UserDTO userDTO);

    void delteUser(String userId);

    PageableResponse<UserDTO> getAllUser(int pageNumber, int pageSize , String sortBy, String sortDir);

    UserDTO getUserById(String userId);

    UserDTO getUserByEmail(String email);

    List<UserDTO> searchUser(String keyword);

}

