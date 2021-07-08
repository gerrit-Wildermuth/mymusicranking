package com.acn.gamechangers.mymusicranking.repository;

import com.acn.gamechangers.mymusicranking.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {
}
