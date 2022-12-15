package com.rent.model.response;

import com.rent.model.entity.DVD;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DVDResponse {
    private Long id;
    private String nama;
    private String jenis;
    private String status;
    private String rented;

    public DVDResponse(DVD dvd) {
        id = dvd.getId();
        nama = dvd.getName();
        jenis = dvd.getNewFilm() ? "BARU" : "LAMA";
        status = dvd.getIsRented() ? "DISEWA" : "TERSEDIA";
        rented = dvd.getIsRented() ? "none" : "auto";
    }
}
