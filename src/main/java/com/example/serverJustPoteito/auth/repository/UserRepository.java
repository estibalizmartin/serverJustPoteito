package com.example.serverJustPoteito.auth.repository;

import com.example.serverJustPoteito.auth.persistence.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {

    Optional<User> findByEmail(String email);

    @Query(value="SELECT * FROM Users u ORDER BY u.id LIMIT ?1 OFFSET ?2", nativeQuery = true)
    List<User> findAllFiltered(int limit, int offset);
}
