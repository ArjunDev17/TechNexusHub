package com.codeneeti.technexushub.controllers;

import com.codeneeti.technexushub.dtos.ApiResponse;
import com.codeneeti.technexushub.dtos.UserDTO;
import com.codeneeti.technexushub.services.UserSerivice;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserSerivice userSerivice;

    @PostMapping("/create")
    public ResponseEntity<UserDTO> createUser(@Valid  @RequestBody UserDTO userDTO) {
        UserDTO userDto1 = userSerivice.createUser(userDTO);
        return new ResponseEntity<>(userDto1, HttpStatus.CREATED);
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<UserDTO> updateUser(
            @PathVariable("userId") String userId,
            @Valid @RequestBody UserDTO userDTO
    ) {
        UserDTO updateUser = userSerivice.updateUser(userId, userDTO);
        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") String userId) {
        userSerivice.delteUser(userId);
        ApiResponse message=ApiResponse.builder()
                .message("data deleted")
                .success(true)
                .status(HttpStatus.OK)
                .build();
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/allUsers")
    public ResponseEntity<List<UserDTO>>getAllUser(){
//        List<UserDTO> allUser = userSerivice.getAllUser();//
        return new ResponseEntity<>(userSerivice.getAllUser(),HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO>getUserById(@PathVariable("userId")String userId ){
        return new ResponseEntity<>(userSerivice.getUserById(userId),HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserDTO>getUserByEmail(@PathVariable("email")String email ){
        return new ResponseEntity<>(userSerivice.getUserByEmail(email),HttpStatus.OK);
    }

    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<UserDTO>>getUserBySearch(@PathVariable("keyword")String keyword ){
        return new ResponseEntity<>(userSerivice.searchUser(keyword),HttpStatus.OK);
    }
}
