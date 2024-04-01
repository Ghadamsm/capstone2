package com.example.hospital.Service;

import com.example.hospital.API.ApiException;
import com.example.hospital.Model.User;
import com.example.hospital.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository ;


    public List<User> getAll(){
        return userRepository.findAll();
    }


    public void addUser(User user){
        userRepository.save(user);
    }


    public void updateUser(Integer Id , User user){

        User u = userRepository.findUserByID(Id);

        if (u == null){
            throw new ApiException("Invalid Id");
        }

        u.setName(user.getName());
        u.setEmail(user.getEmail());
        u.setAge(user.getAge());
        u.setPassword(user.getPassword());
        u.setUserLocation(user.getUserLocation());
        u.setPhoneNumber(user.getPhoneNumber());


        userRepository.save(u);

    }



    public void deleteUser(Integer Id){

        User u = userRepository.findUserByID(Id);

        if (u == null){
            throw new ApiException("Invalid Id");
        }

        userRepository.delete(u);

    }



}
