package com.example.pencraft.service;

import com.example.pencraft.domain.Lot;
import com.example.pencraft.domain.Product;
import com.example.pencraft.domain.Standard;
import com.example.pencraft.form.CountForm;
import com.example.pencraft.form.LastDataForm;
import com.example.pencraft.repository.LotRepository;
import com.example.pencraft.repository.ProductRepository;
import com.example.pencraft.repository.StandardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.*;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class ProcessService {

    private final StandardRepository standardRepository;
    private final LotRepository lotRepository;
    private final ProductRepository productRepository;
    private final WebSocketSenderService webSocketSenderService;

    private Standard standard; // DB에서 가져온 규격을 저장
    private Lot lot; // Lot Entity
    private List<Product> productList; // 생산된 제품을 저장할 리스트

    private ExecutorService executorService = Executors.newSingleThreadExecutor(); // 생산을 돌릴 쓰레드
    private Future<?> productionFuture; // 생산을 담당할 Future

    // 사용자에게 양품, 불량품을 보내주기 위한 변수
    // 각 공정마다 최초 시작에 두 변수를 0으로 초기화해서 사용
    private int goodCount; private int badCount;

    // 현재 공정 상태 0 : 생산 종료, 1 ~ 4 : N공정 생산 중
    private int nowProcess;

    // 몇초 주기로 클라이언트에게 메세지를 보낼지 시간 설정
    private static final int SEND_MESSAGE_DELAY = 1000;
    // 제품 1개 생산하는데 몇 초마다 생산할지 시간 설정
    private static final int PRODUCTION_DELAY = 100;

    // 생산 for문에서 인덱스(i)가 sendIndex마다 메세지 전송
    private static final int SEND_INDEX = SEND_MESSAGE_DELAY / PRODUCTION_DELAY;

    private int msgSendCount;

    // 클라이언트가 최초로 monitoring 페이지에 접속할 경우 마지막 생산 데이터를 전송
    private Map<String, LastDataForm> lastDataMap = new HashMap<>();

    // 생산 지시 시 실행되는 메서드
    public void processStart(CountForm countForm) throws Exception {
        // standard = standardRepository.findById(1L).orElse(null);
        standard = standardRepository.findById(countForm.getStandard_id()).orElse(null);
        log.info("제품 규격 = {}", standard);
        productionFuture = executorService.submit(processTask(countForm.getCount()));
    }


    // 생산 중단 시 실행되는 메서드
    public void stopProduction() {
        log.warn("제품 생산 중단");
        productionFuture.cancel(true);
        if(nowProcess != 0){
            lastDataMap = new HashMap<>();
        }
    }

    // 사용자에게 메세지를 보내는 메서드
    private void sendMessage(String destination) {
        Map<String, Integer> data = new HashMap<>();
        data.put("goodCount", goodCount);
        data.put("badCount", badCount);
        webSocketSenderService.sendMessageToClient(destination,data);
    }

    // 사용자에게 보낼 LastData를 만들어 반환하는 메서드
    private LastDataForm lastDataCreate() {
        return new LastDataForm(nowProcess, goodCount, badCount);
    }

    // monitoring 페이지 최초 접속 클라이언트에게 보낼
    // LastData에 현재 몇 번째 공정인지 업데이트 하는 메서드
    private void nowProcessUpdate() {
        nowProcess++;
        for(String key : lastDataMap.keySet()){
            lastDataMap.get(key).setNowProcess(nowProcess);
        }
    }

    // 사용자가 최초 접속 시 1~4공정의 LastData를 보내주는 메서드
    public void sendLastDataMap() {
        log.info("최초 접속 사용자에게 보낼 Map 데이터 : " + lastDataMap);
        webSocketSenderService.sendMessageToClient("/process/lastdata", lastDataMap);
    }

    // executorService에 할당할 쓰레드 반환 메서드
    private Runnable processTask(int count) {
        return () -> {
            try {
                processOne(count); // 1공정 메서드
                processTwo(); // 2공정 메서드
                processThree(); // 3공정 메서드
                processFour(); // 4공정 메서드
                saveAll(); // 4공정까지 진행된 Lot, List<Product> 저장 메서드
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    // 1공정 메서드
    private void processOne(int count) throws Exception{
        log.info("1공정 시작");
        nowProcess = 0; // 현재 공정 값 0으로 초기화
        nowProcessUpdate(); // 현재 진행 중인 공정으로 업데이트
        lot = new Lot(); // Lot Entity 생성
        lot.setOutput(count); // 입력받은 생산량 저장
        lot.setStart_time(LocalDateTime.now()); // 생산 시작 시간
        lot.setFirst_start(LocalDateTime.now());  // 1공정 시작 시간
        lot.setStatus(1); // 1 : 진행 중
        productList = new ArrayList<>(); // 제품 리스트 초기화
        processOneLogic(count); // 1공정 생산 로직
        lot.setFirst_end(LocalDateTime.now()); // 1공정 종료 시간
        lastDataMap.put("processOne", lastDataCreate()); // Map에 1공정 데이터 추가
        Thread.sleep(SEND_MESSAGE_DELAY); // N초 대기
        sendMessage("/process/first"); // 1공정 구독 메세지 전송
        log.info("1공정 종료");
    }

    private void processTwo() throws Exception{
        log.info("2공정 시작");
        nowProcessUpdate(); // 현재 진행 중인 공정으로 업데이트
        lot.setSecond_start(LocalDateTime.now()); // 2공정 시작 시간
        processTwoLogic(); // 2공정 생산 로직
        lot.setSecond_end(LocalDateTime.now()); // 2공정 종료 시간
        lastDataMap.put("processTwo", lastDataCreate()); // Map에 2공정 데이터 추가
        Thread.sleep(SEND_MESSAGE_DELAY); // N초 대기
        sendMessage("/process/second"); // 2공정 구독 메세지 전송
        log.info("2공정 종료");
    }

    private void processThree() throws Exception{
        log.info("3공정 시작");
        nowProcessUpdate(); // 현재 진행 중인 공정으로 업데이트
        lot.setThird_start(LocalDateTime.now()); // 3공정 시작 시간
        processThreeLogic(); // 3공정 생산 로직
        lot.setThird_end(LocalDateTime.now()); // 3공정 종료 시간
        lastDataMap.put("processThree", lastDataCreate()); // Map에 3공정 데이터 추가
        Thread.sleep(SEND_MESSAGE_DELAY); // N초 대기
        sendMessage("/process/third"); // 3공정 구독 메세지 전송
        log.info("3공정 종료");
    }

    private void processFour() throws Exception{
        log.info("4공정 시작");
        nowProcessUpdate(); // 현재 진행 중인 공정으로 업데이트
        lot.setFourth_start(LocalDateTime.now()); // 4공정 시작 시간
        processFourLogic(); // 4공정 생산 로직
        lot.setFourth_end(LocalDateTime.now()); // 4공정 종료 시간
        lot.setEnd_time(LocalDateTime.now()); // 생산 종료 시간
        lot.setS_count(goodCount); // 양품 개수
        lot.setF_count(lot.getOutput() - lot.getS_count()); // 불량품 개수
        lot.setStatus(2); // 상태 변경 2 : 종료
        lastDataMap.put("processFour", lastDataCreate()); // Map에 4공정 데이터 추가
        Thread.sleep(SEND_MESSAGE_DELAY); // N초 대기
        sendMessage("/process/fourth"); // 4공정 구독 메세지 전송
        log.info("4공정 종료");
    }

    private void saveAll() throws Exception {
        nowProcess = -1; // 현재 진행 중인 프로세스 -1로 셋팅
        nowProcessUpdate(); // 업데이트
        Lot saveLot = lotRepository.save(lot); // lot Entity 저장
        log.info("저장된 Lot = {}", saveLot);

        for (Product product : productList) {
            product.setLot(lot); // 생산된 제품에 Lot 등록
            product.setStandard(standard); // 생산된 제품에 규격 등록
        }

        List<Product> saveProductList = productRepository.saveAll(productList);
        log.info("저장된 제품 리스트 = {}", saveProductList);
    }

    private void processOneLogic(int count) throws Exception{
        goodCount = 0;
        badCount = 0;
        msgSendCount = 0;
        for (int i = 0; i < count; i++) {
            msgSendCount++; // msgSendCount 1 증가
            Thread.sleep(PRODUCTION_DELAY);
            Random random = new Random();
            Double start = 0.0;
            Double end = 0.0;
            Double volume = 0.0;
            int ran = random.nextInt(1, 101);
            if(ran > 5) {
                if(standard.getStandard_id() == 1L){
                    start = 98.0;
                    end = 102.0;
                }
                else if(standard.getStandard_id() == 2L){
                    start = 196.0;
                    end = 204.0;
                }
            } else {
                boolean bool = random.nextBoolean();
                if(bool){
                    if(standard.getStandard_id() == 1L){
                        start = 90.0;
                        end = 98.0;
                    }
                    else if(standard.getStandard_id() == 2L){
                        start = 190.0;
                        end = 195.5;
                    }
                } else{
                    if(standard.getStandard_id() == 1L){
                        start = 103.0;
                        end = 110.0;
                    }
                    else if(standard.getStandard_id() == 2L){
                        start = 205.0;
                        end = 212.0;
                    }
                }
            }
            volume = Math.round(random.nextDouble(start,end) * 100) / 100.0;

            Product p = new Product();
            p.setVolume(volume);

            // 최소 잉크와 최대 잉크 규격이내라면
            if(standard.getMin_volume() <= volume && standard.getMax_volume() >= volume){
                p.setAcceptance("P"); // 제품 양&불 여부 등록 -> P : Pass(양품)
                goodCount++;
            }
            else {
                p.setAcceptance("F"); // 제품 양&불 여부 등록 -> F : Fail(불양품)
                p.setError_code(1L); // Error 코드 100번 등록
                badCount++;
            }
            productList.add(p);
            if(msgSendCount == SEND_INDEX){
                sendMessage("/process/first"); // 1공정 구독자에게 메세지 전송
                msgSendCount = 0;
            }
        }
    }

    private void processTwoLogic() throws Exception{
        goodCount = 0;
        badCount = 0;
        msgSendCount = 0;

        for (int i = 0; i < productList.size(); i++) {
            msgSendCount++;
            if(productList.get(i).getAcceptance().equals("F")) // 만약 F(불량품)라면
                continue; // 닙을 조립하지 않고 건너 뜀
            Thread.sleep(PRODUCTION_DELAY);

            Random random = new Random();
            Double start = 0.0;
            Double end = 0.0;
            Double nib = 0.0;
            int ran = random.nextInt(1, 101);
            if(ran > 5) {
                start = 0.87; end = 1.00;
            } else {
                boolean bool = random.nextBoolean();
                if(bool){
                    start = 0.8; end = 0.865;
                } else{
                    start = 1.005; end = 1.05;
                }
            }
            nib = Math.round(random.nextDouble(start,end) * 100) / 100.0;

            Product p = productList.get(i);
            p.setNib(nib);

            if(standard.getMin_nib() <= nib && standard.getMax_nib() >= nib){
                // 불량품일 경우 뛰어넘었기 때문에 현재 제품은 무조건 양품
                goodCount++;
            }
            else {
                p.setAcceptance("F");
                p.setError_code(2L); // 에러코드 200번 등록
                badCount++;
            }

            if(msgSendCount == SEND_INDEX){
                sendMessage("/process/second"); // 2공정 구독자에게 메세지 전송
                msgSendCount = 0;
            }
        }
    }

    private void processThreeLogic() throws Exception{
        goodCount = 0;
        badCount = 0;
        msgSendCount = 0;

        for (int i = 0; i < productList.size(); i++) {
            msgSendCount++;
            if(productList.get(i).getAcceptance().equals("F"))
                continue;
            Thread.sleep(PRODUCTION_DELAY);
            Random random = new Random();
            String body = "N";
            int ran = random.nextInt(1, 101);
            if(ran > 5) {
                body = "Y";
            }

            Product p = productList.get(i);
            p.setAssembly_body(body);

            if(body.equals("Y")){
                goodCount++;
            }
            else {
                p.setAcceptance("F");
                p.setError_code(3L);
                badCount++;
            }
            if(msgSendCount == SEND_INDEX){
                sendMessage("/process/third"); // 3공정 구독자에게 메세지 전송
                msgSendCount = 0;
            }
        }
    }

    private void processFourLogic() throws Exception{
        goodCount = 0;
        badCount = 0;
        msgSendCount = 0;
        for (int i = 0; i < productList.size(); i++) {
            msgSendCount++;
            if(productList.get(i).getAcceptance().equals("F"))
                continue;
            Thread.sleep(PRODUCTION_DELAY);
            Random random = new Random();
            String cap = "N";
            int ran = random.nextInt(1, 101);
            if(ran > 5) {
                cap = "Y";
            }

            Product p = productList.get(i);
            p.setAssembly_cap(cap);

            if(cap.equals("Y")){
                goodCount++;
            }
            else {
                p.setAcceptance("F");
                p.setError_code(4L);
                badCount++;
            }
            if(msgSendCount == SEND_INDEX){
                sendMessage("/process/fourth"); // 4공정 구독자에게 메세지 전송
                msgSendCount = 0;
            }
        }
    }
}
