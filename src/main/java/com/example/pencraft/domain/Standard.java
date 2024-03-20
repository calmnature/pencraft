package com.example.pencraft.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Standard {
    @Id
    @GeneratedValue
    private Long standard_id;

    private String name;
    private Double min_volume;
    private Double max_volume;
    private Double min_nib;
    private Double max_nib;
    private String body;
    private String cap;
}
