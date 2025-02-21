package it.esercitazione.gestione_eventi.services;

import it.esercitazione.gestione_eventi.entity.Utente;
import it.esercitazione.gestione_eventi.repo.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UtenteService {

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Utente registerUser(Utente user) {
        // Cripta la password prima di salvare l'utente
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return utenteRepository.save(user);
    }

    public Optional<Utente> findUserByUsername(String username) {
        return utenteRepository.findByUsername(username);
    }
}