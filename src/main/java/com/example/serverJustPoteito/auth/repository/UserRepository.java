package com.example.serverJustPoteito.auth.repository;

import com.example.serverJustPoteito.auth.persistence.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

@Transactional
public interface UserRepository extends CrudRepository<User, Integer> {

    Optional<User> findByEmail(String email);
    @Modifying
    @Query(value = "UPDATE users SET password = ?1 WHERE email = ?2", nativeQuery = true)
    int resetPassword(String encodedNewPassword, String email);
    @Query(value = "SELECT * FROM Users u ORDER BY u.id LIMIT ?1 OFFSET ?2", nativeQuery = true)
    List<User> findAllFiltered(int limit, int offset);
    @Modifying
    @Query(value = "UPDATE users SET password = ?1 WHERE email = ?2 AND password = ?3", nativeQuery = true)
    int updatePassword(String newPassword, String email, String oldPassword);
}
