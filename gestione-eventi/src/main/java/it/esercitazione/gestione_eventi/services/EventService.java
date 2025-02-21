package it.esercitazione.gestione_eventi.services;

import it.esercitazione.gestione_eventi.entity.Evento;
import it.esercitazione.gestione_eventi.entity.Utente;
import it.esercitazione.gestione_eventi.repo.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private EventoRepository eventoRepository;

    public Evento event(Evento event, Utente organizer) {
        event.setOrganizzatore(organizer); // Imposta l'organizzatore
        return eventoRepository.save(event);
    }

    public Optional<Evento> getEventById(Long id) {
        return eventoRepository.findById(id);
    }

    public List<Evento> getAllEvents() {
        return eventoRepository.findAll();
    }

    public Evento eEvent(Long id, Evento evento, Utente organizer) {
        return eventoRepository.findById(id).map(event -> {
            if (!event.getOrganizzatore().equals(organizer)) {
                throw new RuntimeException("Non sei l'organizzatore di questo evento!");
            }
            event.setTitle(evento.getTitle());
            event.setDescription(evento.getDescription());
            event.setStartDate(evento.getStartDate());
            event.setLocation(evento.getLocation());
            event.setMaxPartecipants(evento.getMaxPartecipants());
            return eventoRepository.save(event);
        }).orElseThrow(() -> new RuntimeException("Evento non trovato"));
    }

    public void deleteEvent(Long id, Utente organizer) {
        Evento event = eventoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento non trovato"));

        if (!event.getOrganizzatore().equals(organizer)) {
            throw new RuntimeException("Non sei l'organizzatore di questo evento!");
        }

        eventoRepository.deleteById(id);
    }
}

