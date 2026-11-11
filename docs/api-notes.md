
# Atelier 5 — AutoLoc API REST

## A. Tableau des endpoints REST

L'application AutoLoc expose des services REST avec Spring Boot et Spring MVC.

URL de base : http://localhost:8080/api

| Ressource | GET liste | GET par ID | POST | PUT | DELETE |
|---|---|---|---|---|---|
| Clients | Oui | Oui | Oui | Oui | Oui |
| Véhicules | Oui | Oui | Oui | Oui | Oui |
| Agences | Oui | Oui | Oui | Oui | Oui |
| Réservations | Oui | Oui | Oui | Oui | Oui |
| Contrats | Oui | Oui | Oui | Oui | Oui |
| Employés | Oui | Oui | Oui | Oui | Oui |
| Équipements | Oui | Oui | Oui | Oui | Oui |
| Maintenances | Oui | Oui | Oui | Oui | Oui |
| Paiements | Oui | Oui | Non | Non | Non |

Codes HTTP utilisés :
- 200 OK : consultation ou modification réussie.
- 201 Created : création réussie.
- 204 No Content : suppression réussie.
- 400 Bad Request : données invalides.
- 404 Not Found : ressource introuvable.
- 405 Method Not Allowed : méthode non autorisée.

## B. Gestion JSON avec Jackson

Jackson transforme les objets Java en JSON et inversement.

Dans l'entité Contrat, l'annotation
@JsonManagedReference("contrat-paiements")
permet de sérialiser la liste des paiements.

Dans l'entité Paiement, l'annotation
@JsonBackReference("contrat-paiements")
évite une boucle infinie entre Contrat et Paiement.

La relation @OneToMany avec CascadeType.ALL permet
d'enregistrer les paiements associés à un contrat.

Dans ContratServiceImpl, chaque paiement est associé
à son contrat avant l'enregistrement.

La suppression en cascade reste à confirmer par un test.

## C. Tests Postman et Swagger

Swagger UI permet de consulter la documentation
de l'API et de tester les endpoints.

Adresse :
http://localhost:8080/swagger-ui.html

Postman permet d'envoyer des requêtes HTTP
et de contrôler les réponses du serveur.

Tests réalisés :
- GET /api/clients : 200 OK.
- POST /api/clients : 201 Created.
- GET /api/clients/1 : 200 OK.
- PUT /api/clients/1 : 200 OK.
- DELETE /api/clients/1 : 204 No Content.
- GET /api/clients/99999 : 404 Not Found.
- POST /api/paiements : 405 Method Not Allowed.
- GET /api/paiements : 200 OK.
- POST /api/contrats : 201 Created.
- GET /api/paiements : deux paiements retrouvés.

Une tentative de suppression d'un contrat inexistant
a retourné 404 Not Found.

## D. Analyse SonarQube for IDE

Le plugin SonarQube for IDE a été installé
dans IntelliJ IDEA.

L'analyse automatique est activée.

Au moment de la consultation, le panneau Findings
affichait "No Issues" et "No findings to display".

Aucune anomalie SonarQube précise n'a été relevée
sur la capture consultée.

Points d'amélioration identifiés lors du développement :
1. Éviter les recherches répétées avec existsById()
   puis findById() lorsque cela n'est pas nécessaire.
2. Vérifier que les opérations PUT ne créent pas
   de nouvelles ressources lorsque l'ID est inexistant.

Ces deux points sont des observations techniques,
et non des anomalies confirmées par SonarQube.

## E. Questions et bilan

### 1. Quel est le rôle de @RestController ?

@RestController permet de créer un contrôleur REST
qui renvoie directement des données au client,
généralement au format JSON.

### 2. Quelle est la différence entre GET et POST ?

GET permet de consulter des données.
POST permet de créer une nouvelle ressource.

### 3. À quoi sert @RequestMapping ?

@RequestMapping définit le chemin de base
des requêtes traitées par un contrôleur.

### 4. Quel est le rôle de Swagger ?

Swagger permet de documenter les endpoints REST
et de les tester directement dans un navigateur.

### 5. Quelle est la différence entre 404 et 405 ?

404 signifie que la ressource demandée
est introuvable.

405 signifie que la méthode HTTP utilisée
n'est pas autorisée pour l'URL demandée.

### Conclusion

L'Atelier 5 a permis d'exposer les services
de l'application AutoLoc sous forme d'API REST,
de documenter les endpoints avec SpringDoc Swagger,
de tester plusieurs opérations avec Postman
et de gérer les erreurs HTTP.

La relation entre Contrat et Paiement a également
été testée lors de la création et de la consultation.
