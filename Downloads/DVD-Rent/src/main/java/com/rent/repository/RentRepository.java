package com.rent.repository;

import com.rent.model.entity.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentRepository extends JpaRepository<Rent, Long> {
    List<Rent> findAllByReturnDateIsNull();
    List<Rent> findAllByReturnDateIsNotNull();
}
