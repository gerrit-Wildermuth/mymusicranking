package com.acn.gamechangers.mymusicranking.repository;

import com.acn.gamechangers.mymusicranking.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
    
}
