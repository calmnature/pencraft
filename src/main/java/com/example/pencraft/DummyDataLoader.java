package com.example.pencraft;

import com.example.pencraft.domain.Employees;
import com.example.pencraft.domain.Error;
import com.example.pencraft.domain.Lot;
import com.example.pencraft.domain.Standard;
import com.example.pencraft.service.EmployeesService;
import com.example.pencraft.service.ErrorService;
import com.example.pencraft.service.LotService;
import com.example.pencraft.service.StandardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@Component
@Slf4j
@RequiredArgsConstructor
public class DummyDataLoader implements CommandLineRunner{

    private final EmployeesService employeesService;
    private final StandardService standardService;
    private final ErrorService errorService;
    private final LotService lotService;


    @Override
    public void run(String... args) throws Exception {
        if(employeesService.findEmployees().isEmpty()) {
            Employees employees = new Employees(null, "10000", "윤종원", "admin");
            Long saveEntity = employeesService.save(employees);
            log.info("저장된 Employee_id = {}", saveEntity);
        }

        if(standardService.findStandardAll().isEmpty()){
            Standard standard100 = new Standard(null, "마카2mm", 98.0, 102.0, 0.87, 1.00, "y", "y");
            Standard standard200 = new Standard(null, "마카2mm대용량", 196.0, 204.0, 0.87, 1.00, "y", "y");
            Standard save100 = standardService.createStandard(standard100);
            Standard save200 = standardService.createStandard(standard200);
            log.info("save100 = {}", save100);
            log.info("save200 = {}", save200);
        }

        if(lotService.findLotAll().isEmpty()) {
            List<Error> errorList = new ArrayList<>();
            errorList.add(new Error(100L, "1공정에러"));
            errorList.add(new Error(200L, "2공정에러"));
            errorList.add(new Error(300L, "3공정에러"));
            errorList.add(new Error(400L, "4공정에러"));
            List<Error> saveErrorList = errorService.saveAllError(errorList);
            log.info("saveErrorList = {}", saveErrorList);

            List<Lot> lotList = createLotList();
            List<Lot> saveLotList = lotService.saveAllLot(lotList);
            log.info("saveLotList = {}", saveLotList);
        }
    }

