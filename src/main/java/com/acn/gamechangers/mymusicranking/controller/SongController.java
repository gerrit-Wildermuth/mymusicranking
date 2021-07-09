package com.acn.gamechangers.mymusicranking.controller;

import com.acn.gamechangers.mymusicranking.model.Album;
import com.acn.gamechangers.mymusicranking.model.Category;
import com.acn.gamechangers.mymusicranking.model.Song;
import com.acn.gamechangers.mymusicranking.services.AlbumServiceImpl;
import com.acn.gamechangers.mymusicranking.services.CategoryServiceImpl;
import com.acn.gamechangers.mymusicranking.services.SongServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
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

    private final SongServiceImpl songService;
    private final CategoryServiceImpl categoryService;
    private final AlbumServiceImpl albumService;

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
        List<Category> categoryList = categoryService.getCategoryList();
        List<Album> albumList = albumService.getAlbumList();
        modelAndView.addObject("categoryList", categoryList);
        modelAndView.addObject("albumList", albumList);
        modelAndView.addObject("song", song);
        modelAndView.setViewName("form");
        log.log(Level.INFO, "");
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
        modelAndView.addObject("song", song);
        List<Category> categoryList = categoryService.getCategoryList();
        List<Album> albumList = albumService.getAlbumList();
        modelAndView.addObject("categoryList", categoryList);
        modelAndView.addObject("albumList", albumList);
        modelAndView.setViewName("form");
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("message", "Please correct the errors in form!");
            log.info("There where errors in the form!");
            modelAndView.addObject("bindingResult", bindingResult);
            return modelAndView;
        } else {
           /* List<Album> songAlbums = song.getAlbumList();
            List<Category> songCategories = song.getCategoryList();
            for (Album album : songAlbums) {
                album.addSongAlbum(song);
                albumService.addAlbum(album);
            }
            for (Category category : songCategories) {
                category.addSongCategory(song);
                categoryService.addCategory(category);
            }*/
            
            songService.addSong(song);
            modelAndView.addObject("message",
                    "Song got registered successfully! Song Title: " + song.getTitle());
        }
        return modelAndView;
    }

    @PostMapping("/deleteSong")
    public ModelAndView removeSong(@RequestParam Long songId, HttpServletRequest request) {
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
}
