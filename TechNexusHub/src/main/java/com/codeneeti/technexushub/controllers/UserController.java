package com.codeneeti.technexushub.controllers;

import com.codeneeti.technexushub.dtos.ApiResponse;
import com.codeneeti.technexushub.dtos.ImageResponse;
import com.codeneeti.technexushub.dtos.PageableResponse;
import com.codeneeti.technexushub.dtos.UserDTO;
import com.codeneeti.technexushub.services.FileService;
import com.codeneeti.technexushub.services.UserSerivice;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserSerivice userSerivice;
    @Autowired
    private FileService fileService;
    @Value("${user.profile.image.path}")
    private String imageUploadPath;
    private Logger logger= LoggerFactory.getLogger(UserController.class);

    @PostMapping("/create")
    public ResponseEntity<UserDTO> createUser(
            @Valid  @RequestBody UserDTO userDTO) {
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
    public ResponseEntity<PageableResponse<UserDTO>>getAllUser(
            @RequestParam(value = "pageNumber",defaultValue ="0",required = false )int pageNumber,
            @RequestParam(value = "pageSize",defaultValue ="10",required = false )int pageSize,
            @RequestParam(value = "sortBy",defaultValue = "name",required = false)String sortBy,
            @RequestParam(value = "sortDir",defaultValue = "asc",required = false)String sortDir
    ){
//        List<UserDTO> allUser = userSerivice.getAllUser();//
        return new ResponseEntity<>(userSerivice.getAllUser(pageNumber,pageSize,sortBy,sortDir),HttpStatus.OK);
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

    //upload user image
    @PostMapping("/image/{userId}")
    public ResponseEntity<ImageResponse> uploadUserImage(
            @RequestParam("userImage")MultipartFile image,
            @PathVariable String userId
            ) throws IOException {
        String uploadedFile = fileService.uploadFile(image, imageUploadPath);
        UserDTO userById = userSerivice.getUserById(userId);
        userById.setImageName(uploadedFile);
        UserDTO updateUser = userSerivice.updateUser(userId,userById);

        ImageResponse imageResponse=ImageResponse.builder()
                .imageName(uploadedFile)
                .success(true)
                .status(HttpStatus.OK)
                .build();
        return new ResponseEntity<>(imageResponse,HttpStatus.CREATED);
    }

    //save user image
    @GetMapping("/image/{userId}")
    public void serveUserImage(@PathVariable String userId,
                               HttpServletResponse response
                               ) throws IOException {
        UserDTO userById = userSerivice.getUserById(userId);
        logger.info("user Image name is :{}",userById.getImageName());
        InputStream resource = fileService.getResource(imageUploadPath, userById.getImageName());
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource, response.getOutputStream());
    }


}
