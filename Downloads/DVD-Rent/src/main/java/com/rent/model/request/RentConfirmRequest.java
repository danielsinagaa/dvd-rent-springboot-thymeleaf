package com.rent.model.request;

import com.rent.model.entity.Rent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentConfirmRequest {
    private Long id;
    private String memberName;
    private String dvdName;
    private Long deposit;
    private LocalDate rentDate;
    private LocalDate returnDate;
    private String ketJumlah;
    private Boolean dvdCondition;
    private String conditionStatus;
    private Long total;
    private Long denda;
    private Long totalPaid;
    private Long memberId;
    private Long dvdId;

    public RentConfirmRequest(Rent rent) {
        this.id = rent.getId();
        this.memberName = rent.getMember().getName();
        this.dvdName = rent.getDvd().getName();
        this.deposit = rent.getDeposit();
        this.rentDate = rent.getRentDate();
        this.returnDate = LocalDate.now();
        this.dvdCondition = rent.getDvdCondition();
        this.conditionStatus = rent.getDvdCondition() == null ? "TIDAK RUSAK"
                : rent.getDvdCondition() ? "RUSAK BERAT" : "RUSAK RINGAN";
        memberId = rent.getMember().getId();
        dvdId = rent.getDvd().getId();
    }
}
