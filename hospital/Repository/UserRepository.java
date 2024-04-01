package com.example.hospital.Repository;

import com.example.hospital.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {

    User findUserByID (Integer Id);


}
