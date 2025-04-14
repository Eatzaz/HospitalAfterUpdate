package com.example.yourcare.Service;

import com.example.yourcare.Model.User;
import com.example.yourcare.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public List<User>getAllUser(){
        List<User> user=userRepository.findAll();
        return user;
    }
    public void addUser(User user){
        userRepository.save(user);
    }
    public boolean updateUser(Integer id,User user){
        User user1=userRepository.findUserByIdUser(id);
        if(user1==null){
            return false;
        }
        user1.setName(user.getName());
        user1.setEmail(user.getEmail());
        user1.setPhoneNumber(user.getPhoneNumber());
        user1.setAge(user.getAge());
        user1.setStatus(user.getStatus());
        userRepository.save(user1);
        return true;
    }
    public boolean deleteUser(Integer id){
        User user=userRepository.findUserByIdUser(id);
        if(user==null){
            return false;
        }
        userRepository.delete(user);
        return true;
    }
// 15:He takes the ID of the user and makes sure if he is old and with special needs or young and with special needs or middle age
    public String WhatStatus(Integer userId){
        User user=userRepository.findUserByIdUser(userId);
        if(user==null){
            return "User Not Found";
        }
        if(user.getStatus().equals("Spical needs")&&user.getAge()>=60){
            return "The user is elderly and has special needs.";
        }
        if(user.getStatus().equals("Spical needs")&&user.getAge()<60&&user.getAge()>=20){

            return "User The user is Not elderly";
    }
        return "The user is underage";
    }
    //16 :It takes age and disease and extracts patients with this disease with the same required age
    public List<User> getDisease(String disease,Integer age){
        List<User> user=userRepository.findUserByDisease(disease,age);
        if(user==null){
            return null;
        }
        for(int i=0;i<user.size();i++) {
            if (user.get(i).getDisease().equals(disease) && user.get(i).getAge()==age) {
                return user;
            }
        }
        return null;
    }



}
