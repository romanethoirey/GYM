package model;

import service.GymService;

public class Professionnel extends Client {
    public Professionnel(String prenom, String nom, String email, GymService.Status status, GymService gymService, Clients clients ) {
        super(prenom, nom, email, gymService, clients, "professionnel",status);
    }
}
