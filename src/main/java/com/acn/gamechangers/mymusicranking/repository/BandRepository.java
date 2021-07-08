package com.acn.gamechangers.mymusicranking.repository;

import com.acn.gamechangers.mymusicranking.model.Band;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BandRepository extends JpaRepository<Band, Long> {
}
