package com.codeneeti.technexushub.repositories;

import com.codeneeti.technexushub.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,String> {
    //JPL
    //Native
    //custome method
    //implimentation class will be genrated automatically at run time

    Optional<UserEntity> findByEmail(String email);
//  UserEntity findByName(String name);
    Optional<UserEntity> findByNameContaining(String keyword);
    Optional<UserEntity> findByEmailAndPassword(String email,String password);

}
