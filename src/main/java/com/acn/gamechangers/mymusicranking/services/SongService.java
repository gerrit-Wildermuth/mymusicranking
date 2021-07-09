package com.acn.gamechangers.mymusicranking.services;

import com.acn.gamechangers.mymusicranking.model.Song;

import java.util.ArrayList;
import java.util.Optional;

public interface SongService {

    public void addSong(Song song);

    ArrayList<Song> getSongList();

    Optional<Song> findByIdOptional(Long songId);

    void changeSong(Song song);

    boolean isBookAlreadyPresent(Song song);

    void remove(Song song);

    void remove(Long songId);
}
