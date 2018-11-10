package controller;

import exception.MauvaisFormatClientException;
import exception.MauvaisFormatMembreException;
import exception.MauvaisFormatSeanceException;
import model.*;
import service.GymService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static service.GymService.Status.Valide;

public class GymController {

    GymService gymService = new GymService();
    Membres membres = new Membres();
    Professionnels professionnels = new Professionnels();
    Seances seances = new Seances();
    Clients clients = new Clients(membres, professionnels);

    public void menuPrincipal() {

        while (true) {

            gymService.printMenuPrincipal();//(etat);

            switch (gymService.menuUserInput(gymService.NOMBRE_CAS_DUTILISATION)) {
                case 1:// Creation d'un client
                    nouveauCompte();
                    System.out.println("\n\n");
                    break;
                case 2:// Identification du client
                    gymService.printStatusClient(identificationClient());
                    System.out.println("\n\n");
                    break;
                case 3:// Accéder au gym
                    GymService.Status status  = identificationClient();
                    gymService.printStatusClient(status);
                    if(status.equals(GymService.Status.Valide)){
                        gymService.printOuvertureTourniquet();
                    }
                    System.out.println("\n\n");
                    break;
                case 4://Inscrire un Membre à une séance de service
                    System.out.println("Inscrire un Membre à une séance de service.");
                    break;
                case 5://Consulter les inscriptions aux séances de service (pour le Professionnel)
                    System.out.println(" Consulter les inscriptions aux séances de service (pour le Professionnel)");
                    break;
                case 6://Créer une séance de service
                    System.out.println("Créer une séance de service.");
                    break;
                case 7://Consulter la liste des séances de service (pour le Membre)
                    List<Seance> listeSeaces = seances.getListeSeances();
                    if(listeSeaces.isEmpty()){gymService.printAucuneSeances();}else{
                        listeSeaces.stream().forEach(seance -> System.out.println("Titre : "+seance.getTitre()+" Prix : "+seance.getFrais()));
                    }
                    System.out.println("\n\n");
                    break;
                case 8://Confirmer la présence du Membre à une séance de service
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
                gymService.printPaiementInput();
                if(gymService.yesNoInput().equals("y")){
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

        Client client = clients.getClient(numeroClient);

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

    public void initialisation(){
        gymService.printMockData();

        if(gymService.yesNoInput().equals("y")){
            //Deux membres, un professionnel
            membres.addListeMembres(new Membre("Marc-Antoine", "Dufresne Gagnon", "marc-antoine.dufresne.gagnon@umontreal.ca", GymService.Status.Valide, gymService, clients));
            membres.addListeMembres(new Membre("Maud", "Moerel-Martini", "maud.moerel-martini@umontreal.ca", GymService.Status.Suspendu, gymService, clients));
            professionnels.addListeProfessionnels(new Professionnel("Maxime", "Daigle", "maxime.daigle@umontreal.ca", gymService, clients));
            //Deux seances
            try {
                seances.addListeSeances(new Seance(
                        "Yoga",
                        "01-01-2018",
                        "31-12-2018",
                        "12:30",
                        new ArrayList<Boolean>(Arrays.asList(true,false,true,false,false,true,false)),
                        "25",
                        ((Professionnel) professionnels.getListeProfessionnels().toArray()[0]).getNumeroProfessionnel(),
                        "51.35",
                        "",
                        gymService,
                        seances));
                seances.addListeSeances(new Seance(
                        "Karate",
                        "01-03-2018",
                        "31-07-2018",
                        "08:30",
                        new ArrayList<Boolean>(Arrays.asList(true,false,false,true,false,false,true)),
                        "5",
                        ((Professionnel) professionnels.getListeProfessionnels().toArray()[0]).getNumeroProfessionnel(),
                        "18.11",
                        "",
                        gymService,
                        seances));
            }catch (MauvaisFormatSeanceException e){}
        }
    }
}
