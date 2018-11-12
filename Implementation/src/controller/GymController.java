package controller;

import exception.MauvaisFormatInscriptionSeanceException;
import exception.MauvaisFormatPresenceSeanceException;
import exception.MauvaisFormatSeanceException;
import exception.TropParticipantsException;
import model.*;
import service.GymService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GymController {

    GymService gymService = new GymService();
    Membres membres = new Membres();
    Professionnels professionnels = new Professionnels();
    Seances seances = new Seances();
    Clients clients = new Clients(membres, professionnels);

    public void initialisation(){
        gymService.printMockData();

        if(gymService.yesNoInput().equals("y")){
            //Deux membres, un professionnel
            membres.addListeMembres(new Membre("Marc-Antoine", "Dufresne Gagnon", "marc-antoine.dufresne.gagnon@umontreal.ca", GymService.Status.Valide, gymService, clients));
            membres.addListeMembres(new Membre("Maud", "Moerel-Martini", "maud.moerel-martini@umontreal.ca", GymService.Status.Suspendu, gymService, clients));
            professionnels.addListeProfessionnels(new Professionnel("Maxime", "Daigle", "maxime.daigle@umontreal.ca",GymService.Status.Valide,gymService, clients));
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

    public void menuPrincipal() {

        while (true) {

            gymService.printMenuPrincipal();//(etat);
            GymService.Status status;
            ArrayList identification;

            switch (gymService.menuUserInput(gymService.NOMBRE_CAS_DUTILISATION)) {
                case 1:// Creation d'un client
                    nouveauCompte();
                    System.out.println("\n\n");
                    break;
                case 2:// Accéder au gym
                    status  = (GymService.Status)identificationClient().get(0);
                    gymService.printStatusClient(status);
                    if(status.equals(GymService.Status.Valide)){
                        gymService.printOuvertureTourniquet();
                    }
                    System.out.println("\n\n");
                    break;
                case 3://Consulter les inscriptions aux séances de service (pour le Professionnel)//TODO check pour juste Professionnels
                    identification  = identificationClient();
                    status  = (GymService.Status)identification.get(0);
                    gymService.printStatusClient(status);
                    if(status.equals(GymService.Status.Valide)){
                        consultationInscriptionsSeances((Long)identification.get(1));
                    }
                    System.out.println("\n\n");
                    break;
                case 4://Créer une séance de service//TODO check pour juste professionnels
                    identification  = identificationClient();
                    status  = (GymService.Status)identification.get(0);
                    gymService.printStatusClient(status);
                    if(status.equals(GymService.Status.Valide)){
                        nouvelleSeance((Long)identification.get(1));
                    }
                    System.out.println("\n\n");
                    break;
                case 5://Consulter la liste des séances de service pour une possible inscription//TODO check pour juste membres
                    consultationListeSeances();
                    gymService.printChoixInscription();
                    if(gymService.yesNoInput().equals("y")){
                        identification  = identificationClient();
                        gymService.printStatusClient((GymService.Status)identification.get(0));
                        if((identification.get(0)).equals(GymService.Status.Valide)) {
                            inscriptionSeance((Long)identification.get(1));
                        }
                    }
                    System.out.println("\n\n");
                    break;
                case 6://Confirmer la présence du Membre à une séance de service// TODO check pour juste membres
                    identification  = identificationClient();
                    status  = (GymService.Status)identification.get(0);
                    gymService.printStatusClient(status);
                    if(status.equals(GymService.Status.Valide)){
                        confirmationPresenceSeance(Long.parseLong(gymService.informationSeanceInput("code")),(Long)identification.get(1));
                    }
                    System.out.println("\n\n");
                    break;
                default:
                    gymService.printEntreeErronee();
                    break;
            }
        }
    }

    private void nouveauCompte(){
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
                            GymService.Status.Valide,
                            gymService,
                            clients);

                professionnels.addListeProfessionnels(professionnel);

                gymService.printOperationComplete();
                break;
        }
    }

    private void nouvelleSeance(Long numeroProfessionnel){
        try{
            seances.addListeSeances( new Seance(
                    gymService.informationSeanceInput("titre"),
                    gymService.informationSeanceInput("date de debut (JJ-MM-AAAA)"),
                    gymService.informationSeanceInput("date de fin (JJ-MM-AAAA)"),
                    gymService.informationSeanceInput("heure (HH:mm)"),
                    gymService.boolStringToArray(gymService.informationSeanceInput("recurrence hebdomadaire (Dimanche:oui, Lundi:non, Mardi:oui, Mercredi:non, Jeudi:oui, Vendredi:non, Samedi:non) = 1010100")),
                    gymService.informationSeanceInput("capacite"),
                    numeroProfessionnel,
                    gymService.informationSeanceInput("frais (max 100.00)"),
                    gymService.informationSeanceInput("commentaire"),
                    gymService,
                    seances
            ));

            gymService.printOperationComplete();

        }catch (MauvaisFormatSeanceException e){
            gymService.printOperationAnnule();
        }

    }

    private void inscriptionSeance(Long numeroClient) {
        String code = gymService.informationSeanceInput("code");
        gymService.printFraisSeance(seances.getSceance(Long.parseLong(code)).getFrais());
        gymService.printPaiementInput();
        if(gymService.yesNoInput().equals("y")){
            try{
                seances.getSceance(Long.parseLong(code)).addInscription(new InscriptionSeance(
                        gymService.informationSeanceInput("date du rendez-vous (jj-mm-aaaa)"),
                        seances.getSceance(Long.parseLong(code)).getNumeroProfessionnel(),
                        numeroClient,
                        Long.parseLong(code),
                        gymService.informationSeanceInput("commentaire")
                ));

                gymService.printOperationComplete();

            }catch (TropParticipantsException|MauvaisFormatInscriptionSeanceException e){
                gymService.printOperationAnnule();
            }
        }else{
            gymService.printOperationAnnule();
        }
    }

    private void confirmationPresenceSeance(Long code, Long numeroMembre){
        Seance seance = seances.getSceance(code);
        if(!seance.equals(null)){
            if(seance.membreInscrit(numeroMembre)){
                try {
                    seance.addPresence(new PresenceSeance(
                            seance.getNumeroProfessionnel(),
                            numeroMembre,
                            code,
                            gymService.informationSeanceInput("commentaire")
                    ));

                    gymService.printOperationComplete();

                }catch(MauvaisFormatPresenceSeanceException e){
                    gymService.printOperationAnnule();
                }
            }else{
                gymService.printMembreNonInscrit();
            }
        }else{
            gymService.printEntreeErronee();
        }
    }

    private ArrayList identificationClient(){

        String inputsa = gymService.informationPersonnellesInput("numero");

        Long numeroClient = Long.parseLong(inputsa);

        Client client = clients.getClient(numeroClient);

        if(client != null){
            if(client.getType().equals("membre")){
                return new ArrayList(Arrays.asList(client.getStatus(),numeroClient));
            }else{
                return new ArrayList(Arrays.asList(GymService.Status.Valide,numeroClient));
            }
        }else {
            return new ArrayList(Arrays.asList(GymService.Status.Inexistant,numeroClient));
        }
    }

    private String[] informationsPersonnelles(){
        return new String[]{
                gymService.informationPersonnellesInput("prenom"),
                gymService.informationPersonnellesInput("nom"),
                gymService.informationPersonnellesInput("email")};
    }

    private void consultationListeSeances(){
        List<Seance> listeSeaces = seances.getListeSeances();
        if(listeSeaces.isEmpty()){gymService.printAucuneSeances();}else{
            listeSeaces.stream().forEach(seance -> gymService.printInfosSeance(seance));
        }
    }

    private void consultationInscriptionsSeances(Long numeroProfessionnel){
        List<Seance> listeSeaces = seances.getListeSeances()
                .stream()//filtre pour les seances de ce professionnel
                .filter(seance->seance.getNumeroProfessionnel().equals(numeroProfessionnel))
                .collect(Collectors.toList());
        if(listeSeaces.isEmpty()){gymService.printAucuneSeances();}else{
            listeSeaces.stream().forEach(seance -> gymService.printInfosInscriptionsSeance(seance));
        }
    }


}
