package com.codeneeti.technexushub.services.impl;

import com.codeneeti.technexushub.dtos.PageableResponse;
import com.codeneeti.technexushub.dtos.UserDTO;
import com.codeneeti.technexushub.entities.User;
import com.codeneeti.technexushub.exceptions.ResourceNotFoundException;
import com.codeneeti.technexushub.helper.Helper;
import com.codeneeti.technexushub.repositories.UserRepository;
import com.codeneeti.technexushub.services.UserSerivice;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserserviceImpl implements UserSerivice {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Value("${user.profile.image.path}")
    private String imagePath;
    Logger logger= LoggerFactory.getLogger(UserserviceImpl.class);

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        String userId = UUID.randomUUID().toString();
        userDTO.setUserId(userId);
        User user = dtoToEntity(userDTO);
//        userRepository.save(userDTO);
        userRepository.save(user);
        UserDTO userDTO1 = entityToDto(user);

        return userDTO1;
    }

    @Override
    public UserDTO updateUser(String userId, UserDTO userDTO) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("user not findfrom this id"));
        user.setName(userDTO.getName());
        user.setAbout(userDTO.getAbout());
        user.setGender(userDTO.getGender());
        user.setPassword(userDTO.getPassword());
        user.setImageName(userDTO.getImageName());
        User saved = userRepository.save(user);
        return entityToDto(saved);
    }

    @Override
    public void delteUser(String userId) {
        User userEntity = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("user not found"));

        //delete imagre file
        String fullPath=imagePath+userEntity.getImageName();
        try{
            Path path= Paths.get(fullPath);
            Files.delete(path);
        }catch (NoSuchFileException exception){
logger.info("Image folder not found");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public PageableResponse<UserDTO> getAllUser(int pageNumber, int pageSize, String sortBy, String sortDir) {


//        Pageable pageable= (Pageable) PageRequest.of(pageNumber,pageSize);
//        Pageable pageable=PageRequest.of(pageNumber,pageSize);
//        Sort sort = Sort.by(sortBy);
        Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort); //pageNumber-1
//        List<UserEntity> userList = userRepository.findAll();
        Page<User> pages = userRepository.findAll(pageable);
        PageableResponse<UserDTO> response = Helper.getPageableResponse(pages, UserDTO.class);

        return response;
    }

    @Override
    public UserDTO getUserById(String userId) {
        User userEntity = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user not found"));
        return entityToDto(userEntity);
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        User userEntity = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("user not found from this email ID"));
        return entityToDto(userEntity);
    }

    @Override
    public List<UserDTO> searchUser(String keyword) {
        Optional<User> byNameContaining = userRepository.findByNameContaining(keyword);
        List<UserDTO> userDTOList = byNameContaining.stream().map(userEntity -> entityToDto(userEntity)).collect(Collectors.toList());

        return userDTOList;
    }

    private User dtoToEntity(UserDTO userDTO) {
//        UserEntity userEntity = UserEntity.builder()
//                .userId(userDTO.getUserId())
//                .name(userDTO.getName())
//                .email(userDTO.getEmail())
//                .password(userDTO.getPassword())
//                .about(userDTO.getAbout())
//                .gender(userDTO.getGender())
//                .imageName(userDTO.getImageName())
//                .build();
//        return userEntity;
        return modelMapper.map(userDTO, User.class);
    }

    private UserDTO entityToDto(User user) {
//        UserDTO userDTO = UserDTO.builder()
//                .userId(user.getUserId())
//                .name(user.getName())
//                .password(user.getPassword())
//                .email(user.getEmail())
//                .gender(user.getGender())
//                .about(user.getAbout())
//                .imageName(user.getImageName())
//                .build();
//        return userDTO;

//        commented code is the user defined
        return modelMapper.map(user, UserDTO.class);
    }


}
