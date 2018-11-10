package controller;

import exception.MauvaisFormatClientException;
import exception.MauvaisFormatMembreException;
import model.*;
import service.GymService;

import java.util.ArrayList;

import static service.GymService.Status.Valide;

public class GymController {

    GymService gymService = new GymService();
    Membres membres = new Membres();
    Professionnels professionnels = new Professionnels();
    Clients clients = new Clients(membres, professionnels);

    public void menuPrincipal() {

        while (true) {

            gymService.printMenuPrincipal();//(etat);

            switch (gymService.menuUserInput(gymService.NOMBRE_CAS_DUTILISATION)) {
                case 1:
                    nouveauCompte();
                    break;
                case 2:
                    gymService.printStatusClient(identificationClient());
                    break;
                case 3:
                    System.out.println("Accéder au gym.");
                    break;
                case 4:
                    System.out.println("Inscrire un Membre à une séance de service.");
                    break;
                case 5:
                    System.out.println(" Consulter les inscriptions aux séances de service (pour le Professionnel)");
                    break;
                case 6:
                    System.out.println("Créer une séance de service.");
                    break;
                case 7:
                    System.out.println("Consulter la liste des séances de service (pour le Membre).");
                    break;
                case 8:
                    System.out.println(" Confirmer la présence du Membre à une séance de service.");
                    break;
                default:
                    gymService.printEntreeErronee();
                    break;
            }
        }
    }

    public void nouveauCompte(){
        String[] informationsPersonnelles = informationsPersonnelles();
        gymService.printTypeClient();//(etat);

        switch (gymService.menuUserInput(2)){//paiement seulement si client
            case 1://Membre
                if(gymService.paiementInput().equals("y")){
                        Membre membre = new Membre(
                                informationsPersonnelles[0],//Prenom
                                informationsPersonnelles[1],//Nom
                                informationsPersonnelles[2],//Email
                                GymService.Status.Valide,//status
                                gymService,
                                clients);

                    membres.addListeMembres(membre);

                    gymService.printOperationComplete();
                }else{
                    gymService.printOperationAnnule();
                }
                break;
            case 2://Professionnel
                    Professionnel professionnel = new Professionnel(
                            informationsPersonnelles[0],//Prenom
                            informationsPersonnelles[1],//Nom
                            informationsPersonnelles[2],//Email
                            gymService,
                            clients);

                professionnels.addListeProfessionnels(professionnel);

                gymService.printOperationComplete();
                break;
        }
    }

    public GymService.Status identificationClient(){

        String inputsa = gymService.informationPersonnellesInput("numero");

        Long numeroClient = Long.parseLong(inputsa);

        Client client = clients
                .getListeClients()
                .stream()
                .filter(clt->clt.getNumeroClient().equals(numeroClient))
                .findAny()
                .orElse(null);

        if(client != null){
            if(client.getType().equals("membre")){
                return ((Membre)client).getStatus();
            }else{
                return GymService.Status.Valide;
            }
        }else {
            return GymService.Status.Inexistant;
        }
    }

    private String[] informationsPersonnelles(){
        return new String[]{
                gymService.informationPersonnellesInput("prenom"),
                gymService.informationPersonnellesInput("nom"),
                gymService.informationPersonnellesInput("email")};
    }
}
