package com.example.pencraft.domain;

import com.example.pencraft.form.LotForm;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@SequenceGenerator(
        name = "lot_seq",
        sequenceName = "lot_seq",
        initialValue = 1, allocationSize = 1)
public class Lot extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lot_seq")
    @Column(name = "lot_id")
    private Long lotId;

    private Integer output;
    private Integer s_count;
    private Integer f_count;
    private LocalDateTime start_time;
    private LocalDateTime end_time;
    private LocalDateTime first_start;
    private LocalDateTime first_end;
    private LocalDateTime second_start;
    private LocalDateTime second_end;
    private LocalDateTime third_start;
    private LocalDateTime third_end;
    private LocalDateTime fourth_start;
    private LocalDateTime fourth_end;
    private Integer status;
    private Long manager_id;
    private String detail;



    public static Lot toEntity(LotForm lotForm) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return new Lot(
                lotForm.getLot_id(),
                lotForm.getOutput(),
                lotForm.getS_count(),
                lotForm.getF_count(),
                LocalDateTime.parse(lotForm.getStart_time(), formatter),
                LocalDateTime.parse(lotForm.getEnd_time(), formatter),
                LocalDateTime.parse(lotForm.getFirst_start(), formatter),
                LocalDateTime.parse(lotForm.getFirst_end(), formatter),
                LocalDateTime.parse(lotForm.getSecond_start(), formatter),
                LocalDateTime.parse(lotForm.getSecond_end(), formatter),
                LocalDateTime.parse(lotForm.getThird_start(), formatter),
                LocalDateTime.parse(lotForm.getThird_end(), formatter),
                LocalDateTime.parse(lotForm.getFourth_start(), formatter),
                LocalDateTime.parse(lotForm.getFourth_end(), formatter),
                lotForm.getStatus(),
                lotForm.getManager_id(),
                lotForm.getDetail()
        );
    }

    public void patch(int s_count, int f_count) {
        this.s_count = s_count;
        this.f_count = f_count;
        this.status = 2;
    }
}
