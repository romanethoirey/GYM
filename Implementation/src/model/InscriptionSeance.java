package model;

import exception.MauvaisFormatInscriptionSeanceException;
import exception.MauvaisFormatPresenceSeanceException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class InscriptionSeance {
    private LocalDateTime heureCreation;
    private LocalDate dateSeance;
    private Long numeroProfessionnel;
    private Long numeroMembre;
    private Long codeService;
    private String commentaire;

    public InscriptionSeance(String dateSeance, Long numeroProfessionnel, Long numeroMembre, Long codeService, String commentaire) throws MauvaisFormatInscriptionSeanceException {

        if((numeroMembre+"").length() != 9 ||//si longueur invalide
                (numeroProfessionnel+"").length() != 9 ||//si longueur invalide
                (codeService+"").length() != 7 ||//si longueur invalide
                commentaire.length() > 100// si longueur du commentaire plus de 100
        ){throw new MauvaisFormatInscriptionSeanceException("Mauvais format.");}//TODO message plus descriptif

        this.dateSeance = LocalDate.parse(dateSeance, DateTimeFormatter.ofPattern("dd-MM-yyyy"));//TODO check pour date dans le bon range/bon format AVANT
        this.heureCreation = LocalDateTime.now();
        this.numeroMembre = numeroMembre;
        this.numeroProfessionnel = numeroProfessionnel;
        this.codeService = codeService;
        this.commentaire = commentaire;
    }

    public Long getNumeroProfessionnel() {
        return numeroProfessionnel;
    }

    public Long getNumeroMembre() {
        return numeroMembre;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public LocalDateTime getHeureCreation() {
        return heureCreation;
    }

    public LocalDate getDateSeance() {
        return dateSeance;
    }

    public Long getCodeService() {
        return codeService;
    }


    @Override
    public String toString() {
        return "InscriptionSeance{" +
                "heureCreation=" + heureCreation +
                ", dateSeance=" + dateSeance +
                ", numeroProfessionnel=" + numeroProfessionnel +
                ", numeroMembre=" + numeroMembre +
                ", codeService=" + codeService +
                ", commentaire='" + commentaire + '\'' +
                '}';
    }
}