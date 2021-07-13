package com.acn.gamechangers.mymusicranking.mapper;

import com.acn.gamechangers.mymusicranking.dto.SongDto;
import com.acn.gamechangers.mymusicranking.model.Song;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SongMapper {
    SongDto toDto(Song song);

    Song toSong(SongDto songDto);
}
