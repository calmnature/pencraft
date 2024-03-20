package com.example.pencraft.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LastDataForm {
    private int nowProcess;
    private int goodCount;
    private int badCount;
}