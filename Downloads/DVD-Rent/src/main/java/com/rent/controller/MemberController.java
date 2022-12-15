package com.rent.controller;

import com.rent.model.entity.DVD;
import com.rent.model.entity.Member;
import com.rent.model.response.PenyewaanResponse;
import com.rent.service.MemberService;
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

@Controller
public class MemberController {

    @Autowired
    RentService rentService;

    @Autowired
    MemberService memberService;

    @GetMapping("/member")
    public String member(Model model){
        return getMember(model);
    }

    @GetMapping("member/save")
    public String newMemberForm(@ModelAttribute Member member, Model model){
        model.addAttribute("member", new Member());
        return "new_member";
    }

    @PostMapping("member/save")
    public String saveMember(@ModelAttribute Member member, Model model){
        memberService.save(member);

        return getMember(model);
    }

    @GetMapping("member/edit/{id}")
    public String newMemberForm(@ModelAttribute Member member, Model model, @PathVariable("id") Long id){
        model.addAttribute("member", memberService.findById(id));
        return "edit_member";
    }

    private String getMember(Model model) {
        List<PenyewaanResponse> penyewaan = new ArrayList<>();
        rentService.findAllRent().forEach(it -> penyewaan.add(new PenyewaanResponse(it)));

        model.addAttribute("member", memberService.findAll());
        model.addAttribute("penyewaan", penyewaan);
        return "member";
    }
}
