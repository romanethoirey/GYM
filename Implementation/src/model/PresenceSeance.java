package model;

import exception.MauvaisFormatPresenceSeanceException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PresenceSeance {
    private LocalDateTime heureCreation;// TODO utiliser now()?
    private int numeroProfessionnel;
    private int numeroMembre;
    private int codeService;
    private String commentaire;

    public PresenceSeance(String heureCreation, String numeroProfessionnel, String numeroMembre, String codeService, String commentaire) throws MauvaisFormatPresenceSeanceException{
        this.heureCreation = LocalDateTime.parse(heureCreation, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));

        if(numeroMembre.length() != 9 ||//si longueur invalide
           numeroProfessionnel.length() != 9 ||//si longueur invalide
           codeService.length() != 7 ||//si longueur invalide
           commentaire.length() > 100// si longueur du commentaire plus de 100
        ){throw new MauvaisFormatPresenceSeanceException("Mauvais format.");}//TODO message plus descriptif
    }
}