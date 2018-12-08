package model;

import service.GymService;

public class Membre extends Client {
	
    /**
     * @param prenom
     * @param nom
     * @param email
     * @param status
     * @param gymService
     * @param clients
     */
    public Membre(String prenom, String nom, String email, GymService.Status status, GymService gymService, Clients clients) {
        super(prenom, nom, email, gymService, clients, "membre", status);
    }
}
