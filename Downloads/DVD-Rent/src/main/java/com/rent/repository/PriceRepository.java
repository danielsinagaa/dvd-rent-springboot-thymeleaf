package com.rent.repository;

import com.rent.model.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {
    public Price findByStatusIsTrue();
    public Price findByStatusIsFalse();
}
