package com.ontravelsolutions.kostusev.townforevery.repository;

import com.ontravelsolutions.kostusev.townforevery.entity.Town;
import com.ontravelsolutions.kostusev.townforevery.entity.TownInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TownInformationRepository extends JpaRepository<TownInformation, Long> {

    List<TownInformation> findAllByTown(Town town);

    List<TownInformation> findAllByTown_NameIgnoreCase(String town);
}
