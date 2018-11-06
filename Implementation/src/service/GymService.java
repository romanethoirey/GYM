package service;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GymService {

    public Integer intUserInput(){
            Scanner sc = new Scanner(System.in);
            try{
                return sc.nextInt();
            }catch (InputMismatchException e){
                return -1;
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

//    public String StringUserInput(){
//        Scanner sc = new Scanner(System.in); // scanner pour userinput
//        printSelectionMenu();
//        try{
//            return sc.nextLine();
//        }catch (InputMismatchException e){
//            return "";
//        }
//    }

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

    public void printEntreeErronee(){
        System.out.println("\nEntree Erronee"); // TODO Loop pour essayer une autre entree
    }

    private void printSelectionMenu(){
        System.out.println("Veuiller selectionner le numero d'une des options ci-dessus"); // TODO Loop pour essayer une autre entree
    }
}


