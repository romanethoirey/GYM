package model;

import service.GymService;

public abstract class Client {//TODO informations personnelles

    private Long numero;// unique a chaque Client
    private String type;
    private String prenom;
    private String nom;
    private String email;

    public Client(String prenom, String nom, String email, GymService gymService, Clients clients, String type) {
        while(true){
            this.numero = gymService.randomLongLengthN(9);
            if(clients
                    .getListeClients()
                    .stream()
                    .filter(client->client.numero.equals(this.numero))
                    .count() == 0){ break;}//verification que le numero genere n'existe pas
        }
        this.type = type;
        this.prenom = prenom;
        this.nom = nom;
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public Long getNumeroClient() {
        return this.numero;
    }
}
