package com.acn.gamechangers.mymusicranking.services;

import com.acn.gamechangers.mymusicranking.model.Playlist;
import com.acn.gamechangers.mymusicranking.repository.PlaylistRepository;

public class PlaylistServiceImpl implements PlaylistService {
    private final PlaylistRepository playlistRepository;

    public PlaylistServiceImpl(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    @Override
    public void addSong(Playlist playlist) {
        playlistRepository.save(playlist);
    }

    @Override
    public void createPlaylist(Playlist playlist) {

    }
}
