package it.esercitazione.gestione_eventi.controller;

import it.esercitazione.gestione_eventi.entity.Evento;
import it.esercitazione.gestione_eventi.entity.Prenotazione;
import it.esercitazione.gestione_eventi.entity.Utente;
import it.esercitazione.gestione_eventi.services.PrenotazioneService;
import it.esercitazione.gestione_eventi.services.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/prenotazioni")
public class PrenotazioneController {

    @Autowired
    private PrenotazioneService prenotazioneService;

    @Autowired
    private EventoService eventoService;

    @PostMapping("/{userId}/{eventId}")
    public ResponseEntity<Prenotazione> prenotaEvento(@PathVariable Long userId, @PathVariable Long eventId) {
        Utente user = new Utente();
        user.setId(userId);

        Optional<Evento> eventoOpt = eventoService.getEventById(eventId);
        if (eventoOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Prenotazione prenotazione = prenotazioneService.bookEvent(user, eventoOpt.get());
        return ResponseEntity.ok(prenotazione);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Prenotazione>> getPrenotazioniUtente(@PathVariable Long userId) {
        Utente user = new Utente();
        user.setId(userId);
        return ResponseEntity.ok(prenotazioneService.getUserPrenotaziones(user));
    }

    @DeleteMapping("/{prenotazioneId}/{userId}")
    public ResponseEntity<Void> cancellaPrenotazione(@PathVariable Long prenotazioneId, @PathVariable Long userId) {
        Utente user = new Utente();
        user.setId(userId);
        prenotazioneService.cancelPrenotazione(prenotazioneId, user);
        return ResponseEntity.noContent().build();
    }
}
