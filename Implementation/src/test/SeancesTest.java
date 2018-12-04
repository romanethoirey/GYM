package test;

import exception.MauvaisFormatInscriptionSeanceException;
import exception.MauvaisFormatPresenceSeanceException;
import exception.MauvaisFormatSeanceException;
import exception.TropParticipantsException;
import model.*;
import service.GymService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class SeancesTest {
	private GymService gymService = new GymService();
	private Seances seances=new Seances();
	private Seance s;
	
	
	@Before
	public void setUp() throws Exception {
		s = new Seance ("Yoga","01-01-2018","31-12-2018","12:30",new ArrayList<Boolean>(Arrays.asList(true,false,true,false,false,true,false)),
                "25",(long) 111111111,"51.35","",gymService,seances); 
		seances.addListeSeances(s);
	}

	@Test
	public void testGetSceance() {
		assertEquals(seances.getSceance(s.getCode()),s);
		assertNotNull(seances.getSceance(0));
		
	}

}
