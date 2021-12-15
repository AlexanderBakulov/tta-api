package com.bakulovas.tta.repository;


import com.bakulovas.tta.entity.Office;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OfficeRepository extends JpaRepository<Office, Integer> {
    Optional<Office> findByName(String name);

    Office getByName(String name);
}
