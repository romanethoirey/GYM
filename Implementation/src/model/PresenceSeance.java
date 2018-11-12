package model;

import exception.MauvaisFormatPresenceSeanceException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PresenceSeance {
    private LocalDateTime heureCreation;// TODO utiliser now()?
    private Long numeroProfessionnel;
    private Long numeroMembre;
    private Long codeService;
    private String commentaire;

    public PresenceSeance(Long numeroProfessionnel, Long numeroMembre, Long codeService, String commentaire) throws MauvaisFormatPresenceSeanceException{
        if((numeroMembre+"").length() != 9 ||//si longueur invalide
                (numeroProfessionnel+"").length() != 9 ||//si longueur invalide
                (codeService+"").length() != 7 ||//si longueur invalide
                commentaire.length() > 100// si longueur du commentaire plus de 100
        ){throw new MauvaisFormatPresenceSeanceException("Mauvais format.");}//TODO message plus descriptif

        this.heureCreation = LocalDateTime.now();
        this.numeroMembre = numeroMembre;
        this.numeroProfessionnel = numeroProfessionnel;
        this.codeService = codeService;
        this.commentaire = commentaire;
    }
}