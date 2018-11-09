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
    private LocalDateTime heureCreation;// TODO utiliser now()?
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private LocalTime heureSeance;
    private ArrayList<Boolean> recurHebdo;//liste de 7 elements, dimanche a samedi, 0 ou 1 s'il y a cours
    private Long capacite;
    private Long numeroProfessionnel;
    private Long code;
    private String frais;//TODO couper le signe de $ a la fin du String
    private String Commentaire;
    private ArrayList<Membre> listeParticipants;
    private ArrayList<InscriptionSeance> listeInscriptionsSeance;
    private ArrayList<PresenceSeance> listePresencesSeance;

    public Seance(String heureCreation, String dateDebut, String dateFin, String heureSeance, ArrayList<Boolean> recurHebdo, String capacite, String numeroProfessionnel, String frais, String commentaire, GymService gymService, Seances seances) throws MauvaisFormatSeanceException{
        this.heureCreation = LocalDateTime.parse(heureCreation, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        this.dateDebut = LocalDate.parse(dateDebut, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        this.dateFin = LocalDate.parse(dateFin, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        this.heureSeance = LocalTime.parse(heureSeance, DateTimeFormatter.ofPattern("HH:mm"));

        if(recurHebdo.size() != 7 || // Si pas exactement 7 jours
           Integer.getInteger(capacite) > 30 || // si capacite de plus de 30 membres
           numeroProfessionnel.length()!=9 ||//si longueur invalide
           Float.valueOf(frais) > 100.00 || Float.valueOf(frais) < 0 ||//si valeur trop grande ou sous 0
           commentaire.length() > 100// si longueur du commentaire plus de 100
        ){throw new MauvaisFormatSeanceException("Mauvais format.");}//TODO message plus descriptif

        while(true){
            this.code = gymService.randomLongLengthN(7);
            if(seances.getListeSeances().stream().filter(seance->seance.code.equals(this.code)).count() == 0){ break;}//verification que le numero genere n'existe pas
        }
        this.recurHebdo = recurHebdo;
        this.capacite = Long.getLong(capacite);
        this.numeroProfessionnel = Long.getLong(numeroProfessionnel);
        this.frais = frais;
        this.Commentaire = commentaire;
        this.listeParticipants = new ArrayList<Membre>();
    }

    public Long getCode() {
        return this.code;
    }

    public void addMembreParticipant(Membre membre) throws TropParticipantsException{
        if(this.listeParticipants.size()<this.capacite){this.listeParticipants.add(membre);}
        else{throw new TropParticipantsException("La limite de participant est deja atteinte.");}
    }
}
