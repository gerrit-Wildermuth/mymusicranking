package com.acn.gamechangers.mymusicranking.controller;

import com.acn.gamechangers.mymusicranking.model.Album;
import com.acn.gamechangers.mymusicranking.model.Category;
import com.acn.gamechangers.mymusicranking.model.Song;
import com.acn.gamechangers.mymusicranking.repository.AlbumRepository;
import com.acn.gamechangers.mymusicranking.repository.CategoryRepository;
import com.acn.gamechangers.mymusicranking.repository.SongRepository;
import com.acn.gamechangers.mymusicranking.services.SongServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller()
@RequestMapping("/songs")
public class SongController {

    private final SongServiceImpl songService;
    private final CategoryRepository categoryRepository;
    private final AlbumRepository albumRepository;
    private final SongRepository songRepository;

    public SongController(SongServiceImpl songService, CategoryRepository categoryRepository, AlbumRepository albumRepository, SongRepository songRepository) {
        this.songService = songService;
        this.categoryRepository = categoryRepository;
        this.albumRepository = albumRepository;
        this.songRepository = songRepository;
    }

    @GetMapping("/{songsId}")
    @ResponseBody
    public String readSongs(@PathVariable("songsId") Integer songsId) {
        Song.builder().title("Test").build();
        if (songsId < songService.readSongs().size()) {
            return songService.readSongs().stream().findAny().get().toString();
        }
        return ResponseEntity.badRequest().build().toString();
    }

    @GetMapping("/{songsId}/noBand")
    public String readSongsWithoutAlbum(@PathVariable("songsId") Integer songsId) {
        if (songsId < songService.readSongs().size()) {
            return songService.readSongs().stream().findAny().get().toStringWithoutAlbum();
        }
        return ResponseEntity.badRequest().build().toString();
    }

    @GetMapping("/form")
    public ModelAndView showForm() {
        ModelAndView modelAndView = new ModelAndView();
        Song song = new Song();
        List<Category> categoryList = categoryRepository.findAll();
        List<Album> albumList = albumRepository.findAll();
        modelAndView.addObject("categoryList", categoryList);
        modelAndView.addObject("albumList", albumList);
        modelAndView.addObject("song", song);
        modelAndView.setViewName("form");
        return modelAndView;
    }

    @GetMapping("")
    public ModelAndView readSongs() {
        ModelAndView modelAndView = new ModelAndView();
        List<Song> songList = songRepository.findAll();
        modelAndView.addObject("songList", songList);
        modelAndView.setViewName("songs");
        return modelAndView;
    }

    @PostMapping("/form")
    public ModelAndView addSong(@Valid Song song, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("song", song);
        List<Category> categoryList = categoryRepository.findAll();
        List<Album> albumList = albumRepository.findAll();
        modelAndView.addObject("categoryList", categoryList);
        modelAndView.addObject("albumList", albumList);
        modelAndView.addObject("bindingResult", bindingResult);
        modelAndView.setViewName("form");
        if (bindingResult.hasErrors()) {
            return modelAndView;
        }
        songService.addSong(song);
        return modelAndView;
    }

    @PostMapping("/deleteSong")
    public ModelAndView removeSong(@RequestParam Long songId) {
        songService.remove(songId);
        ModelAndView modelAndView = new ModelAndView();
        List<Song> songList = songRepository.findAll();
        modelAndView.addObject("songList", songList);
        modelAndView.setViewName("songs");
        return modelAndView;
    }

    @PatchMapping("")
    public ResponseEntity changeSong(@RequestBody Song song) {
        songService.changeSong(song);
        return ResponseEntity.ok().build();
    }
}
