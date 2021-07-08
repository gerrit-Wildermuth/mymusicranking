package com.acn.gamechangers.mymusicranking.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Song {
    @Id
    @TableGenerator(name = "custGen", valueColumnName = "id", initialValue = 1000, allocationSize = 1)
    @GeneratedValue(generator = "custGen", strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Size(min = 2, max = 60)
    private String title;
    @Min(0)
    private int likes;
    @Max(10)
    @Min(1)
    private int rating;
    @NotNull
    @Min(0)
    private int plays;
    @Size(min = 2, max = 12)
    private double length;
    @PastOrPresent
    private LocalDate publishedDate;
    private String songCover;//Base64
    @ManyToMany(mappedBy = "songList",
            cascade = CascadeType.DETACH)
    private List<Category> categoryList = new ArrayList<>();
    @ManyToMany(mappedBy = "songList",
            cascade = CascadeType.DETACH)
    private List<Album> albumList = new ArrayList<>();
    @ManyToMany(mappedBy = "songList",
            cascade = CascadeType.DETACH)
    private List<Playlist> playlist = new ArrayList<>();

    public Song(String title, int likes, int rating, int plays, double length, ArrayList<Category> categoryList, ArrayList<Album> albumList) {
        this.title = title;
        this.likes = likes;
        this.rating = rating;
        this.plays = plays;
        this.length = length;
        this.categoryList = categoryList;
        this.albumList = albumList;
    }

    public String toStringWithoutAlbum() {
        return "Song{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", likes=" + likes +
                ", rating=" + rating +
                ", plays=" + plays +
                ", length=" + length +
                ", category=" + categoryList +
                '}';
    }
}
