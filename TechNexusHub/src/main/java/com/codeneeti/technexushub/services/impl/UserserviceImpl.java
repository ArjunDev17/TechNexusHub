package com.codeneeti.technexushub.services.impl;

import com.codeneeti.technexushub.dtos.UserDTO;
import com.codeneeti.technexushub.entities.UserEntity;
import com.codeneeti.technexushub.exceptions.ResourceNotFoundException;
import com.codeneeti.technexushub.repositories.UserRepository;
import com.codeneeti.technexushub.services.UserSerivice;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        String userId = UUID.randomUUID().toString();
        userDTO.setUserId(userId);
        UserEntity user = dtoToEntity(userDTO);
//        userRepository.save(userDTO);
        userRepository.save(user);
        UserDTO userDTO1 = entityToDto(user);

        return userDTO1;
    }

    @Override
    public UserDTO updateUser(String userId, UserDTO userDTO) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("user not findfrom this id"));
        user.setName(userDTO.getName());
        user.setAbout(userDTO.getAbout());
        user.setGender(userDTO.getGender());
        user.setPassword(userDTO.getPassword());
        user.setImageName(userDTO.getImageName());
        UserEntity saved = userRepository.save(user);
        return entityToDto(saved);
    }

    @Override
    public void delteUser(String userId) {
        userRepository.findById(userId).orElseThrow(() -> new RuntimeException("user not found"));
    }

    @Override
    public List<UserDTO> getAllUser() {
        List<UserEntity> userList = userRepository.findAll();
        List<UserDTO> userDTOList = userList.stream().map(userEntity -> entityToDto(userEntity)).collect(Collectors.toList());
        return userDTOList;
    }

    @Override
    public UserDTO getUserById(String userId) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user not found"));
        return entityToDto(userEntity);
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        UserEntity userEntity = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("user not found from this email ID"));
        return entityToDto(userEntity);
    }

    @Override
    public List<UserDTO> searchUser(String keyword) {
        Optional<UserEntity> byNameContaining = userRepository.findByNameContaining(keyword);
        List<UserDTO> userDTOList = byNameContaining.stream().map(userEntity -> entityToDto(userEntity)).collect(Collectors.toList());

        return userDTOList;
    }

    private UserEntity dtoToEntity(UserDTO userDTO) {
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
        return modelMapper.map(userDTO, UserEntity.class);
    }

    private UserDTO entityToDto(UserEntity user) {
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
