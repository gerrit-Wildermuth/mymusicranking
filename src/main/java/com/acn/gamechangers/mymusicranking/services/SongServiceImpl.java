package com.acn.gamechangers.mymusicranking.services;

import com.acn.gamechangers.mymusicranking.model.Song;
import com.acn.gamechangers.mymusicranking.repository.SongRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class SongServiceImpl implements SongService {

    private final SongRepository songRepository;

    public SongServiceImpl(AlbumServiceImpl albumService, BandServiceImpl bandService, CategoryServiceImpl categoryService, SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    @Override
    public void addSong(Song song) {
        songRepository.save(song);
    }

    @Override
    public ArrayList<Song> getSongList() {
        return (ArrayList<Song>) songRepository.findAll();
    }


    @Override
    public Optional<Song> findByIdOptional(Long songId) {
        return songRepository.findById(songId);
    }

    @Override
    public void changeSong(Song song) {
        songRepository.save(song);
    }

    @Override
    public boolean isBookAlreadyPresent(Song song) {
        Optional<Song> optionalSong = songRepository.findById(song.getId());
        return optionalSong.isPresent();
    }

    @Override
    public void remove(Song song) {
        songRepository.delete(song);
    }

    @Override
    public void remove(Long songId) {
        songRepository.deleteById(songId);
    }
}
