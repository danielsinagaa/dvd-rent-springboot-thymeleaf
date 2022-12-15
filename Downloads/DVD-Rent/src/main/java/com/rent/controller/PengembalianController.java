package com.rent.controller;

import com.rent.model.entity.DVD;
import com.rent.model.entity.Rent;
import com.rent.model.request.RentConfirmRequest;
import com.rent.model.request.RentFormRequest;
import com.rent.model.response.DVDResponse;
import com.rent.model.response.PenyewaanResponse;
import com.rent.repository.DVDRepository;
import com.rent.service.DVDService;
import com.rent.service.MemberService;
import com.rent.service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PengembalianController {
    @Autowired
    private RentService rentService;

    @Autowired
    private DVDService dvdService;

    @Autowired
    private MemberService memberService;
    @Autowired
    private DVDRepository dVDRepository;

    @GetMapping("/dvdReturn")
    public String returnDvd(Model model){
        List<PenyewaanResponse> penyewaan = new ArrayList<>();
        rentService.findAllRent().forEach(it -> penyewaan.add(new PenyewaanResponse(it)));

        model.addAttribute("penyewaan", penyewaan);
        return "pengembalian";
    }

    @GetMapping("/dvdReturn/form/{id}")
    public String returnDvd(@PathVariable("id") Long id, Model model, @ModelAttribute RentFormRequest request){
        Rent rent = rentService.findById(id);
        RentFormRequest rentFormRequest = new RentFormRequest(rent);

        model.addAttribute("formRent", rentFormRequest);
        model.addAttribute("rent", rentFormRequest);
        return "pengembalian_form";
    }

    @PostMapping("/dvdReturn/{id}")
    public String returnForm(@PathVariable("id") Long id,
                             Model model, @ModelAttribute(name = "rentConfirm") RentFormRequest request){
        Rent rent = rentService.findById(id);
        rent.setDvdCondition(request.getDvdCondition());
        RentConfirmRequest rentConfirm = rentService.rentConfirm(rent);

        model.addAttribute("rent", rentConfirm);
        model.addAttribute("rentConfirm", rentConfirm);
        return "pengembalian_confirm";
    }

    @PostMapping("/dvdReturn/confirm/{id}")
    public String confirmForm(@PathVariable("id")Long id,
            Model model, @ModelAttribute(name = "rentConfirm") RentConfirmRequest request){
        Rent rent = new Rent(request);
        DVD dvd = dvdService.findById(request.getDvdId());
        rent.setMember(memberService.findById(request.getMemberId()));
        rent.setDvd(dvd);

        rentService.save(rent);
        dvd.setIsRented(false);
        dvdService.save(dvd);

        return getHome(model, dvdService, rentService);
    }

    static String getHome(Model model, DVDService dvdService, RentService rentService) {
        List<DVDResponse> dvdResponses = new ArrayList<>();
        List<DVDResponse> dvdAvail = new ArrayList<>();
        List<DVDResponse> dvdRented = new ArrayList<>();
        List<PenyewaanResponse> penyewaan = new ArrayList<>();

        dvdService.findAll().forEach(it -> dvdResponses.add(new DVDResponse(it)));
        dvdService.findByIsRented(true).forEach(it -> dvdAvail.add(new DVDResponse(it)));
        dvdService.findByIsRented(false).forEach(it -> dvdRented.add(new DVDResponse(it)));
        rentService.findAll().forEach(it -> penyewaan.add(new PenyewaanResponse(it)));

        model.addAttribute("dvd", dvdResponses);
        model.addAttribute("dvdAvail", dvdAvail);
        model.addAttribute("dvdRented", dvdRented);
        model.addAttribute("penyewaan", penyewaan);

        return "home";
    }

}
