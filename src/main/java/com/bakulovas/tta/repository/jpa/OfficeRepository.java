package com.bakulovas.tta.repository.jpa;


import com.bakulovas.tta.entity.Office;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfficeRepository extends JpaRepository<Office, Integer> {
    Office getByName(String name);
}