    private List<Lot> createLotList() {
        List<Lot> createList = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Integer[] outputList = new Integer[]{
                77777, 100000, 150000, 16000, 9800, 6200, 550, 430, 560, 555, 250, 300, 500, 500
        };

        Integer[] sCountList = new Integer[]{
                70000, 97655, 143333, 15539, 9563, 5983, 500, 415, 531, 513, 210, 283, 390, 421
        };

        Integer[] fCountList = new Integer[]{
                7777, 2345, 6667, 461, 237, 217, 50, 15, 29, 42, 40, 17, 110, 79
        };

        String[] startList = new String[]{
                "2021-05-10 13:00:00", "2022-06-06 09:30:00", "2023-12-10 09:30:00", "2024-01-11 09:30:00", "2024-02-05 09:30:00", "2024-02-10 09:30:00", "2024-03-06 09:30:00", "2024-03-07 09:30:00", "2024-03-08 09:30:00", "2024-03-09 09:30:00", "2024-03-13 09:30:00", "2024-03-13 09:30:00", "2024-03-14 09:30:00", "2024-03-14 09:30:00"
        };
        String[] endList = new String[]{
                "2021-05-11 02:59:54", "2022-06-06 17:59:54", "2023-12-10 17:59:54", "2024-01-11 17:59:54", "2024-02-05 17:59:54", "2024-02-10 17:59:54", "2024-03-06 17:59:54", "2024-03-07 17:59:54", "2024-03-08 17:59:54", "2024-03-09 17:59:54", "2024-03-13 17:59:54", "2024-03-13 17:59:54", "2024-03-14 17:59:54", "2024-03-14 17:59:54"
        };
        String[] firstStratList = new String[]{
                "2021-05-10 13:00:00", "2022-06-06 09:30:00", "2023-12-10 09:30:00", "2024-01-11 09:30:00", "2024-02-05 09:30:00", "2024-02-10 09:30:00", "2024-03-06 09:30:00", "2024-03-07 09:30:00", "2024-03-08 09:30:00", "2024-03-09 09:30:00", "2024-03-13 09:30:00", "2024-03-13 09:30:00", "2024-03-14 09:30:00", "2024-03-14 09:30:00"
        };
        String[] firstEndList = new String[]{
                "2021-05-10 16:05:32", "2022-06-06 12:38:44", "2023-12-10 12:38:44", "2024-01-11 12:38:44", "2024-02-05 12:38:44", "2024-02-10 12:38:44", "2024-03-06 12:38:44", "2024-03-07 12:38:44", "2024-03-08 12:38:44", "2024-03-09 12:38:44", "2024-03-13 12:38:44", "2024-03-13 12:38:44", "2024-03-14 12:38:44", "2024-03-14 12:38:44"
        };
        String[] secondStratList = new String[]{
                "2021-05-10 16:05:33", "2022-06-06 12:38:45", "2023-12-10 12:38:45", "2024-01-11 12:38:45", "2024-02-05 12:38:45", "2024-02-10 12:38:45", "2024-03-06 12:38:45", "2024-03-07 12:38:45", "2024-03-08 12:38:45", "2024-03-09 12:38:45", "2024-03-13 12:38:45", "2024-03-13 12:38:45", "2024-03-14 12:38:45", "2024-03-14 12:38:45"
        };
        String[] secondEndList = new String[]{
                "2021-05-10 19:05:33", "2022-06-06 14:12:19", "2023-12-10 14:12:19", "2024-01-11 14:12:19", "2024-02-05 14:12:19", "2024-02-10 14:12:19", "2024-03-06 14:12:19", "2024-03-07 14:12:19", "2024-03-08 14:12:19", "2024-03-09 14:12:19", "2024-03-13 14:12:19", "2024-03-13 14:12:19", "2024-03-14 14:12:19", "2024-03-14 14:12:19"
        };
        String[] thirdStratList = new String[]{
                "2021-05-10 19:05:34", "2022-06-06 14:12:20", "2023-12-10 14:12:20", "2024-01-11 14:12:20", "2024-02-05 14:12:20", "2024-02-10 14:12:20", "2024-03-06 14:12:20", "2024-03-07 14:12:20", "2024-03-08 14:12:20", "2024-03-09 14:12:20", "2024-03-13 14:12:20", "2024-03-13 14:12:20", "2024-03-14 14:12:20", "2024-03-14 14:12:20"
        };
        String[] thirdEndList = new String[]{
                "2021-05-10 23:58:44", "2022-06-06 15:44:13", "2023-12-10 15:44:13", "2024-01-11 15:44:13", "2024-02-05 15:44:13", "2024-02-10 15:44:13", "2024-03-06 15:44:13", "2024-03-07 15:44:13", "2024-03-08 15:44:13", "2024-03-09 15:44:13", "2024-03-13 15:44:13", "2024-03-13 15:44:13", "2024-03-14 15:44:13", "2024-03-14 15:44:13"
        };
        String[] fourthStratList = new String[]{
                "2021-05-10 23:58:45", "2022-06-06 15:44:14", "2023-12-10 15:44:14", "2024-01-11 15:44:14", "2024-02-05 15:44:14", "2024-02-10 15:44:14", "2024-03-06 15:44:14", "2024-03-07 15:44:14", "2024-03-08 15:44:14", "2024-03-09 15:44:14", "2024-03-13 15:44:14", "2024-03-13 15:44:14", "2024-03-14 15:44:14", "2024-03-14 15:44:14"
        };
        String[] fourthEndList = new String[]{
                "2021-05-11 02:59:54", "2022-06-06 17:59:54", "2023-12-10 17:59:54", "2024-01-11 17:59:54", "2024-02-05 17:59:54", "2024-02-10 17:59:54", "2024-03-06 17:59:54", "2024-03-07 17:59:54", "2024-03-08 17:59:54", "2024-03-09 17:59:54", "2024-03-13 17:59:54", "2024-03-13 17:59:54", "2024-03-14 17:59:54", "2024-03-14 17:59:54"
        };

        for(int i = 0; i < startList.length; i++){
            Lot lot = new Lot(null, outputList[i], sCountList[i], fCountList[i], LocalDateTime.parse(startList[i], formatter), LocalDateTime.parse(endList[i], formatter), LocalDateTime.parse(firstStratList[i], formatter), LocalDateTime.parse(firstEndList[i], formatter), LocalDateTime.parse(secondStratList[i], formatter), LocalDateTime.parse(secondEndList[i], formatter), LocalDateTime.parse(thirdStratList[i], formatter), LocalDateTime.parse(thirdEndList[i], formatter), LocalDateTime.parse(fourthStratList[i], formatter), LocalDateTime.parse(fourthEndList[i], formatter), 2, null, null);

            createList.add(lot);
        }
        return createList;
    }

}


