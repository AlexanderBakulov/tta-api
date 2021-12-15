package com.bakulovas.tta.repository;


import com.bakulovas.tta.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByLogin(String login);

    Set<User> findByLastName(String lastName);


    Optional<User>  findByLoginAndLastName(String login, String lastname);

}
