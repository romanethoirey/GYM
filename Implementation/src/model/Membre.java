package model;

import exception.MauvaisFormatClientException;
import service.GymService;

public class Membre extends Client {

    public GymService.status status;

    public Membre(String prenom, String nom, String email, GymService.status status, GymService gymService, Clients clients) {
        super(prenom, nom, email, gymService, clients);
        this.status = status;
    }

    public GymService.status getStatus() {
        return status;
    }

    public void setStringStatus(GymService.status status) {this.status =status; }
}
