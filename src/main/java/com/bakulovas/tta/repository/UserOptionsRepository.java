package com.bakulovas.tta.repository;

import com.bakulovas.tta.entity.User;
import com.bakulovas.tta.entity.UserOptions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserOptionsRepository extends JpaRepository<UserOptions, Integer> {

    UserOptions findByUser(User user);
}
