package model;

import exception.MauvaisFormatInscriptionSeanceException;
import exception.MauvaisFormatPresenceSeanceException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class InscriptionSeance {
    private LocalDateTime heureCreation;// TODO utiliser now()?
    private LocalDate dateSeance;
    private int numeroProfessionnel;
    private int numeroMembre;
    private int codeService;
    private String Commentaire;

    public InscriptionSeance(String heureCreation,String dateSeance, String numeroProfessionnel, String numeroMembre, String codeService, String commentaire) throws MauvaisFormatInscriptionSeanceException {
        this.heureCreation = LocalDateTime.parse(heureCreation, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        this.dateSeance = LocalDate.parse(dateSeance, DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        if(numeroMembre.length() != 9 ||//si longueur invalide
           numeroProfessionnel.length() != 9 ||//si longueur invalide
           codeService.length() != 7 ||//si longueur invalide
           commentaire.length() > 100// si longueur du commentaire plus de 100
        ){throw new MauvaisFormatInscriptionSeanceException("Mauvais format.");}//TODO message plus descriptif
    }
}