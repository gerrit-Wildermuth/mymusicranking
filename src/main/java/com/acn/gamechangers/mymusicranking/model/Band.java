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
public class Band {
    @Id
    @TableGenerator(name = "custGen", valueColumnName = "id", initialValue = 1000, allocationSize = 1)
    @GeneratedValue(generator = "custGen", strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private LocalDate creationDate;
    @OneToMany(mappedBy = "band")
    private List<Artist> artists = new ArrayList<>();
    @OneToMany(mappedBy = "band")
    private List<Album> albumList = new ArrayList<>();

    public Band(String title, LocalDate creationDate, List<Artist> artists) {
        this.title = title;
        this.creationDate = creationDate;
        this.artists = artists;
    }

}