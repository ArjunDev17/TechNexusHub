package com.codeneeti.technexushub.repositories;

import com.codeneeti.technexushub.entities.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserProfileRepository extends JpaRepository<UserProfile, String> {
    // Custom query methods

    Optional<UserProfile> findByEmail(String email);

    List<UserProfile> findByFirstNameContainingOrLastNameContaining(String firstNameKeyword, String lastNameKeyword);

//    Optional<UserProfile> findByEmailAndPhoneNumber(String email, String phone);
    @Query("SELECT u FROM UserProfile u WHERE u.email = :email AND u.phone = :phone")
    Optional<UserProfile> findByEmailAndPhoneNumber(@Param("email") String email, @Param("phone") String phone);

}
