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
    //에러찾는 로직
    public Error findById(Long id) {
        return errorRepository.findByProduct_errorCode(id);
    }

    //전체 에러 기준을 가져옴
    public List<Error> findAllErrors() {
        return errorRepository.findAll();
    }

    
    //에러 코드에 따른 에러 내용 받는 로직
    public String whereIsError(Long id) {
        Optional<Error> result = errorRepository.findById(id);
        if (result.isPresent()) {
            return result.get().getError_detail();
        } else {
            return "Undefine Error";
        }
    }

    public Page<Error> findAllPage(int no, int size) {
        Pageable pageable = PageRequest.of(no, size);
        return errorRepository.findAll(pageable);
    }

}
