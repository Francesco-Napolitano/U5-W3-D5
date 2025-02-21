package it.esercitazione.gestione_eventi.repo;


import it.esercitazione.gestione_eventi.entity.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, Long> {
}
