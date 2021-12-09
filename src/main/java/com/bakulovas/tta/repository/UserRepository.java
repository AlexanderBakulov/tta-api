package com.bakulovas.tta.repository;


import com.bakulovas.tta.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {


    User getByLogin(String login);

    Set<User> findByLastName(String lastName);

    User getById(int id);



}
