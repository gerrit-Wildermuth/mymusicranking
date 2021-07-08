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
public class Playlist {
    @Id
    @TableGenerator(name = "custGen", valueColumnName = "id", initialValue = 1000, allocationSize = 1)
    @GeneratedValue(generator = "custGen", strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private LocalDate creationDate;
    @ManyToMany(cascade = {CascadeType.DETACH})
    @JoinTable(
            name = "playlist_Song", joinColumns = {@JoinColumn(name = "playlist_id")},
            inverseJoinColumns = {@JoinColumn(name = "song_id")}
    )
    private List<Song> songList = new ArrayList<>();
}
