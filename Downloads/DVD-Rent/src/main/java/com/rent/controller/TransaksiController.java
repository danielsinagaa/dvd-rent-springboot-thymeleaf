package com.rent.controller;

import com.rent.model.entity.Penalty;
import com.rent.model.entity.Price;
import com.rent.model.response.DVDResponse;
import com.rent.model.response.PenyewaanResponse;
import com.rent.service.DVDService;
import com.rent.service.PenaltyService;
import com.rent.service.PriceService;
import com.rent.service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TransaksiController {

    @Autowired
    private DVDService dvdService;

    @Autowired
    private RentService rentService;

    @Autowired
    private PriceService priceService;

    @Autowired
    private PenaltyService penaltyService;


    @GetMapping("/transaksi")
    public String transaksi(Model model,
                            @ModelAttribute("penaltyNew") Penalty penaltyNew, @ModelAttribute("penaltyOld") Penalty penaltyOld,
                            @ModelAttribute("priceOld") Price priceOld, @ModelAttribute("priceNew") Price priceNew){


        return getTransaksi(model);
    }

    @PostMapping("/harga/baru")
    public String ubahHargaBaru(@ModelAttribute("priceNew") Price priceNew, Model model){
        Price price = priceService.status(true);
        price.setPrice(priceNew.getPrice());
        priceService.save(price);

        return getTransaksi(model);
    }

    @PostMapping("/harga/lama")
    public String ubahHargaLama(@ModelAttribute("priceOld") Price priceOld, Model model){
        Price price = priceService.status(false);
        price.setPrice(priceOld.getPrice());
        priceService.save(price);

        return getTransaksi(model);
    }

    @PostMapping("/denda/berat")
    public String ubahDendaBerat(@ModelAttribute("penaltyNew") Penalty penaltyNew, Model model){
        Penalty penalty = penaltyService.condition(true);
        penalty.setPrice(penaltyNew.getPrice());
        penaltyService.save(penalty);

        return getTransaksi(model);
    }

    @PostMapping("/denda/ringan")
    public String ubahDendaRingan(@ModelAttribute("penaltyOld") Penalty penaltyOld, Model model){
        Penalty penalty = penaltyService.condition(true);
        penalty.setPrice(penaltyOld.getPrice());
        penaltyService.save(penalty);

        return getTransaksi(model);
    }

    private String getTransaksi(Model model) {
        List<DVDResponse> dvdResponses = new ArrayList<>();
        List<DVDResponse> dvdAvail = new ArrayList<>();
        List<DVDResponse> dvdRented = new ArrayList<>();
        List<PenyewaanResponse> penyewaan = new ArrayList<>();

        dvdService.findAll().forEach(it -> dvdResponses.add(new DVDResponse(it)));
        dvdService.findByIsRented(true).forEach(it -> dvdAvail.add(new DVDResponse(it)));
        dvdService.findByIsRented(false).forEach(it -> dvdRented.add(new DVDResponse(it)));
        rentService.findAll().forEach(it -> penyewaan.add(new PenyewaanResponse(it)));

        model.addAttribute("priceOld", priceService.status(false));
        model.addAttribute("priceNew", priceService.status(true));
        model.addAttribute("dvd", dvdResponses);
        model.addAttribute("dvdAvail", dvdAvail);
        model.addAttribute("dvdRented", dvdRented);
        model.addAttribute("penyewaan", penyewaan);
        model.addAttribute("totalPemasukkan", rentService.income());

        return "transaksi";
    }
}
