package com.codeneeti.technexushub.services.impl;

import com.codeneeti.technexushub.dtos.UserAddressDto;
import com.codeneeti.technexushub.dtos.UserProfileDto;
import com.codeneeti.technexushub.entities.UserAddress;
import com.codeneeti.technexushub.entities.UserProfile;
import com.codeneeti.technexushub.repositories.UserProfileRepository;
import com.codeneeti.technexushub.services.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Override
    public UserProfileDto createUserProfile(UserProfileDto userProfileDto) {
        UserProfile userProfile = new UserProfile();
        userProfile.setFirstName(userProfileDto.getFirstName());
        userProfile.setLastName(userProfileDto.getLastName());
        userProfile.setEmail(userProfileDto.getEmail());
        userProfile.setPhone(userProfileDto.getPhone());

        // Set address
        UserAddress address = new UserAddress();
        address.setStreet(userProfileDto.getAddress().getStreet());
        address.setCity(userProfileDto.getAddress().getCity());
        address.setState(userProfileDto.getAddress().getState());
        address.setZipCode(userProfileDto.getAddress().getZipCode());

        userProfile.setAddress(address);
        userProfile.setProfilePicture(userProfileDto.getProfilePicture());

        UserProfile savedProfile = userProfileRepository.save(userProfile);
        return entityToDto(savedProfile);
    }

    @Override
    public UserProfileDto updateUserProfile(Long profileId, UserProfileDto userProfileDto) {
        UserProfile userProfile = userProfileRepository.findById(String.valueOf(profileId))
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        userProfile.setFirstName(userProfileDto.getFirstName());
        userProfile.setLastName(userProfileDto.getLastName());
        userProfile.setEmail(userProfileDto.getEmail());
        userProfile.setPhone(userProfileDto.getPhone());

        // Update address
        UserAddress address = userProfile.getAddress();
        address.setStreet(userProfileDto.getAddress().getStreet());
        address.setCity(userProfileDto.getAddress().getCity());
        address.setState(userProfileDto.getAddress().getState());
        address.setZipCode(userProfileDto.getAddress().getZipCode());

        userProfile.setProfilePicture(userProfileDto.getProfilePicture());

        UserProfile updatedProfile = userProfileRepository.save(userProfile);
        return entityToDto(updatedProfile);
    }

    @Override
    public UserProfileDto getUserProfileById(Long profileId) {
        UserProfile userProfile = userProfileRepository.findById(String.valueOf(profileId))
                .orElseThrow(() -> new RuntimeException("Profile not found"));
        return entityToDto(userProfile);
    }

    private UserProfileDto entityToDto(UserProfile userProfile) {
        UserProfileDto dto = new UserProfileDto();
        dto.setFirstName(userProfile.getFirstName());
        dto.setLastName(userProfile.getLastName());
        dto.setEmail(userProfile.getEmail());
        dto.setPhone(userProfile.getPhone());

        UserAddressDto addressDto = new UserAddressDto();
        addressDto.setStreet(userProfile.getAddress().getStreet());
        addressDto.setCity(userProfile.getAddress().getCity());
        addressDto.setState(userProfile.getAddress().getState());
        addressDto.setZipCode(userProfile.getAddress().getZipCode());

        dto.setAddress(addressDto);
        dto.setProfilePicture(userProfile.getProfilePicture());

        return dto;
    }
}
