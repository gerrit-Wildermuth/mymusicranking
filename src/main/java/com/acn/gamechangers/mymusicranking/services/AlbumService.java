package com.acn.gamechangers.mymusicranking.services;

import com.acn.gamechangers.mymusicranking.model.Album;

import java.util.List;

public interface AlbumService {
    public List<Album> getAlbumList();

    void addAlbum(Album album);
}
