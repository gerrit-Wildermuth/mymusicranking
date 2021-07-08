package com.acn.gamechangers.mymusicranking.services;

import com.acn.gamechangers.mymusicranking.model.Song;
import com.acn.gamechangers.mymusicranking.repository.SongRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongServiceImpl implements SongService {

    private final SongRepository songRepository;

    public SongServiceImpl(AlbumServiceImpl albumService, BandServiceImpl bandService, CategoryServiceImpl categoryService, SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public void addSong(Song song) {
        songRepository.save(song);
    }

    public List<Song> readSongs() {
        return songRepository.findAll();
    }

    @Override
    public void changeSong(Song song) {
        songRepository.save(song);
    }
}
