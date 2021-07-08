package com.acn.gamechangers.mymusicranking.controller;

import com.acn.gamechangers.mymusicranking.services.ArtistServiceImpl;
import org.springframework.web.bind.annotation.RestController;

@RestController("/artist")
public class ArtistController {

    private final ArtistServiceImpl artistService;


    public ArtistController(ArtistServiceImpl artistService) {
        this.artistService = artistService;
    }

//    @GetMapping("/{artistId}")
//    public String readArtist(@PathVariable("artistId") Long artistId) {
//        Optional<Artist> optionalArtist = artistService.getArtistById(artistId);
//        if (optionalArtist.isPresent()) {
//            return optionalArtist.get().toString();
//        }
//        return ResponseEntity.badRequest().toString();
//    }
}
