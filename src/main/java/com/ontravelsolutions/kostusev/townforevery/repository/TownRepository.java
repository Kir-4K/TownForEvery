package com.ontravelsolutions.kostusev.townforevery.repository;

import com.ontravelsolutions.kostusev.townforevery.entity.Town;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TownRepository extends JpaRepository<Town, Long> {

    Optional<Town> findByNameIgnoreCase(String name);
}
