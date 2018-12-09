package model;

import java.time.LocalDate;

import service.GymService;

public abstract class Client {

    private Long numero;// unique a chaque Client
    private String type;
    private String prenom;
    private String nom;
    private String email;
    private GymService.Status status;

    /**
     * Construit une nouvelle instance de client
     * @param prenom Le prénom du Client
     * @param nom le nom du Client
     * @param email l'email du Client
     * @param gymService l'objet pour accéder aux méthodes de services
     * @param clients la liste des clients
     * @param type le type de client
     * @param status le statut : valide, suspendu, Inexistant
     */
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

    /**
     * retourne le type de client
     * @return le type de client
     */
    public String getType() {
        return type;
    }

    /**
     * @return le numéro du Client
     */
    public Long getNumeroClient() {
        return this.numero;
    }

    /**
     * @return le statut actuel du Client
     */
    public GymService.Status getStatus() {
        return status;
    }

    /**
     * Modifie le statut actuel du client
     * @param status le statut actuel du client
     */
    public void setStatus(GymService.Status status) {this.status =status; }

    /**
     * @return le prénom du client
     */
    public String getPrenom() {
        return prenom;
    }
    /**
     * @return l'email du client
     */
    public String getMail() {
    	return this.email;
    }
   

    /**
     * @return le nom du client
     */
    public String getNom() {
        return nom;
    }
    
}
