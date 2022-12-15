package com.rent.service;

import com.rent.model.entity.Penalty;
import com.rent.repository.PenaltyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class PenaltyService extends AbstractService<Penalty> {
    @Autowired
    private PenaltyRepository penaltyRepository;

    public PenaltyService(JpaRepository<Penalty, Long> repository) {
        super(repository);
    }

    public Penalty condition(Boolean status){
        if (status) return penaltyRepository.findByHeavyConditionIsTrue();

        return penaltyRepository.findByHeavyConditionIsFalse();
    }
}
