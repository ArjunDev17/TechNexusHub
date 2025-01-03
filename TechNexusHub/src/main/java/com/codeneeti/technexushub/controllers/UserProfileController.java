package com.codeneeti.technexushub.controllers;

import com.codeneeti.technexushub.dtos.UserProfileDto;
import com.codeneeti.technexushub.services.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user-profiles")
@CrossOrigin(origins = "*")
public class UserProfileController {

    @Autowired
    private UserProfileService userProfileService;

    @PostMapping("/create")
    public ResponseEntity<UserProfileDto> createUserProfile(@RequestBody UserProfileDto userProfileDto) {
        UserProfileDto createdProfile = userProfileService.createUserProfile(userProfileDto);
        return new ResponseEntity<>(createdProfile, HttpStatus.CREATED);
    }

    @PutMapping("/update/{profileId}")
    public ResponseEntity<UserProfileDto> updateUserProfile(@PathVariable Long profileId, @RequestBody UserProfileDto userProfileDto) {
        UserProfileDto updatedProfile = userProfileService.updateUserProfile(profileId, userProfileDto);
        return new ResponseEntity<>(updatedProfile, HttpStatus.OK);
    }

    @GetMapping("/{profileId}")
    public ResponseEntity<UserProfileDto> getUserProfile(@PathVariable Long profileId) {
        UserProfileDto userProfile = userProfileService.getUserProfileById(profileId);
        return new ResponseEntity<>(userProfile, HttpStatus.OK);
    }
}
