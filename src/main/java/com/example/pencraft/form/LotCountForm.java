package com.example.pencraft.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LotCountForm {
    private Integer goodCount;
    private Integer badCount;
}
