package com.rent.controller;

import com.rent.model.entity.DVD;
import com.rent.model.response.DVDResponse;
import com.rent.service.DVDService;
import com.rent.service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

import static com.rent.controller.PengembalianController.getHome;

@Controller
public class DVDController {
    @Autowired
    private DVDService dvdService;

    @Autowired
    private RentService rentService;

    @GetMapping("/dvd")
    public String dvd(Model model){
        return home(model);
    }

    @GetMapping("dvd/save")
    public String newDvd(@ModelAttribute DVD dvd, Model model){
        model.addAttribute("dvd", new DVD());
        return "new_dvd";
    }

    @PostMapping("dvd/save")
    public String postDVD(@ModelAttribute DVD dvd, Model model){
        dvd.setIsRented(false);
        dvdService.save(dvd);

        getHome(model, dvdService, rentService);
        return "home";
    }

    @GetMapping("dvd/edit/{id}")
    public String editDVD(@ModelAttribute DVD dvd, Model model, @PathVariable("id") Long id){
        model.addAttribute("dvd", dvdService.findById(id));
        return "edit_dvd";
    }

    @GetMapping("delete/dvd/{id}")
    public String deleteDVD(Model model, @PathVariable("id") Long id){
        dvdService.deleteById(id);

        return home(model);
    }

    private String home(Model model) {
        List<DVDResponse> dvdAvail = new ArrayList<>();
        List<DVDResponse> dvdRented = new ArrayList<>();
        List<DVDResponse> allDvd = new ArrayList<>();

        dvdService.findByIsRented(true).forEach(it -> dvdAvail.add(new DVDResponse(it)));
        dvdService.findByIsRented(false).forEach(it -> dvdRented.add(new DVDResponse(it)));
        dvdService.findAll().forEach(it -> allDvd.add(new DVDResponse(it)));

        model.addAttribute("allDVD", allDvd);
        model.addAttribute("dvdAvail", dvdAvail);
        model.addAttribute("dvdRented", dvdRented);
        return "dvd";
    }

}
