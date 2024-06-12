package com.example.pencraft.service;

import com.example.pencraft.domain.Lot;
import com.example.pencraft.domain.Product;
import com.example.pencraft.form.LotCountForm;
import com.example.pencraft.form.LotForm;
import com.example.pencraft.form.ProductForm;
import com.example.pencraft.repository.LotRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class LotService {

    private final LotRepository lotRepository;
    private final WebSocketSenderService webSocketSenderService;
    private List<Lot> lotList;
    private final String CLASSIFY_DAY = "DAY";
    private final String CLASSIFY_MONTH = "MONTH";
    private final String CLASSIFY_YEAR = "YEAR";


    public Lot save(Lot lot) {
        return lotRepository.save(lot);
    }

    public List<Lot> saveAllLot(List<Lot> list) {
        return lotRepository.saveAll(list);
    }

    public List<Lot> findLotAll() {
        return lotRepository.findAll();
    }

    public Page<Lot> findAllPage(int no, int size) {
        Pageable pageable = PageRequest.of(no, size, Sort.by(Sort.Direction.DESC, "lotId"));
        return lotRepository.findAll(pageable);
    }

    public List<LotForm> entityToDto(List<Lot> content) {
        List<LotForm> formList = new ArrayList<>();

        for(Lot l : content){
            formList.add(LotForm.toDto(l));
        }
        return formList;
    }

    public Lot findByLotId(Long id) {
        return lotRepository.findByLot_id(id);
    }

    public void allDateSend() {
        dayDataSend(); // 일별 데이터를 보내줄 메서드
        monthDataSend(); // 월별 데이터를 보내줄 메서드
        yearDataSend(); // 연간 데이터를 보내줄 메서드
    }

    public void dayDataSend() {
        lotList = findLot(CLASSIFY_DAY); // 금일 기준 -N일 까지의 데이터를 lotList에 저장
        Map<String, LotCountForm> data = createMap(CLASSIFY_DAY); // 클라이언트에게 보낼 데이터를 정제하여 data에 저장
        webSocketSenderService.sendMessageToClient("/process/daychart", data); // /process/daychart 구독자에게 전송
    }

    public void monthDataSend() {
        lotList = findLot(CLASSIFY_MONTH); // 금년 기준 1월 ~ 12월 까지의 데이터를 lotList에 저장
        Map<String, LotCountForm> data = createMap(CLASSIFY_MONTH); // 클라이언트에게 보낼 데이터를 정제하여 data에 저장
        webSocketSenderService.sendMessageToClient("/process/monthchart", data); // /process/monthchart 구독자에게 전송
    }

    public void yearDataSend() {
        lotList = findLot(CLASSIFY_YEAR); // 모든 데이터를 lotList에 저장
        Map<String, LotCountForm> data = createMap(CLASSIFY_YEAR); // 클라이언트에게 보낼 데이터를 정제하여 data에 저장
        webSocketSenderService.sendMessageToClient("/process/yearchart", data); // /process/yearchart 구독자에게 전송
    }

    private List<Lot> findLot(String classification) {
        final int BEFORE_DAY = 2; // 몇일 전까지 데이터를 가져올 지 정할 상수
        LocalDateTime now = LocalDateTime.now(); // 현재 시간 저장
        DateTimeFormatter formatter = null;
        String compare; // Repository에 보낼 비교 데이터
        switch(classification){
            case CLASSIFY_DAY : // case가 "DAY"일 경우
                formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                compare = now.minusDays(BEFORE_DAY).format(formatter);
                lotList = lotRepository.findByBeforeDays(compare);
                break;
            case CLASSIFY_MONTH : // case가 "MONTH"일 경우
                formatter = DateTimeFormatter.ofPattern("yyyy");
                compare = now.format(formatter) + "%";
                lotList = lotRepository.findByMonth(compare);
                break;
            case CLASSIFY_YEAR : // case가 "YEAR"일 경우
                lotList = lotRepository.findAll();
                break;
        }
        return lotList;
    }

    private Map<String, LotCountForm> createMap(String classification) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        switch (classification) { // formatter에 알맞는 패턴을 넣어준 것
            case CLASSIFY_DAY :
                formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                break;
            case CLASSIFY_MONTH :
                formatter = DateTimeFormatter.ofPattern("yyyy-MM");
                break;
            case CLASSIFY_YEAR :
                formatter = DateTimeFormatter.ofPattern("yyyy");
                break;
        }

        Map<String, LotCountForm> data = new HashMap<>();
        log.info("LotList = {}",lotList);
        for(Lot lot : lotList) {
            log.info("Lot = {}",lot);
            String lotCountKey = lot.getEnd_time().format(formatter);
            int goodCount = lot.getS_count(); // 해당 Lot의 양품 수량 저장
            int badCount = lot.getF_count(); // 해당 Lot의 불량품 수량 저장
            // 저장한 양품과 불량품으로 LotCountForm 생성
            LotCountForm newLotCount = new LotCountForm(goodCount, badCount);

            if(data.get(lotCountKey) == null){
                data.put(lotCountKey, newLotCount);
            } else{
                LotCountForm origin = data.get(lotCountKey);
                origin.setGoodCount(origin.getGoodCount() + newLotCount.getGoodCount());
                origin.setBadCount(origin.getBadCount() + newLotCount.getBadCount());
            }
        }
        return data;
    }
}
