package com.codeneeti.technexushub.services;

import com.codeneeti.technexushub.dtos.UserProfileDto;

public interface UserProfileService {

    UserProfileDto createUserProfile(UserProfileDto userProfileDto);

    UserProfileDto updateUserProfile(Long profileId, UserProfileDto userProfileDto);

    UserProfileDto getUserProfileById(Long profileId);
}
