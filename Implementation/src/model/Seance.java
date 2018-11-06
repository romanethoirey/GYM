package model;

import exception.MauvaisFormatSeanceException;
import javafx.util.converter.LocalDateStringConverter;
import javafx.util.converter.LocalDateTimeStringConverter;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Seance {
    private LocalDateTime heureCreation;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private LocalTime heureSeance;
    private ArrayList<Boolean> recurHebdo;//liste de 7 elements, dimanche a samedi, 0 ou 1 s'il y a cours
    private int capacite;
    private int numeroProfessionnel;
    private int code;
    private String frais;
    private String Commentaire;

    public Seance(String heureCreation, String dateDebut, String dateFin, String heureSeance, ArrayList<Boolean> recurHebdo, String capacite, String numeroProfessionnel, String code, String frais, String commentaire) throws MauvaisFormatSeanceException{

        this.heureCreation = LocalDateTime.parse(heureCreation, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        this.dateDebut = LocalDate.parse(dateDebut, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        this.dateFin = LocalDate.parse(dateFin, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        this.heureSeance = LocalTime.parse(heureSeance, DateTimeFormatter.ofPattern("HH:mm"));

        if(
           recurHebdo.size() != 7 || // Si pas exactement 7 jours
           Integer.getInteger(capacite) > 30 || // si capacite de plus de 30 membres
           numeroProfessionnel.length()!=9 ||//si longueur invalide
           code.length() != 7 ||//si longueur invalide
           Float.valueOf(frais) > 100.00 || Float.valueOf(frais) < 0 ||//si valeur trop grande ou sous 0
           commentaire.length() > 100// si longueur du commentaire plus de 100
        ){throw new MauvaisFormatSeanceException();}

        this.recurHebdo = recurHebdo;
        this.capacite = Integer.getInteger(capacite);
        this.numeroProfessionnel = Integer.getInteger(numeroProfessionnel);
        this.code = Integer.getInteger(code);
        this.frais = frais;
        this.Commentaire = commentaire;
    }

    public Integer getCode() {
        return this.code;
    }
}
