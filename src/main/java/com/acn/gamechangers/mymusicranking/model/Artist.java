package com.acn.gamechangers.mymusicranking.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Artist {
    @Id
    @TableGenerator(name = "custGen", valueColumnName = "id", initialValue = 1000, allocationSize = 1)
    @GeneratedValue(generator = "custGen", strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private String role;
    @Transient
    private int age;
    @ManyToOne
    @JoinColumn(name = "band_id")
    private Band band;

    public Artist(String firstName, String lastName, LocalDate birthday, String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.role = role;
    }
}