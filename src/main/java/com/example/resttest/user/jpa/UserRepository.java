package com.example.resttest.user.jpa;

import com.example.resttest.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
