package com.bakulovas.tta.repository.jpa;


import com.bakulovas.tta.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {


    User getByLogin(String login);

}
