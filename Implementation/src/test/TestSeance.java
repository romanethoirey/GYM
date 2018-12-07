package test;//test

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

import org.junit.Test;

import exception.MauvaisFormatSeanceException;
import org.junit.Assert;
import model.Membre;
import model.Membres;
import model.Professionnel;
import model.Professionnels;
import model.Seance;
import model.Seances;
import model.Clients;
import service.GymService;


public class TestSeance {
	  GymService gymService = new GymService();
	    Membres membres = new Membres();
	    Professionnels professionnels = new Professionnels();
	    Seances seances = new Seances();
	    Clients clients = new Clients(membres, professionnels);
		@SuppressWarnings("deprecation")
		@Test
		public void testLesDatesSeance() throws MauvaisFormatSeanceException {
			membres.addListeMembres(new Membre("Marc-Antoine", "Dufresne Gagnon", "marc-antoine.dufresne.gagnon@umontreal.ca", GymService.Status.Valide, gymService, clients));
            membres.addListeMembres(new Membre("Maud", "Moerel-Martini", "maud.moerel-martini@umontreal.ca", GymService.Status.Suspendu, gymService, clients));
            professionnels.addListeProfessionnels(new Professionnel("Maxime", "Daigle", "maxime.daigle@umontreal.ca",GymService.Status.Valide,gymService, clients));
            professionnels.addListeProfessionnels(new Professionnel("Michaelis", "Famelis", "CONFIDENTIEL",GymService.Status.Valide,gymService, clients));
            professionnels.addListeProfessionnels(new Professionnel("Thomas", "Schweizer", "CONFIDENTIEL",GymService.Status.Valide,gymService, clients));
           
			Seance seance =new Seance("Jogging","11","01-01-2017","31-12-2018","12:30",
				        new ArrayList<Boolean>(Arrays.asList(true,false,true,false,false,true,false)),
				        "25",
				        ((Professionnel) professionnels.getListeProfessionnels().toArray()[2]).getNumeroClient(),
				        "45.35",
				        "",
				        gymService,
				        seances);
			Assert.assertTrue((seance.getDateDebut().compareTo(seance.getDatefin()) < 0));// alors que ca doit pas etre valide
			
			
		}
		
}
