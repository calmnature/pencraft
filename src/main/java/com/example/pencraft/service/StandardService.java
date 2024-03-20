package com.example.pencraft.service;

import com.example.pencraft.domain.Standard;
import com.example.pencraft.repository.StandardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class StandardService {
    private final StandardRepository standardRepository;

    public Standard createStandard(Standard standard) {
        return standardRepository.save(standard);
    }
    public List<Standard> findStandardAll(){
        return standardRepository.findAll();
    }
}
