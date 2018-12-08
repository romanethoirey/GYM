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
            professionnels.addListeProfessionnels(new Professionnel("Michaelis", "Famelis", "CONFIDENTIEL",GymService.Status.Valide,gymService, clients));
            professionnels.addListeProfessionnels(new Professionnel("Thomas", "Schweizer", "CONFIDENTIEL",GymService.Status.Valide,gymService, clients));
            //Deux seances
            try {
                seances.addListeSeances(new Seance(
                        "Yoga", "101",
                        "01-01-2018",
                        "31-12-2018",
                        "12:30",
                        new ArrayList<Boolean>(Arrays.asList(true,false,true,false,false,true,false)),
                        "25",
                        ((Professionnel) professionnels.getListeProfessionnels().toArray()[0]).getNumeroClient(),
                        "51.35",
                        "",
                        gymService,
                        seances));
                seances.addListeSeances(new Seance(
                        "Karate", "789",
                        "01-03-2018",
                        "31-07-2018",
                        "08:30",
                        new ArrayList<Boolean>(Arrays.asList(true,false,false,true,false,false,true)),
                        "5",
                        ((Professionnel) professionnels.getListeProfessionnels().toArray()[0]).getNumeroClient(),
                        "18.11",
                        "",
                        gymService,
                        seances));
                seances.addListeSeances(new Seance(
                        "Meditation","456",
                        "01-01-2018",
                        "31-12-2018",
                        "12:30",
                        new ArrayList<Boolean>(Arrays.asList(true,false,true,false,false,true,false)),
                        "25",
                        ((Professionnel) professionnels.getListeProfessionnels().toArray()[1]).getNumeroClient(),
                        "11.35",
                        "",
                        gymService,
                        seances));
                seances.addListeSeances(new Seance(
                        "Flechettes","395",
                        "01-03-2018",
                        "31-07-2018",
                        "08:30",
                        new ArrayList<Boolean>(Arrays.asList(true,false,false,true,false,false,true)),
                        "5",
                        ((Professionnel) professionnels.getListeProfessionnels().toArray()[2]).getNumeroClient(),
                        "65.11",
                        "",
                        gymService,
                        seances));
                seances.addListeSeances(new Seance(
                        "Jogging", "734",
                        "01-01-2018",
                        "31-12-2018",
                        "12:30",
                        new ArrayList<Boolean>(Arrays.asList(true,false,true,false,false,true,false)),
                        "25",
                        ((Professionnel) professionnels.getListeProfessionnels().toArray()[2]).getNumeroClient(),
                        "45.35",
                        "",
                        gymService,
                        seances));
                seances.addListeSeances(new Seance(
                        "Step Dance", "295",
                        "01-03-2018",
                        "31-07-2018",
                        "08:30",
                        new ArrayList<Boolean>(Arrays.asList(true,false,false,true,false,false,true)),
                        "5",
                        ((Professionnel) professionnels.getListeProfessionnels().toArray()[2]).getNumeroClient(),
                        "76.11",
                        "",
                        gymService,
                        seances));
                seances.addListeSeances(new Seance(
                        "Football","649",
                        "01-01-2018",
                        "31-12-2018",
                        "12:30",
                        new ArrayList<Boolean>(Arrays.asList(true,false,true,false,false,true,false)),
                        "25",
                        ((Professionnel) professionnels.getListeProfessionnels().toArray()[1]).getNumeroClient(),
                        "5.35",
                        "",
                        gymService,
                        seances));
                seances.addListeSeances(new Seance(
                        "Soccer","190",
                        "01-12-2018",
                        "31-03-2019",
                        "08:30",
                        new ArrayList<Boolean>(Arrays.asList(true,false,false,true,false,false,true)),
                        "5",
                        ((Professionnel) professionnels.getListeProfessionnels().toArray()[2]).getNumeroClient(),
                        "8.11",
                        "",
                        gymService,
                        seances));
            }catch (MauvaisFormatSeanceException e){}

            System.out.println("\n\n");
        }
    }

    public void menuPrincipal() {
    	//
        while (true) {

            gymService.printMenuPrincipal();//(etat);

            switch (gymService.menuUserInput(GymService.NOMBRE_CAS_DUTILISATION)) {
                case 1:// Creation d'un client
                    nouveauCompte();
                    System.out.println("\n\n");
                    break;
                    
                case 2:// Connexion
                	int choix=choixMenu();
                	if(choix==1) {
                		Membre m = (Membre) identificationClient();
                		MenuMembre(m);
                	}
                	else {
                		if(choix ==2) {
                			Professionnel p  = (Professionnel)identificationClient();
                			menuProfessionel(p);
                		}
                		else {
                			gymService.printEntreeErronee();
                		}
                	}
                	
                    break;
                    
                case 3://Gestion
                    menuGestion();
                    break;
                    
                case 4://Quitter le logiciel
                    System.exit(0);
                    break;
                default:
                    gymService.printEntreeErronee();
                    break;
            }
            gymService.printContinuerOuFin();

            if(gymService.yesNoInput().equals("n")){
                System.exit(0);
            }

        }
    }
    private void MenuMembre(Membre m) {
    	
    		gymService.printNomCodeClient(m);
    		gymService.printMenuMembre();//(etat);
        	GymService.Status status = m.getStatus();
        	Long numm = m.getNumeroClient();
        	
	    	switch (gymService.menuUserInput(GymService.NOMBRE_CAS_DUTILISATION)) {
	    		case 1:
	                gymService.printStatusClient(status);
	                if(status.equals(GymService.Status.Valide)){
	                    gymService.printOuvertureTourniquet();
	                }
	                System.out.println("\n\n");
	                gymService.printDeconnexion();
	                String answer=gymService.yesNoInput();
	    	    	if(answer.equals("y")){
	                    menuPrincipal();
	                }
	    			else {
	    				if(answer.equals("n"))
	    					MenuMembre(m);
	    				else
	    					gymService.printEntreeErronee();
	    			}
	    			break;
	    			
	    		case 2:
	    			paiementCompte(m.getNumeroClient());
	    			System.out.println("\n\n");
	    			break;
	    			
	    		case 3:
	    			consultationListeSeances();
                    gymService.printChoixInscription();
                    if(gymService.yesNoInput().equals("y")){
                        gymService.printStatusClient(status);
                        if((status).equals(GymService.Status.Valide)) {
                            inscriptionSeance(numm);
                        }
                    }
                    System.out.println("\n\n");
                    break;
                  
	    		case 4:
	    			
	    			break;
	    		
	    		case 5:
	    			gymService.printDeconnexion();
	    	    	if(gymService.yesNoInput().equals("y")){
	                    menuPrincipal();
	                }
	    			else {
	    				if(gymService.yesNoInput().equals("n"))
	    					MenuMembre(m);
	    				else
	    					gymService.printEntreeErronee();
	    			}
	    			break;
	    			
	    		default:
                    gymService.printEntreeErronee();
                    break;
            }
	    	
    }
    
    public void menuProfessionel(Professionnel p) {
    	
    	while(true) {
    		gymService.printNomCodeClient(p);
    		gymService.printMenuProfessionnel();//(etat);
    		GymService.Status status;
    		status  = (GymService.Status)p.getStatus();
    		Long nump=p.getNumeroClient();
	    	switch (gymService.menuUserInput(GymService.NOMBRE_CAS_DUTILISATION)) {
		    	case 1:
	                gymService.printStatusClient(status);
	                if(status.equals(GymService.Status.Valide) ){
	                    consultationInscriptionsSeances(nump);
	                }
	                System.out.println("\n\n");
	                break;
	                
		    	case 2:
	                gymService.printStatusClient(status);
	                if(status.equals(GymService.Status.Valide)){
	                    nouvelleSeance(nump);
	                }
	                System.out.println("\n\n");
	                break;
	            
		    	case 3:
                    gymService.printStatusClient(status);
                    if(status.equals(GymService.Status.Valide)){
                        consultationListeSeances();
                        confirmationPresenceSeance(Long.parseLong(gymService.informationSeanceInput("code")),nump);
                    }
                    System.out.println("\n\n");
                    break;
                
		    	case 4:
		    		gymService.printDeconnexion();
	    	    	if(gymService.yesNoInput().equals("y")){
	                    menuPrincipal();
	                }
	    			else {
	    				if(gymService.yesNoInput().equals("n"))
	    					menuProfessionel(p);
	    				else
	    					gymService.printEntreeErronee();
	    			}
	    			break;
	    		
		    	default:
                    gymService.printEntreeErronee();
                    break;
	    	}
    	}
    }
    
    public void menuGestion() {
    	 while (true) {

             gymService.printMenuGestion();//(etat);
             GymService.Status status;
             ArrayList identification;

             switch (gymService.menuUserInput(GymService.NOMBRE_CAS_DUTILISATION)) {
	             case 1:// Créer le fichier TEF 
	                 creationFichierTEF();
	                 System.out.println("\n\n");
	                 break;
	                 
	             case 2://Créer le rapport de synthèse
	                 creationRapportSynthese();
	                 System.out.println("\n\n");
	                 break;
	                 
	             case 3:
	            	 gymService.printDeconnexion();
		    	    	if(gymService.yesNoInput().equals("y")){
		                    menuPrincipal();
		                }
		    			else {
		    				if(gymService.yesNoInput().equals("n"))
		    					menuGestion();
		    				else
		    					gymService.printEntreeErronee();
		    			}
	            	 break;
	            	
	             default:
	                    gymService.printEntreeErronee();
	                    break;	 
             }
    	 }    
    }
    
    //retourne 1 ou 2 en fonction de si c'est un membre ou un pro
    private int choixMenu() {
    	gymService.printTypeClient();
    	switch (gymService.menuUserInput(GymService.NOMBRE_CAS_DUTILISATION)) {
    		case 1:
    			return 1;
    		case 2:
    			return 2;
    		default:
                gymService.printEntreeErronee();
                return 0;	
    			
    	}
    }
    
    
    private void paiementCompte(Long numeroClient) {
    	Membre membre = membres.getMembre(numeroClient);
    	gymService.printPaiementInput();
    	if(gymService.yesNoInput().equals("y"))
        	membre.setStatus(GymService.Status.Valide);
        gymService.printOperationComplete();
    	
    }
    
    private void nouveauCompte(){
        String[] informationsPersonnelles = informationsPersonnelles();
        gymService.printTypeClient();//(etat);

        switch (gymService.menuUserInput(2)){//paiement seulement si client
            case 1://Membre
                Membre membre = new Membre(
                        informationsPersonnelles[0],//Prenom
                        informationsPersonnelles[1],//Nom
                        
                        informationsPersonnelles[2],//Email
                        GymService.Status.Suspendu,//status
                        gymService,
                        clients);
                membres.addListeMembres(membre);
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
                    gymService.informationSeanceInput("code de la séance de 3 chiffres"),
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
                        Integer.parseInt(code),
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
        gymService.printInfosSeance(seance);

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

    private Client identificationClient(){

        String inputsa = gymService.informationPersonnellesInput("numero");
        Long numeroClient = Long.parseLong(inputsa);
        return clients.getClient(numeroClient);
       
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

    private void creationFichierTEF(){
        List<String> contenu = new ArrayList<>();
        contenu.add(GymService.TITRE_FICHIER_TEF + "\n\n");

        professionnels
                .getListeProfessionnels()
                .stream()
                .forEach(professionnel -> {
                    contenu.add("Nom du professionnel: " + professionnel.getNom() + ", " + professionnel.getPrenom());
                    contenu.add("Numero d'identification : " + professionnel.getNumeroClient());
                    contenu.add("Responsables des services suivants : ");
                    seances
                            .getListeSeances()
                            .stream()
                            .filter(seance->seance.getNumeroProfessionnel().equals(professionnel.getNumeroClient()))
                            .forEach(seance -> {
                        contenu.add("  Titre de la seance : " + seance.getTitre() + "  |  Code de la seance : " + seance.getCode() + "  |  Nombre de participants : " +seance.getListeInscriptionsSeance().size());
                    });
                    contenu.add("\n");
                });

        gymService.creationFichierTexte(GymService.TITRE_FICHIER_TEF, contenu);
        gymService.printOperationComplete();
    }

    private void creationRapportSynthese(){
        List<String> contenu = new ArrayList<>();
        Double totalDesFrais = 0.0;
        Integer totalDesSeances = 0;

        contenu.add(GymService.TITRE_RAPPORT_SYNTHESE + "\n\n");

        List<Professionnel> listeProfessionnels = professionnels.getListeProfessionnels();
        List<Seance> listeSeances = seances.getListeSeances();

        for (int i = 0; i < listeProfessionnels.size(); i++) {
            Double totalDesFraisProfessionnel = 0.0;
            Integer totalDesSeancesProfessionnel = 0;

            contenu.add("Nom du professionnel: " + listeProfessionnels.get(i).getNom()  + ", " + listeProfessionnels.get(i).getPrenom());
            contenu.add("Numero d'identification : " + listeProfessionnels.get(i).getNumeroClient());

            for (int j = 0; j < listeSeances.size(); j++) {
                if(listeSeances.get(j).getNumeroProfessionnel().equals(listeProfessionnels.get(i).getNumeroClient())){
                    totalDesSeancesProfessionnel++;
                    totalDesFraisProfessionnel += listeSeances.get(j).getFrais();
                }
            }

            totalDesFrais += totalDesFraisProfessionnel;
            totalDesSeances += totalDesSeancesProfessionnel;

            contenu.add("Nombre de services fournis : " + totalDesSeancesProfessionnel);
            contenu.add("Total des frais de services : " + gymService.arrondirDoubleDeuxDecimals(totalDesFraisProfessionnel));
            contenu.add("\n");
        }

        contenu.add("\n");
        contenu.add("Nombre total de professionnels : " + professionnels.getListeProfessionnels().size());
        contenu.add("Nombre total de seances de service : " + totalDesSeances);
        contenu.add("Total des Frais : " + gymService.arrondirDoubleDeuxDecimals(totalDesFrais));

        gymService.creationFichierTexte(GymService.TITRE_RAPPORT_SYNTHESE,  contenu);
        gymService.printOperationComplete();
    }
    /**
     * @return
     */
    public Seances GetSeance() {
    	return this.seances;
    }
}
