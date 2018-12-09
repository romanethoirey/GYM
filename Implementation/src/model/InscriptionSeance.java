package model;

import exception.MauvaisFormatInscriptionSeanceException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class InscriptionSeance {
    private LocalDateTime heureCreation;
    private LocalDate dateSeance;
    private Long numeroProfessionnel;
    private Long numeroMembre;
    private int codeSeance;
    private String commentaire;

    /**
     * Construit un nouvel enregistrement pour une inscription
     * @param dateSeance La date de la séance 
     * @param numeroProfessionnel Le numéro du professionnel ayant proposé la séance
     * @param numeroMembre Le numéro du membre s'inscrivant
     * @param codeService Le code du Service proposé
     * @param commentaire Divers commentaires
     * @throws MauvaisFormatInscriptionSeanceException si les codes client ne font pas 9 chiffres, CodeService != 7 ou 100+ carac pour les commentaires
     */
    public InscriptionSeance(String dateSeance, Long numeroProfessionnel, Long numeroMembre, int codeService, String commentaire) throws MauvaisFormatInscriptionSeanceException {

        if((numeroMembre+"").length() != 9 ||//si longueur invalide
                (numeroProfessionnel+"").length() != 9 ||//si longueur invalide
                (codeService+"").length() != 7 ||//si longueur invalide
                commentaire.length() > 100// si longueur du commentaire plus de 100
        ){throw new MauvaisFormatInscriptionSeanceException("Mauvais format.");}//TODO message plus descriptif

        this.dateSeance = LocalDate.parse(dateSeance, DateTimeFormatter.ofPattern("dd-MM-yyyy"));//TODO check pour date dans le bon range/bon format AVANT
        this.heureCreation = LocalDateTime.now();
        this.numeroMembre = numeroMembre;
        this.numeroProfessionnel = numeroProfessionnel;
        this.codeSeance = codeService;
        this.commentaire = commentaire;
    }

    /**
     * @return le numéro du membre
     */
    public Long getNumeroMembre() {
        return numeroMembre;
    }
    
    /**
     * @return le code du Service proposé
     */
    public int getCodeService() {
    	return codeSeance;
    }

    /** (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "InscriptionSeance{" +
                "heureCreation=" + heureCreation +
                ", dateSeance=" + dateSeance +
                ", numeroProfessionnel=" + numeroProfessionnel +
                ", numeroMembre=" + numeroMembre +
                ", codeService=" + codeSeance +
                ", commentaire='" + commentaire + '\'' +
                '}';
    }
}