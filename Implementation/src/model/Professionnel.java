package model;

import service.GymService;

public class Professionnel extends Client {
	
    /**
     * Crée un nouveau professionnel
     * @param prenom Le prénom du pro
     * @param nom Le nom du pro
     * @param email L'email du pro
     * @param status Le statut du pro
     * @param gymService L'objet permettant d'avoir accès au méthodes de service
     * @param clients La liste de clients dont le pro fait partie
     */
    public Professionnel(String prenom, String nom, String email, GymService.Status status, GymService gymService, Clients clients ) {
        super(prenom, nom, email, gymService, clients, "professionnel",status);
    }
}
