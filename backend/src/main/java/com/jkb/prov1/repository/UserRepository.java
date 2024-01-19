package com.jkb.prov1.repository;

import com.jkb.prov1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByName(String name);

    @Query("select u.password from User u where u.name = :name")
    String getPasswordByName(@Param("name") String name);
}
