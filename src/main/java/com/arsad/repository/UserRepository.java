package com.arsad.repository;

import com.arsad.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/* Created by Arsad on 2023-03-25 19:54 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserName(String userName);

    @Modifying
    @Query("UPDATE User SET password=:encPwd WHERE id=:userId")
    void updateUserPassword(String encPwd, Long userId);
}
