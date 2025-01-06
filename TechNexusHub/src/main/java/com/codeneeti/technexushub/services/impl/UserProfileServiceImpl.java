package com.codeneeti.technexushub.services.impl;

import com.codeneeti.technexushub.dtos.UserAddressDto;
import com.codeneeti.technexushub.dtos.UserProfileDto;
import com.codeneeti.technexushub.entities.UserAddress;
import com.codeneeti.technexushub.entities.UserProfile;
import com.codeneeti.technexushub.repositories.UserProfileRepository;
import com.codeneeti.technexushub.services.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    private UserProfileRepository userProfileRepository;


    @Transactional
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
        userProfile.setDob(userProfileDto.getDob());
        userProfile.setGender(userProfileDto.getGender());
        userProfile.setCollegeName(userProfileDto.getCollegeName());
        userProfile.setCourse(userProfileDto.getCourse());
        userProfile.setYearOfStudy(userProfileDto.getYearOfStudy());
        userProfile.setCgpa(userProfileDto.getCgpa());
        userProfile.setInterest(userProfileDto.getInterest());
        userProfile.setSkill(userProfileDto.getSkill());
        userProfile.setLinkdn(userProfileDto.getLinkdn());
        userProfile.setGithub(userProfileDto.getGithub());

        // Save and return the profile
        UserProfile savedProfile = userProfileRepository.save(userProfile);
        System.out.println("Saved Profile: " + savedProfile.getId());  // Debugging log to confirm ID

        return entityToDto(savedProfile);
    }


    // Helper method to convert String to Date
    private java.util.Date parseDate(String dateStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.parse(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Helper method to convert entity to DTO
    private UserProfileDto entityToDto(UserProfile userProfile) {
        UserProfileDto dto = new UserProfileDto();
        dto.setFirstName(userProfile.getFirstName());
        dto.setLastName(userProfile.getLastName());
        dto.setEmail(userProfile.getEmail());
        dto.setPhone(userProfile.getPhone());

        // Map the address from entity to DTO
        UserAddressDto addressDto = new UserAddressDto();
        addressDto.setStreet(userProfile.getAddress().getStreet());
        addressDto.setCity(userProfile.getAddress().getCity());
        addressDto.setState(userProfile.getAddress().getState());
        addressDto.setZipCode(userProfile.getAddress().getZipCode());
        dto.setAddress(addressDto);

        dto.setProfilePicture(userProfile.getProfilePicture());
        dto.setDob(userProfile.getDob());
        dto.setGender(userProfile.getGender());
        dto.setCollegeName(userProfile.getCollegeName());
        dto.setCourse(userProfile.getCourse());
        dto.setYearOfStudy(String.valueOf(userProfile.getYearOfStudy())); // Convert Integer to String
        dto.setCgpa(userProfile.getCgpa());
        dto.setInterest(userProfile.getInterest());
        dto.setSkill(userProfile.getSkill());
        dto.setLinkdn(userProfile.getLinkdn());
        dto.setGithub(userProfile.getGithub());
        return dto;
    }

    // Helper method to format Date to String
    private String formatDate(java.util.Date date) {
        if (date == null) return null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
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

//    private UserProfileDto entityToDto(UserProfile userProfile) {
//        UserProfileDto dto = new UserProfileDto();
//        dto.setFirstName(userProfile.getFirstName());
//        dto.setLastName(userProfile.getLastName());
//        dto.setEmail(userProfile.getEmail());
//        dto.setPhone(userProfile.getPhone());
//
//        UserAddressDto addressDto = new UserAddressDto();
//        addressDto.setStreet(userProfile.getAddress().getStreet());
//        addressDto.setCity(userProfile.getAddress().getCity());
//        addressDto.setState(userProfile.getAddress().getState());
//        addressDto.setZipCode(userProfile.getAddress().getZipCode());
//
//        dto.setAddress(addressDto);
//        dto.setProfilePicture(userProfile.getProfilePicture());
//
//        return dto;
//    }

}
