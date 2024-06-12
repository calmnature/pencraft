package com.example.pencraft.service;

import com.example.pencraft.domain.Error;
import com.example.pencraft.domain.Product;
import com.example.pencraft.repository.ErrorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ErrorService {

    private final ErrorRepository errorRepository;

    public List<Error> saveAllError(List<Error> list){
        return errorRepository.saveAll(list);
    }

    public List<Error> findAllErrors() {
        return errorRepository.findAll();
    }
}
