package com.example.test.service;

import com.example.test.model.domain.Lord;
import com.example.test.model.dto.LordDTO;
import com.example.test.repository.LordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LordService {

    private final LordRepository lordRepository;

    public LordDTO addLord(Integer age, String name) {
        Lord lord = new Lord(name, age);
        lordRepository.save(lord);

        return new LordDTO()
                .setAge(lord.getAge())
                .setId(lord.getId())
                .setName(lord.getName());
    }

    public List<LordDTO> getLoungers() {
        List<Lord> loungers = lordRepository.getLoungers();
        return map(loungers);
    }

    public List<LordDTO> getTop10YoungestLord() {
        List<Lord> top10YoungestLord = lordRepository.getTop10YoungestLord();
        return map(top10YoungestLord);
    }

    public ArrayList<Lord> getAllLords() {
        return (ArrayList<Lord>) lordRepository.findAll();
    }

    private List<LordDTO> map(Iterable<Lord> lords) {
        List<LordDTO> lordDTOS = new ArrayList<>();
        for (Lord lord : lords) {
            lordDTOS.add(
                    new LordDTO()
                            .setAge(lord.getAge())
                            .setId(lord.getId())
                            .setName(lord.getName())
            );
        }
        return lordDTOS;
    }
}
