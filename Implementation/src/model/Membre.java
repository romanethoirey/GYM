package model;

import service.GymService;

public class Membre extends Client {
	
    /**
     * Construit une instance de membre
     * @param prenom Le prénom du membre
     * @param nom Le nom du Membre
     * @param email L'email du Membre
     * @param status Le statut du Membre
     * @param gymService L'objet permettant d'utiliser les méthodes de GymService
     * @param clients la liste de clients dont le membre fait partie
     */
    public Membre(String prenom, String nom, String email, GymService.Status status, GymService gymService, Clients clients) {
        super(prenom, nom, email, gymService, clients, "membre", status);
    }
}
