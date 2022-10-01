package com.example.test.controllers.web;

import com.example.test.model.domain.Lord;
import com.example.test.service.LordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class WebLordController {

    private final LordService lordService;

    @GetMapping(path = "/lord/create")
    public String getCreateLordView() {
        return "create-lord";
    }

    @PostMapping(path = "/lord")
    public String createLord(@Valid Lord lord) {
        lordService.addLord(lord.getAge(), lord.getName());
        return "redirect:/lords";
    }

    @GetMapping(path = "/lords")
    public String getAllLordsView(Model model) {
        model.addAttribute("lords", lordService.getAllLords());
        return "lords";
    }


    @GetMapping(path = "/lords/youngest")
    public String getTop10YoungestLord(Model model) {
        model.addAttribute("lords", lordService.getTop10YoungestLord());
        return "lords";
    }

    @GetMapping(path = "/lords/loungers")
    public String getLoungers(Model model) {
        model.addAttribute("lords", lordService.getLoungers());
        return "lords";
    }

    @GetMapping("/")
    public String homePage() {
        return "redirect:/lords";
    }

}
