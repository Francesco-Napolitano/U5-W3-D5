package it.esercitazione.gestione_eventi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "utenti")
public class Utente {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private  Long id;
    private String username;
    private String password;
    private String role;

    @OneToMany
    @JoinColumn(name = "utente_id")
    private Evento evento;


}
