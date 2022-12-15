package com.rent.service;

import com.rent.model.entity.DVD;
import com.rent.repository.DVDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DVDService extends AbstractService<DVD> {
    @Autowired
    private DVDRepository dvdRepository;

    public DVDService(JpaRepository<DVD, Long> repository) {
        super(repository);
    }

    public List<DVD> findByIsRented(Boolean isRented){
        return dvdRepository.findByIsRented(isRented);
    }

    public List<DVD> findAllIsRented(){ return dvdRepository.findAllByIsRentedFalse();}
}
