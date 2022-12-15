package com.rent.service;

import com.rent.model.entity.Price;
import com.rent.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class PriceService extends AbstractService<Price> {
    @Autowired
    PriceRepository priceRepository;

    public PriceService(JpaRepository<Price, Long> repository) {
        super(repository);
    }

    public Price status(Boolean status){
        if (status) return priceRepository.findByStatusIsTrue();

        return priceRepository.findByStatusIsFalse();
    }
}
