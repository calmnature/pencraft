package com.example.pencraft.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class OccurrenceError {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "process_id")
    private Process process;

    @ManyToOne
    @JoinColumn(name = "error_id")
    private Error error;

    @ManyToOne
    @JoinColumn(name = "lot_id")
    private Lot lot;
}
