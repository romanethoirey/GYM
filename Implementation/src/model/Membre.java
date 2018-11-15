package model;

import service.GymService;

public class Membre extends Client {
    public Membre(String prenom, String nom, String email, GymService.Status status, GymService gymService, Clients clients) {
        super(prenom, nom, email, gymService, clients, "membre", status);
    }
}
