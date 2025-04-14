package com.example.yourcare.Repository;

import com.example.yourcare.Model.Stack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StackRepository extends JpaRepository<Stack,Integer> {
    @Query("select d from Stack d where d.id=?1")
    Stack findServiceStoreById(Integer id);
    @Query("select d from Stack d where d.numberAmbulance=?1")
    Stack findServiceStoreByNumberAmbulance(Integer number);
}
