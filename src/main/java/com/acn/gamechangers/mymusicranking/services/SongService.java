package com.acn.gamechangers.mymusicranking.services;

import com.acn.gamechangers.mymusicranking.model.Song;

import java.util.List;

public interface SongService {

    public void addSong(Song song);

    public List<Song> readSongs();

    void changeSong(Song song);
}
