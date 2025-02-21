package it.esercitazione.gestione_eventi.services;

import it.esercitazione.gestione_eventi.entity.Evento;
import it.esercitazione.gestione_eventi.entity.Prenotazione;
import it.esercitazione.gestione_eventi.entity.Utente;
import it.esercitazione.gestione_eventi.repo.EventoRepository;
import it.esercitazione.gestione_eventi.repo.PrenotazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrenotazioneService {

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    @Autowired
    private EventoRepository eventoRepository;

    public Prenotazione bookEvent(Utente user, Evento event) {
        if (event.getMaxPartecipants() <= 0) {
            throw new RuntimeException("Nessun posto disponibile per questo evento!");
        }

        Prenotazione prenotazione = new Prenotazione();
        prenotazione.setUtente(user);
        prenotazione.setEvento(event);

        event.setMaxPartecipants(event.getMaxPartecipants() - 1);
        eventoRepository.save(event);

        return prenotazioneRepository.save(prenotazione);
    }

    public List<Prenotazione> getUserPrenotaziones(Utente utente) {
        return prenotazioneRepository.findByUtente(utente);
    }

    public void cancelPrenotazione(Long prenotazioneId, Utente user) {
        Prenotazione prenotazione = prenotazioneRepository.findById(prenotazioneId)
                .orElseThrow(() -> new RuntimeException("Prenotazione non trovata"));

        if (!prenotazione.getUtente().equals(user)) {
            throw new RuntimeException("Non puoi cancellare una prenotazione di un altro utente!");
        }

        Evento event = prenotazione.getEvento();
        event.setMaxPartecipants(event.getMaxPartecipants() + 1);
        eventoRepository.save(event);

        prenotazioneRepository.delete(prenotazione);
    }
}
