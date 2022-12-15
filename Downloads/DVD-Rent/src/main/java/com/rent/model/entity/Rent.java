package com.rent.model.entity;

import com.rent.model.request.RentConfirmRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "RENT")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "dvd_id")
    private DVD dvd;

    private Long deposit;
    private LocalDate rentDate;
    private LocalDate returnDate;
    private Boolean dvdCondition;
    private Long totalPaid;

    public Rent(RentConfirmRequest req) {
        this.id = req.getId();
        this.member = member;
        this.dvd = dvd;
        this.deposit = req.getDeposit();
        this.rentDate = req.getRentDate();
        this.returnDate = req.getReturnDate();
        this.dvdCondition = req.getConditionStatus().equalsIgnoreCase("TIDAK RUSAK") ? null
        : !req.getConditionStatus().equalsIgnoreCase("RUSAK RINGAN");
        this.totalPaid = req.getTotalPaid();
    }
}
