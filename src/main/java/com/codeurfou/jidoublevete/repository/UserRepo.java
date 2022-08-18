package com.codeurfou.jidoublevete.repository;

import com.codeurfou.jidoublevete.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Long> {


    Optional<User> findByEmail(String email);
}
