package it.esercitazione.gestione_eventi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "eventi")
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String title;
    private String description;
    private String location;
    private LocalDateTime startDate;
    private int maxPartecipants;

    @ManyToOne
    @JoinColumn(name = "organizzatore_id")
    private Utente organizzatore;




}
