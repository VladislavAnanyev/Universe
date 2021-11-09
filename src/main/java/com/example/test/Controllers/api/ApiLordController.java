package com.example.test.Controllers.api;

import com.example.test.Model.Lord;
import com.example.test.Service.LordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping(path = "/api")
public class ApiLordController {

    @Autowired
    private LordService lordService;

    @PostMapping(path = "/lord")
    public Lord addLord(@Valid @RequestBody Lord lord) {
        return lordService.addLord(lord);
    }

    @GetMapping(path = "/lords/loungers")
    public Set<Lord> getLoungers() {
        return lordService.getLoungers();
    }

    @GetMapping(path = "/lords/youngest")
    public Set<Lord> getYoungestLords() {
        return lordService.getTop10YoungestLord();
    }
}
