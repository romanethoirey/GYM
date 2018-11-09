package model;

import service.GymService;

public abstract class Client {//TODO informations personnelles

    private Long numero;// unique a chaque Client
    private String prenom;
    private String nom;
    private String email;

    public Client(String prenom, String nom, String email, GymService gymService, Clients clients) {
        while(true){
            this.numero = gymService.randomLongLengthN(9);
            if(clients.getListeClients().stream().filter(client->client.numero.equals(this.numero)).count() == 0){ break;}//verification que le numero genere n'existe pas
        }
        this.prenom = prenom;
        this.nom = nom;
        this.email = email;
    }

    public Long getNumeroClient() {
        return this.numero;
    }
}
