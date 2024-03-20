package com.example.pencraft.form;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class PassFailProductForm {

    private int first_s_count;
    private int first_f_count;

    private int second_s_count;
    private int second_f_count;

    private int third_s_count;
    private int third_f_count;

    private int fourth_s_count;
    private int fourth_f_count;

}
