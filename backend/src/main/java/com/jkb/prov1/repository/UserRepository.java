package com.jkb.prov1.repository;

import com.jkb.prov1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
