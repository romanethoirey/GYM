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
     * @param dateSeance
     * @param numeroProfessionnel
     * @param numeroMembre
     * @param codeService
     * @param commentaire
     * @throws MauvaisFormatInscriptionSeanceException
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
     * @return
     */
    public Long getNumeroMembre() {
        return numeroMembre;
    }
    
    /**
     * @return
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