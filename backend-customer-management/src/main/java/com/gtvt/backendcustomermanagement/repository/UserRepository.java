package com.gtvt.backendcustomermanagement.repository;

import com.gtvt.backendcustomermanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUserName(String userName);
}
