package controller;

import service.GymService;

public class GymController {

    GymService gymService = new GymService();
    final Integer NOMBRE_CAS_DUTILISATION=8; //TODO sortir constantes, les mettre dans un fichier de constantes de systeme

    public void menuPrincipal() {

        while (true) {

            gymService.printMenuPrincipal();//(etat);

            switch (gymService.menuUserInput(NOMBRE_CAS_DUTILISATION)) {
                case 1:
                    System.out.println("Créer un nouveau compte au gym.");
                    break;
                case 2:
                    System.out.println("Identification du Client.");
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
}
