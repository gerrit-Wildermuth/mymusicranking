package com.acn.gamechangers.mymusicranking.controller;

import com.acn.gamechangers.mymusicranking.model.Album;
import com.acn.gamechangers.mymusicranking.model.Category;
import com.acn.gamechangers.mymusicranking.model.Song;
import com.acn.gamechangers.mymusicranking.services.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.acn.gamechangers.mymusicranking.config.Helper.getPreviousPageByRequest;

@Controller()
@RequestMapping("/songs")
@Log4j2
public class SongController {

    private final SongService songService;
    private final CategoryService categoryService;
    private final AlbumService albumService;

    public SongController(SongServiceImpl songService, CategoryServiceImpl categoryService, AlbumServiceImpl albumService) {
        this.songService = songService;
        this.categoryService = categoryService;
        this.albumService = albumService;
    }

    @GetMapping("/form")
    public ModelAndView showForm() {
        ModelAndView modelAndView = new ModelAndView();
        Song song = new Song();
        song.setReleaseDate(LocalDate.now());
        setModelAndViewContentForSong(song, modelAndView);
        return modelAndView;
    }

    @GetMapping("")
    public ModelAndView readSongs() {
        ModelAndView modelAndView = new ModelAndView();
        List<Song> songList = songService.getSongList();
        modelAndView.addObject("songList", songList);
        modelAndView.setViewName("songs");
        return modelAndView;
    }

    @PostMapping("/form")
    public ModelAndView addSong(@Valid Song song, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        setModelAndViewContentForSong(song, modelAndView);
        if (bindingResult.hasErrors()) {
            return errorHandlingForForm(bindingResult, modelAndView);
        } else {
            saveSongWithAlbumsAndCategory(song, modelAndView);
        }
        return modelAndView;
    }

    @PostMapping("/deleteSong")
    public ModelAndView removeSong(@PathVariable("songsId") Long songId, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(getPreviousPageByRequest(request).orElse("/songs"));
        Optional<Song> byIdOptional = songService.findByIdOptional(songId);
        if (byIdOptional.isEmpty()) {
            modelAndView.addObject("message", "There is no Song with this songId: " + songId);
            log.info("There is no Song with this songId: " + songId);
        } else {
            songService.remove(songId);
            modelAndView.addObject("message", "Song with songId" + songId + " got removed!");
            log.info("Song with songId" + songId + " got removed!");
            List<Song> songList = songService.getSongList();
            modelAndView.addObject("songList", songList);
        }
        return modelAndView;
    }

    @PatchMapping("")
    public ResponseEntity changeSong(@RequestBody Song song) {
        songService.changeSong(song);
        return ResponseEntity.ok().build();
    }

    private void setModelAndViewContentForSong(Song song, ModelAndView modelAndView) {
        modelAndView.addObject("song", song);
        List<Category> categoryList = categoryService.getCategoryList();
        List<Album> albumList = albumService.getAlbumList();
        modelAndView.addObject("categoryList", categoryList);
        modelAndView.addObject("albumList", albumList);
        modelAndView.setViewName("form");
    }

    private void saveSongWithAlbumsAndCategory(Song song, ModelAndView modelAndView) {
        songService.addSong(song);
        List<Album> songAlbums = song.getAlbumList();
        List<Category> songCategories = song.getCategoryList();
        for (Album album : songAlbums) {
            album.addSongAlbum(song);
            albumService.addAlbum(album);
        }
        for (Category category : songCategories) {
            category.addSongCategory(song);
            categoryService.addCategory(category);
        }
        log.info("Song got registered successfully! Song Title: " + song.getTitle());
        modelAndView.addObject("message",
                "Song got registered successfully! Song Title: " + song.getTitle());
    }

    private ModelAndView errorHandlingForForm(BindingResult bindingResult, ModelAndView modelAndView) {
        modelAndView.addObject("message", "Please correct the errors in form!");
        log.info("There where errors in the form!");
        modelAndView.addObject("bindingResult", bindingResult);
        return modelAndView;
    }
}
