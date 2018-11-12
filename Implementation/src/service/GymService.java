package service;

import model.Seance;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GymService {

    public final static Integer NOMBRE_CAS_DUTILISATION=6; //TODO sortir constantes, les mettre dans un fichier de constantes de systeme

    public enum Status{Suspendu, Valide, Inexistant, Expire}//Status possible d'un membre

    //////////INPUT//////////

    public Integer intUserInput(){
            Scanner sc = new Scanner(System.in);
            try{
                return sc.nextInt();
            }catch (InputMismatchException e){
                return -1;
            }
    }

    public String stringUserInput(){
        Scanner sc = new Scanner(System.in); // scanner pour userinput
        try{
            return sc.nextLine();
        }catch (InputMismatchException e){//TODO Faire le bon exception handling
            return "";
        }
    }

    public String informationPersonnellesInput(String attribut){
        while(true){//boucle jusqu'a entree valide
            printInformationPersonnellesInput(attribut);
            String input = stringUserInput();
            if( !input.isEmpty() || attribut.equals("commentaire")){return input;}//dans le range des cas d'utilisation
            printEntreeErronee();
        }
    }

    public String informationSeanceInput(String attribut){
        while(true){//boucle jusqu'a entree valide
            printSeanceInput(attribut);
            String input = stringUserInput();
            if( !input.isEmpty() || attribut.equals("commentaire")){return input;}//dans le range des cas d'utilisation
            printEntreeErronee();
        }
    }

    public Integer menuUserInput(Integer max){
        while(true){//boucle jusqu'a entree valide
            printSelectionMenu();
            Integer input = intUserInput();
            if( 0 < input && input <= max ){return input;}//dans le range des cas d'utilisation
            printEntreeErronee();
        }
    }

    public String yesNoInput(){
        while(true){//boucle jusqu'a entree valide
            String input = stringUserInput();
            if( input.equals("y") || input.equals("n") ){return input;}//dans le range des cas d'utilisation
            printEntreeErronee();
        }
    }

    //////////PRINT//////////

    public void printMenuPrincipal() {
        System.out.println("" +// TODO mettre le texte dans une fichier separe ?
                "1) Créer un nouveau compte au gym.\n" +
                "2) Accéder au gym.\n" +
                "3) Consulter les inscriptions aux séances de service (pour le Professionnel).\n" +
                "4) Créer une séance de service.\n" +
                "5) Consulter la liste des séances de service pour une possible inscription.\n" +
                "6) Confirmer la présence du Membre à une séance de service.");
    }

    public void printTypeClient() {
        System.out.println("" +// TODO mettre le texte dans une fichier separe ?
                "Le nouveau client est :\n"+
                "1) Un nouveau membre.\n" +
                "2) Un nouveau professionnel.\n");
    }

    public void printEntreeErronee(){
        System.out.println("\nEntree Erronee."); //
    }

    public void printMembreNonInscrit(){
        System.out.println("\nLe membre n'est pas inscrit a cette seance, acces refuse."); //
    }

    public void printOperationAnnule(){
        System.out.println("\nOperation Annule."); //
    }

    public void printOperationComplete(){
        System.out.println("\nOperation Complete."); //
    }

    public void printPaiementInput(){
        System.out.println("\nPaiement complete ? (y/n)");
    }

    public void printChoixInscription(){
        System.out.println("\nLe client veut-il s'inscrire ? (y/n)");
    }

    public void printMockData(){
        System.out.println("\nVoulez vous initialiser le logiciel en mode demo (creation de mock data) ? (y/n)");
    }

    public void printOuvertureTourniquet(){
        System.out.println("\nVeuillez ouvrir le tourniquet.");
    }

    public void printAucuneSeances(){
        System.out.println("\nAucunes seances.");
    }

    private void printSelectionMenu(){ System.out.println("\nVeuiller selectionner le numero d'une des options ci-dessus.");}

    private void printInformationPersonnellesInput(String attribut){System.out.println("\nVeuiller entrer le " + attribut + " du client.");}

    public void printNumeroNouceauClient(Long attribut){System.out.println("\nLe numero du nouveau client est :  " + attribut+ ".");}

    public void printInfosSeance(Seance seance){System.out.println("Titre : "+seance.getTitre()+" Prix : "+seance.getFrais() + "$ Code de seance : "+seance.getCode());}

    public void printSeanceInput(String attribut){System.out.println("\nVeuiller entrer le " + attribut + " de la seance.");}

    public void printFraisSeance(String attribut){System.out.println("\nLe prix de la Seance est de " + attribut + " $.");}

    public void printStatusClient(Status status){System.out.println("\nLe client avec le numero entre est " + status.toString() );}

    public void printInfosInscriptionsSeance(Seance seance){
        System.out.println("Titre : "+seance.getTitre());
        seance.getListeInscriptionsSeance().stream().forEach(inscription ->System.out.println(inscription.toString()));
    }

    //////////AUTRES//////////

    public Long randomLongLengthN(int n){
        return (long)(Math.random() * (long) Math.pow(10, n - 1) * 9) + (long) Math.pow(10, n - 1);
    }

    public ArrayList<Boolean> boolStringToArray(String string){
        ArrayList<Boolean> boolArray = new ArrayList<Boolean>();

        for(int i=0; i<string.length();i++){
            if(string.charAt(i) == '0'){
                boolArray.add(false);
            }else if(string.charAt(i) == '1'){
                boolArray.add(true);
            }
        }
        return boolArray;
    }

}


