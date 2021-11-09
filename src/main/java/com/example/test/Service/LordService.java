package com.example.test.Service;

import com.example.test.Model.Lord;
import com.example.test.Repository.LordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class LordService {

    private final LordRepository lordRepository;

    public Lord addLord(Lord lord) {
        return lordRepository.save(lord);
    }

    public Set<Lord> getLoungers() {
        return lordRepository.getLoungers();
    }

    public Set<Lord> getTop10YoungestLord() {
        return lordRepository.getTop10YoungestLord();
    }

    public ArrayList<Lord> getAllLords() {
        return (ArrayList<Lord>) lordRepository.findAll();
    }
}
