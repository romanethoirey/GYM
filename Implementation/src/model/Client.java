package model;

import java.time.LocalDate;

import service.GymService;

public abstract class Client {//TODO informations personnelles

    private Long numero;// unique a chaque Client
    private String type;
    private String prenom;
    private String nom;
    private String email;
    private GymService.Status status;

    public Client(String prenom, String nom, String email, GymService gymService, Clients clients, String type, GymService.Status status) {
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
        this.status = status;

        gymService.printNumeroNouceauClient(this.getNumeroClient());
    }

    public String getType() {
        return type;
    }

    public Long getNumeroClient() {
        return this.numero;
    }

    public GymService.Status getStatus() {
        return status;
    }

    public void setStatus(GymService.Status status) {this.status =status; }

    public String getPrenom() {
        return prenom;
    }
    public String getMail() {
    	return this.email;
    }
   

    public String getNom() {
        return nom;
    }
    
}
