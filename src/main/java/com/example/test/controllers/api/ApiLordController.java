package com.example.test.controllers.api;

import com.example.test.model.dto.CreateLordRequest;
import com.example.test.model.dto.LordDTO;
import com.example.test.service.LordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class ApiLordController {

    @Autowired
    private LordService lordService;

    @PostMapping(path = "/lord")
    public LordDTO addLord(@Valid @RequestBody CreateLordRequest lord) {
        return lordService.addLord(
                lord.getAge(),
                lord.getName()
        );
    }

    @GetMapping(path = "/lords/loungers")
    public List<LordDTO> getLoungers() {
        return lordService.getLoungers();
    }

    @GetMapping(path = "/lords/youngest")
    public List<LordDTO> getYoungestLords() {
        return lordService.getTop10YoungestLord();
    }
}
