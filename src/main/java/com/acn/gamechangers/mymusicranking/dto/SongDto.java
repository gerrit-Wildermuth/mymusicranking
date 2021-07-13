package com.acn.gamechangers.mymusicranking.dto;

import com.acn.gamechangers.mymusicranking.model.Album;
import com.acn.gamechangers.mymusicranking.model.Category;
import com.acn.gamechangers.mymusicranking.model.Playlist;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SongDto {
    private String title;
    private int likes;
    private int rating;
    private int plays;
    private double length;
    private LocalDate releaseDate;
    private String songCover;//Base64
    private List<Category> categoryList = new ArrayList<>();
    private List<Album> albumList = new ArrayList<>();
    private List<Playlist> playlist = new ArrayList<>();
}
