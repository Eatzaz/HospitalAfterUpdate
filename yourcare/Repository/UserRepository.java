package com.example.yourcare.Repository;

import com.example.yourcare.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {
    @Query("select u from User u where u.idUser=?1")
    User findUserByIdUser(Integer id);
    @Query("select u from User u where u.disease=?1 and u.age=?2")
    List<User>findUserByDisease(String disease,Integer age);
    @Query("select u from User u where u.age=?1 ")
    List<User> findUserByAge(Integer ageMin);
}
