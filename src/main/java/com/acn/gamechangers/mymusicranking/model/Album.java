package com.acn.gamechangers.mymusicranking.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Album {
    @Id
    @TableGenerator(name = "custGen", valueColumnName = "id", initialValue = 1000, allocationSize = 1)
    @GeneratedValue(generator = "custGen", strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private LocalDate publicationDate;
    @ManyToOne
    @JoinColumn(name = "band_id")
    private Band band;
    @ManyToMany(cascade = {CascadeType.DETACH})
    @JoinTable(
            name = "album_Song", joinColumns = {@JoinColumn(name = "album_id")},
            inverseJoinColumns = {@JoinColumn(name = "song_id")}
    )
    private List<Song> songList = new ArrayList<>();


    public Album(String title, LocalDate publicationDate, List<Song> songList) {
        this.title = title;
        this.publicationDate = publicationDate;
        this.songList = songList;
    }
}
