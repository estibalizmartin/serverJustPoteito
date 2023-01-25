package com.example.serverJustPoteito.auth.repository;

import com.example.serverJustPoteito.auth.persistence.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {

    Optional<User> findByEmail(String email);

    /*
    @Query(value="SELECT * FROM Users u ORDER BY u.id LIMIT ?1 OFFSET ?2", nativeQuery = true)
    List<User> findAllFiltered(int limit, int offset);
    */
}
