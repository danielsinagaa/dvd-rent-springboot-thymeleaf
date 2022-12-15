package com.rent.repository;

import com.rent.model.entity.Penalty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PenaltyRepository extends JpaRepository<Penalty, Long> {
    public Penalty findByHeavyConditionIsTrue();
    public Penalty findByHeavyConditionIsFalse();
}
