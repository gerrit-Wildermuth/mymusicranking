package com.acn.gamechangers.mymusicranking.services;

import com.acn.gamechangers.mymusicranking.model.Album;
import com.acn.gamechangers.mymusicranking.repository.AlbumRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepository;

    public AlbumServiceImpl(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    public ArrayList<Album> getAlbumList() {
        return (ArrayList<Album>) albumRepository.findAll();
    }
}

