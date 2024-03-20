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
                // '2024-03-12'의 포맷 생성
                formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                // 현재 2024-03-12T10:11:32.485743라고 가정
                // now.minusDay(4) => 현재 날짜 -4일 => 2024-03-08T10:11:32.485743
                // (2024-03-08T10:11:32.485743).format(formatter) => "2024-03-08"
                compare = now.minusDays(BEFORE_DAY).format(formatter);
                // findByFiveDays Query = "SELECT * FROM lot WHERE end_time >= :compare"
                // end_time이 2024-03-08보다 큰 것 -> 즉, 2024-03-08 ~ 2024-03-12 현재시간까지 데이터를 가져옴
                lotList = lotRepository.findByBeforeDays(compare);
                break;
            case CLASSIFY_MONTH : // case가 "MONTH"일 경우
                // '2024'의 포맷 생성
                formatter = DateTimeFormatter.ofPattern("yyyy");
                // '2024%'라고 하여 쿼리문에서 비교할 조건(?) 생성
                compare = now.format(formatter) + "%";
                // findByMonth Query = "SELECT * FROM lot WHERE end_time like :compare"
                // end_time like "2024%" -> 2024년으로 시작하는 모든 데이터를 가져옴
                lotList = lotRepository.findByMonth(compare);
                break;
            case CLASSIFY_YEAR : // case가 "YEAR"일 경우
                // 모든 데이터 추출
                lotList = lotRepository.findAll();
                break;
        }
        return lotList;
    }

    private Map<String, LotCountForm> createMap(String classification) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // 클라이언트에게 보낼 Key값의 포맷을 만드는 switch문
        switch (classification) { // formatter에 알맞는 패턴을 넣어준 것
            case CLASSIFY_DAY :
                // 일일 데이터의 키 값은 "2024-03-12", "2024-03-11" 등
                formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                break;
            case CLASSIFY_MONTH :
                // 월별 데이터의 키 값은 "2024-01", "2024-02" 등
                formatter = DateTimeFormatter.ofPattern("yyyy-MM");
                break;
            case CLASSIFY_YEAR :
                // 연별 데이터 키 값은 "2024"
                formatter = DateTimeFormatter.ofPattern("yyyy");
                break;
        }
        // 사용자에게 보낼 데이터를 Map으로 생성(JSON 문자열 형태로 받기 위해)
        Map<String, LotCountForm> data = new HashMap<>();
        log.info("LotList = {}",lotList);
        for(Lot lot : lotList) {
            // List<Lot> lotList
            log.info("Lot = {}",lot);
            // lot의 end_time의 포맷을 위에서 정한 formatter 형식으로 변경
            // '2024-03-12' / '2024-01' / '2024' 등
            String lotCountKey = lot.getEnd_time().format(formatter);
            int goodCount = lot.getS_count(); // 해당 Lot의 양품 수량 저장
            int badCount = lot.getF_count(); // 해당 Lot의 불량품 수량 저장
            // 저장한 양품과 불량품으로 LotCountForm 생성
            LotCountForm newLotCount = new LotCountForm(goodCount, badCount);

            // 다음과 같이 조건문을 나눈 이유는 Map.put을 했을 때 이미 있는 Key값이라면 Value값이 덮어씌워지기 때문
            if(data.get(lotCountKey) == null){
                /*
                    만약 Map.get(키)를 했을 때 null이라면 (해당 Key의 Value가 없다는 의미)
                    Map.put()으로 Key값과 Value값을 추가
                    Ex) lotCountKey = '2024-03-12', lotCount(goodCount = 10, badCount = 1)
                    => 클라이언트 수신 시 : '2024-03-12'={goodCount : 10, badCount : 1}의 형식으로 수신
                 */
                data.put(lotCountKey, newLotCount);
            } else{
                /*
                    Map.get(키)를 했을 때 null이 아니라면 (해당 Key에 해당하는 Value가 있다는 의미)
                    해당 .get(키)를 하면 LotCountForm(Value)이 추출
                    기존의 LotCountForm에 있는 goodCount와 badCount에
                    새로운 데이터의 goodCount와 badCount를 더한 뒤 기존 데이터에 저장
                 */
                LotCountForm origin = data.get(lotCountKey);
                origin.setGoodCount(origin.getGoodCount() + newLotCount.getGoodCount());
                origin.setBadCount(origin.getBadCount() + newLotCount.getBadCount());
            }
        }
        return data;
    }
}
