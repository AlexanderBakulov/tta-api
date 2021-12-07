package com.bakulovas.tta.repository;

import com.bakulovas.tta.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository  extends JpaRepository<Role, Integer> {
    Role getByName(String name);
}
