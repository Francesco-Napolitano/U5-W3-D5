package it.esercitazione.gestione_eventi.controller;

import it.esercitazione.gestione_eventi.entity.Evento;
import it.esercitazione.gestione_eventi.entity.Utente;
import it.esercitazione.gestione_eventi.services.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/eventi")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @PostMapping("/{organizerId}")
    public ResponseEntity<Evento> creaEvento(@RequestBody Evento evento, @PathVariable Long organizerId) {
        Utente organizer = new Utente();
        organizer.setId(organizerId);
        Evento nuovoEvento = eventoService.createEvent(evento, organizer);
        return ResponseEntity.ok(nuovoEvento);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evento> getEventoById(@PathVariable Long id) {
        Optional<Evento> evento = eventoService.getEventById(id);
        return evento.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Evento>> getAllEventi() {
        return ResponseEntity.ok(eventoService.getAllEvents());
    }

    @PutMapping("/{id}/{organizerId}")
    public ResponseEntity<Evento> aggiornaEvento(@PathVariable Long id, @PathVariable Long organizerId, @RequestBody Evento evento) {
        Utente organizer = new Utente();
        organizer.setId(organizerId);
        Evento eventoAggiornato = eventoService.updateEvent(id, evento, organizer);
        return ResponseEntity.ok(eventoAggiornato);
    }

    @DeleteMapping("/{id}/{organizerId}")
    public ResponseEntity<Void> eliminaEvento(@PathVariable Long id, @PathVariable Long organizerId) {
        Utente organizer = new Utente();
        organizer.setId(organizerId);
        eventoService.deleteEvent(id, organizer);
        return ResponseEntity.noContent().build();
    }
}
