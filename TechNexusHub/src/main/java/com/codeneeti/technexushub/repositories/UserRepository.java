package com.codeneeti.technexushub.repositories;

import com.codeneeti.technexushub.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,String> {
    //JPL
    //Native
    //custome method
    //implimentation class will be genrated automatically at run time

    Optional<User> findByEmail(String email);
//  UserEntity findByName(String name);
    Optional<User> findByNameContaining(String keyword);
    Optional<User> findByUserName(String keyword);
    Optional<User> findByEmailAndPassword(String email, String password);

}
