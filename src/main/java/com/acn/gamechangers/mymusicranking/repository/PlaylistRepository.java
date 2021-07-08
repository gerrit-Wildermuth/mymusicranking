package com.acn.gamechangers.mymusicranking.repository;

import com.acn.gamechangers.mymusicranking.model.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
}
