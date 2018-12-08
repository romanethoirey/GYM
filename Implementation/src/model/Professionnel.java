package model;

import service.GymService;

public class Professionnel extends Client {
	
    /**
     * @param prenom
     * @param nom
     * @param email
     * @param status
     * @param gymService
     * @param clients
     */
    public Professionnel(String prenom, String nom, String email, GymService.Status status, GymService gymService, Clients clients ) {
        super(prenom, nom, email, gymService, clients, "professionnel",status);
    }
}
