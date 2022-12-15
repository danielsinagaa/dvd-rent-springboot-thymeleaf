package com.rent.controller;

import com.rent.model.entity.DVD;
import com.rent.model.entity.Rent;
import com.rent.model.request.NewRentRequest;
import com.rent.service.DVDService;
import com.rent.service.MemberService;
import com.rent.service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;

@Controller
public class SewaController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private DVDService dvdService;

    @Autowired
    private RentService rentService;

    @GetMapping("/sewa")
    public String sewa(Model model){

        model.addAttribute("members", memberService.findAll());
        model.addAttribute("dvd", dvdService.findAllIsRented());
        model.addAttribute("newRent", new NewRentRequest());

        return "sewa";
    }

    @PostMapping("/sewa")
    public String addRent(@ModelAttribute("newRent")NewRentRequest request, Model model){
        Rent rent = new Rent();
        DVD dvd = dvdService.findById(request.getDvdId());
        rent.setRentDate(LocalDate.now());
        rent.setMember(memberService.findById(request.getMemberId()));
        rent.setDvd(dvd);
        rent.setDeposit(100000L);
        rentService.save(rent);
        dvd.setIsRented(true);
        dvdService.save(dvd);

        model.addAttribute("members", memberService.findAll());
        model.addAttribute("dvd", dvdService.findAllIsRented());
        model.addAttribute("newRent", new NewRentRequest());

        return "sewa";
    }
}
