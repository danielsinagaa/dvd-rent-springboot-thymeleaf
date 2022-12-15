package com.rent.model.request;

import com.rent.model.entity.Rent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentFormRequest {
    private Long id;
    private String memberName;
    private String dvdName;
    private Boolean dvdCondition;
    private LocalDate rentDate;
    private LocalDate tanggalPengembalian = LocalDate.now();

    public RentFormRequest(Rent rent) {
        this.id = rent.getId();
        this.memberName = rent.getMember().getName();
        this.dvdName = rent.getDvd().getName();
        this.dvdCondition = rent.getDvdCondition();
        this.rentDate = rent.getRentDate();
        this.tanggalPengembalian = LocalDate.now();
    }
}
