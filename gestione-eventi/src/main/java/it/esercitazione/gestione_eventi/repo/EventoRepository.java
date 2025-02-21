package it.esercitazione.gestione_eventi.repo;


import it.esercitazione.gestione_eventi.entity.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {
}
