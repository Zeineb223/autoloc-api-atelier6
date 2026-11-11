
package tn.esprit.autoloc.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.entity.Client;
import tn.esprit.autoloc.repository.IClientRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements IClientService {

    private final IClientRepository clientRepository;

    public ClientServiceImpl(IClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client enregistrerClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public List<Client> afficherClients() {
        return clientRepository.findAll();
    }

    @Override
    public Optional<Client> chercherClient(Long id) {
        return clientRepository.findById(id);
    }

    @Override
    public Client modifierClient(Long id, Client client) {
        Client existant = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client introuvable"));

        existant.setNom(client.getNom());
        existant.setPrenom(client.getPrenom());
        existant.setEmail(client.getEmail());
        existant.setTelephone(client.getTelephone());
        existant.setNumPermis(client.getNumPermis());
        existant.setDateInscription(client.getDateInscription());

        return clientRepository.save(existant);
    }

    @Override
    public void supprimerClient(Long id) {
        clientRepository.deleteById(id);
    }
}
