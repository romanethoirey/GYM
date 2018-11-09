package service;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GymService {

    public final static Integer NOMBRE_CAS_DUTILISATION=8; //TODO sortir constantes, les mettre dans un fichier de constantes de systeme

    public enum status{Suspendu, Valide}//Status possible d'un membre

    public Integer intUserInput(){
            Scanner sc = new Scanner(System.in);
            try{
                return sc.nextInt();
            }catch (InputMismatchException e){
                return -1;
            }
    }

    public Long randomLongLengthN(int n){
        return (long)(Math.random() * (long) Math.pow(10, n - 1) * 9) + (long) Math.pow(10, n - 1);
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
            if( !input.isEmpty()){return input;}//dans le range des cas d'utilisation
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

    public String paiementInput(){
        while(true){//boucle jusqu'a entree valide
            printPaiementInput();
            String input = stringUserInput();
            if( input.equals("y") || input.equals("n") ){return input;}//dans le range des cas d'utilisation
            printEntreeErronee();
        }
    }

    public void printMenuPrincipal() {
        System.out.println("" +// TODO mettre le texte dans une fichier separe ?
                "1) Créer un nouveau compte au gym.\n" +
                "2) Identification du Client.\n" +
                "3) Accéder au gym.\n" +
                "4) Inscrire un Membre à une séance de service.\n" +
                "5) Consulter les inscriptions aux séances de service (pour le Professionnel).\n" +
                "6) Créer une séance de service.\n" +
                "7) Consulter la liste des séances de service (pour le Membre).\n" +
                "8) Confirmer la présence du Membre à une séance de service.");
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

    public void printOperationAnnule(){
        System.out.println("\nOperation Annule."); //
    }

    public void printOperationComplete(){
        System.out.println("\nOperation Complete."); //
    }

    public void printPaiementInput(){
        System.out.println("\nPaiement complete ? (y/n)");
    }

    private void printSelectionMenu(){ System.out.println("Veuiller selectionner le numero d'une des options ci-dessus.");}

    private void printInformationPersonnellesInput(String attribut){System.out.println("Veuiller inscrire le " + attribut + " du nouveau client.");}

}


