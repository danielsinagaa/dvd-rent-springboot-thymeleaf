package com.rent.controller;

import com.rent.service.DVDService;
import com.rent.service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import static com.rent.controller.PengembalianController.getHome;

@Controller
public class HomeController {
    @Autowired
    private DVDService dvdService;

    @Autowired
    private RentService rentService;

    @GetMapping
    public String home(Model model){
        return getHome(model, dvdService, rentService);
    }
}
