package model;

import exception.MauvaisFormatPresenceSeanceException;
import java.time.LocalDateTime;

public class PresenceSeance {
    private LocalDateTime heureCreation;
    private Long numeroProfessionnel;
    private Long numeroMembre;
    private Long codeSeance;
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
        this.codeSeance = codeService;
        this.commentaire = commentaire;
    }
}