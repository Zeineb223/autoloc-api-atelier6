
package tn.esprit.autoloc.service;

import tn.esprit.autoloc.entity.Client;
import java.util.List;
import java.util.Optional;

public interface IClientService {

    Client enregistrerClient(Client client);

    List<Client> afficherClients();

    Optional<Client> chercherClient(Long id);

    Client modifierClient(Long id, Client client);

    void supprimerClient(Long id);
}
