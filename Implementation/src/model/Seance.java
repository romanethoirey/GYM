package model;

import exception.MauvaisFormatSeanceException;
import exception.TropParticipantsException;
import service.GymService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Seance {
    private String titre;
    private LocalDateTime heureCreation;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private LocalTime heureSeance;
    private ArrayList<Boolean> recurHebdo;//liste de 7 elements, dimanche a samedi, 0 ou 1 s'il y a cours
    private Long capacite;
    private Long numeroProfessionnel;
    private Long code;
    private Double frais;//TODO couper le signe de $ a la fin du String
    private String commentaire;
    private ArrayList<InscriptionSeance> listeInscriptionsSeance;
    private ArrayList<PresenceSeance> listePresencesSeance;

    public Seance(String titreSeance, String code,String dateDebut, String dateFin, String heureSeance, ArrayList<Boolean> recurHebdo, String capacite, Long numeroProfessionnel, String frais, String commentaire, GymService gymService, Seances seances) throws MauvaisFormatSeanceException{
        this.titre = titreSeance;
        this.heureCreation = LocalDateTime.now();
        this.dateDebut = LocalDate.parse(dateDebut, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        this.dateFin = LocalDate.parse(dateFin, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        this.heureSeance = LocalTime.parse(heureSeance, DateTimeFormatter.ofPattern("HH:mm"));

        if(recurHebdo.size() != 7 || // Si pas exactement 7 jours
           Integer.parseInt(capacite) > 30 || // si capacite de plus de 30 membres
           Float.valueOf(frais) > 100.00 || Float.valueOf(frais) < 0 ||//si valeur trop grande ou sous 0
           commentaire.length() > 100// si longueur du commentaire plus de 100
        ){throw new MauvaisFormatSeanceException("Mauvais format.");}//TODO message plus descriptif

        while(true){
            this.code = Long.parseLong(code);
            if(seances
                    .getListeSeances()
                    .stream()
                    .filter(seance->seance.code.equals(this.code)).count() == 0){ break;}//verification que le numero genere n'existe pas
        }
        this.recurHebdo = recurHebdo;
        this.capacite = Long.parseLong(capacite);
        this.numeroProfessionnel = numeroProfessionnel;
        this.frais = Double.parseDouble(frais);
        this.commentaire = commentaire;
        this.listeInscriptionsSeance = new ArrayList<InscriptionSeance>();
        this.listePresencesSeance = new ArrayList<PresenceSeance>();
    }

    public Long getCode() {
        return this.code;
    }

    public String getTitre() {
        return this.titre;
    }

    public Double getFrais() {
        return this.frais;
    }

    public Long getNumeroProfessionnel() {
        return numeroProfessionnel;
    }

    public void addInscription(InscriptionSeance inscriptionSeance) throws TropParticipantsException{
        if(this.listeInscriptionsSeance.size()<this.capacite){this.listeInscriptionsSeance.add(inscriptionSeance);}
        else{throw new TropParticipantsException("La limite d'inscriptions est deja atteinte.");}
    }

    public ArrayList<InscriptionSeance> getListeInscriptionsSeance() {
        return this.listeInscriptionsSeance;
    }

    public void addPresence(PresenceSeance presenceSeance){
        this.listePresencesSeance.add(presenceSeance);
    }

    public ArrayList<PresenceSeance> getListePresenceSeance() {
        return this.listePresencesSeance;
    }

    public boolean membreInscrit(Long numeroMembre){
        return this.listeInscriptionsSeance.stream().anyMatch(inscription->inscription.getNumeroMembre().equals(numeroMembre));
    }
    
    public String affichageJour(ArrayList<Boolean> recurHebdo) {
    	String list = "";
    	for(int i=0;i<recurHebdo.size();i++) {
    		if(recurHebdo.get(0)==true && list.contains("Dimanche")==false) {
    			list+="Dimanche, ";
    		}
    		if(recurHebdo.get(1)==true && list.contains("Lundi")==false) {
    			list+="Lundi, ";
    		}
    		if(recurHebdo.get(2)==true && list.contains("Mardi")==false) {
    			list+="Mardi, ";
    		}
    		if(recurHebdo.get(3)==true && list.contains("Mercredi")==false) {
    			list+="Mercredi, ";
    		}
    		if(recurHebdo.get(4)==true && list.contains("Jeudi")==false) {
    			list+="Jeudi, ";
    		}
    		if(recurHebdo.get(5)==true && list.contains("Vendredi")==false) {
    			list+="Vendredi, ";
    		}
    		if(recurHebdo.get(6)==true && list.contains("Samedi")==false) {
    			list+="Samedi ";
    		}
    	}
    	return list;
    }

    @Override
    public String toString() {
        return "" +
               titre +" "+code+ '\n'+
                "  heureCreation: " + heureCreation +
                ", dateDebut: " + dateDebut +
                ", dateFin: " + dateFin +
                ", heureSeance: " + heureSeance + ",\n" +
                "  Récurrence: " + affichageJour(recurHebdo) + '\n' +
                "  capacite: " + capacite + '\n' +
                "  numeroProfessionnel: " + numeroProfessionnel + '\n' +
                "  frais: " + frais + "$\n" +
                "  Commentaire: " + commentaire + '\n';
    }
}
