package com.codeneeti.technexushub.repositories;

import com.codeneeti.technexushub.entities.Fresher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FresherRepository extends JpaRepository<Fresher, Long> {

    // Custom query methods
    Optional<Fresher> findByEmail(String email);  // Find fresher by email
//    Optional<Fresher> findByUsername(String username);  // Find fresher by username
//    Optional<Fresher> findByEmailAndUsername(String email, String username);  // Find fresher by email and username
    Optional<Fresher> findByPhoneNumber(String phoneNumber);  // Find fresher by phone number (optional)

    // Example: Searching fresher by name containing a keyword
    Optional<Fresher> findByFirstNameContaining(String keyword);  // Find fresher by first name containing a keyword
    Optional<Fresher> findByLastNameContaining(String keyword);  // Find fresher by last name containing a keyword

    // Add more custom methods if necessary...
}
