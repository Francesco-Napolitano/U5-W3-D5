package it.esercitazione.gestione_eventi.repo;


import it.esercitazione.gestione_eventi.entity.Prenotazione;
import it.esercitazione.gestione_eventi.entity.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {
    List<Prenotazione> findByUtente(Utente user);
}
