package com.acn.gamechangers.mymusicranking.services;

import com.acn.gamechangers.mymusicranking.model.Artist;

import java.util.List;
import java.util.Optional;

public interface ArtistService {
    public List<Artist> getArtistList();

    Optional<Artist> getArtistById(Long artistId);
}
