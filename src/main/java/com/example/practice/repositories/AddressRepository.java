package com.example.practice.repositories;

import com.example.practice.entities.Address;
import com.example.practice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface AddressRepository extends JpaRepository<Address, String> {

    @Query("""
        select a from Address a, User u where a.userId = u.id and u.id = :user_id
    """)
    List<Address> findAddressByUserId(@Param("user_id") String userId);
}
