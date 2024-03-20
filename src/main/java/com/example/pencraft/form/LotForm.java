package com.example.pencraft.form;

import com.example.pencraft.domain.Lot;
import lombok.*;

import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LotForm {

    private Long lot_id;
    private Integer output;
    private Integer s_count;
    private Integer f_count;
    private String start_time;
    private String end_time;

    private String first_start;
    private String first_end;

    private String second_start;
    private String second_end;

    private String third_start;
    private String third_end;

    private String fourth_start;
    private String fourth_end;

    private Integer status;
    private Long manager_id;
    private String detail;



    public static LotForm toDto(Lot lot) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return new LotForm(
                lot.getLotId(),
                lot.getOutput(),
                lot.getS_count(),
                lot.getF_count(),
                lot.getStart_time().format(df),
                lot.getEnd_time().format(df),
                lot.getFirst_start().format(df),
                lot.getFirst_end().format(df),
                lot.getSecond_start().format(df),
                lot.getSecond_end().format(df),
                lot.getThird_start().format(df),
                lot.getThird_end().format(df),
                lot.getFourth_start().format(df),
                lot.getFourth_end().format(df),
                lot.getStatus(),
                lot.getManager_id(),
                lot.getDetail()
        );
    }
}
