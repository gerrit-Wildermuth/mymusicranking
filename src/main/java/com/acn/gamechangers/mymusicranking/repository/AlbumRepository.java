package com.acn.gamechangers.mymusicranking.repository;

import com.acn.gamechangers.mymusicranking.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {
}
