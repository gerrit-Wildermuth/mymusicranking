package com.acn.gamechangers.mymusicranking.controller;

import com.acn.gamechangers.mymusicranking.model.Song;
import com.acn.gamechangers.mymusicranking.services.SongServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController("/Rest")
public class SongRestController {

    private final SongServiceImpl songService;

    public SongRestController(SongServiceImpl songService) {
        this.songService = songService;
    }


    @GetMapping("/{songsId}")
    public String readSongs(@PathVariable("songsId") Integer songsId) {
        Song.builder().title("Test").build();
        if (songsId < songService.getSongList().size()) {
            return songService.getSongList().stream().findAny().get().toString();
        }
        return ResponseEntity.badRequest().build().toString();
    }

    @GetMapping("/{songsId}/noBand")
    public String readSongsWithoutAlbum(@PathVariable("songsId") Integer songsId) {
        if (songsId < songService.getSongList().size()) {
            return songService.getSongList().stream().findAny().get().toStringWithoutAlbum();
        }
        return ResponseEntity.badRequest().build().toString();
    }
}
