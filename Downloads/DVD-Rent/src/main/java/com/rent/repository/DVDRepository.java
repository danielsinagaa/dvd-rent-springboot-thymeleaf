package com.rent.repository;

import com.rent.model.entity.DVD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DVDRepository extends JpaRepository<DVD, Long> {

    public List<DVD> findByIsRented(Boolean isRented);
    public List<DVD> findAllByIsRentedFalse();
}
