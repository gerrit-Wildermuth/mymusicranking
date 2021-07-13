package com.acn.gamechangers.mymusicranking.controller;

import com.acn.gamechangers.mymusicranking.model.Song;
import com.acn.gamechangers.mymusicranking.services.SongService;
import com.acn.gamechangers.mymusicranking.services.SongServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController()
@RequestMapping("rest/songs")
public class SongRestController {


    private final SongService songService;

    public SongRestController(SongServiceImpl songService) {
        this.songService = songService;
    }

    @GetMapping("/{songsId}")
    public String readSong(@PathVariable("songsId") Long songsId) {
        Song.builder().title("Test").build();
        if (songsId < songService.getSongList().size()) {
            return songService.getSongList().stream().findAny().get().toString();
        }
        return ResponseEntity.badRequest().build().toString();
    }

    @GetMapping("/test")
    public ArrayList<Song> readSongs() {
        return songService.getSongList();
    }

    @GetMapping("/{songsId}/noBand")
    public String readSongsWithoutAlbum(@PathVariable("songsId") Integer songsId) {
        if (songsId < songService.getSongList().size()) {
            return songService.getSongList().stream().findAny().get().toStringWithoutAlbumAndCategory();
        }
        return ResponseEntity.badRequest().build().toString();
    }
}
