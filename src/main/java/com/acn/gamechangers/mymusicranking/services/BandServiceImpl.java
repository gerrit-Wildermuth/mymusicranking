package com.acn.gamechangers.mymusicranking.services;

import com.acn.gamechangers.mymusicranking.model.Band;
import com.acn.gamechangers.mymusicranking.repository.BandRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BandServiceImpl implements BandService {

    private final BandRepository bandRepository;

    public BandServiceImpl(ArtistServiceImpl artistService, BandRepository bandRepository) {
        this.bandRepository = bandRepository;
    }

    public List<Band> getbandList() {
        return bandRepository.findAll();
    }
}
