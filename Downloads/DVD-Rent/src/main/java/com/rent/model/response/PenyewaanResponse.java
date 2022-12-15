package com.rent.model.response;

import com.rent.model.entity.Rent;
import lombok.Data;

import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Data
public class PenyewaanResponse {
    private Long id;
    private String nama;
    private String dvd;
    private String tanggalPenyewaan;
    private String tanggalPengembalian;

    public PenyewaanResponse(Rent rent) {
        this.id = rent.getId();
        this.nama = rent.getMember().getName();
        this.dvd = rent.getDvd().getName();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        this.tanggalPenyewaan = formatter.format(rent.getRentDate());
        this.tanggalPengembalian = Objects.isNull(rent.getReturnDate()) ? "BELUM DIKEMBALIKAN" : formatter.format(rent.getReturnDate());
    }
}
