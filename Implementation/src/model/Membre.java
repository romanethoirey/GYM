package model;

import exception.MauvaisFormatClientException;
import service.GymService;

public class Membre extends Client {

    private GymService.Status status;

    public Membre(String prenom, String nom, String email, GymService.Status status, GymService gymService, Clients clients) {
        super(prenom, nom, email, gymService, clients, "membre");
        this.status = status;
    }

    public GymService.Status getStatus() {
        return status;
    }

    public void setStringStatus(GymService.Status status) {this.status =status; }
}
