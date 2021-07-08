package com.acn.gamechangers.mymusicranking.services;

import com.acn.gamechangers.mymusicranking.model.Artist;
import com.acn.gamechangers.mymusicranking.repository.ArtistRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ArtistServiceImpl implements ArtistService {

    private final ArtistRepository artistRepository;

    public ArtistServiceImpl(ArtistRepository artistRepository) {
//        artistList.add(new Artist("Niklas", "Keiser", null, "Gesang, Gitarre"));
//        artistList.add(new Artist("Christian", "Knippen", null, "Gitarre"));
//        artistList.add(new Artist("Lukas ", "Bruns", null, "Bass"));
//        artistList.add(new Artist("Steffen", "Pott", null, "Schlagzeug"));
        this.artistRepository = artistRepository;
    }

    public ArrayList<Artist> getArtistList() {
        return (ArrayList<Artist>) artistRepository.findAll();
    }

    @Override
    public Optional<Artist> getArtistById(Long artistId) {
        return artistRepository.findById(artistId);
    }
}
