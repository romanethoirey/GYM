package model;

import java.util.ArrayList;
import java.util.List;

public class Membres {

    private List<Membre> listeMembres;

    public Membres() {
        this.listeMembres = new ArrayList<Membre>();
    }

    public List<Membre> getListeMembres() {
        return listeMembres;
    }

    public void addListeMembres(Membre Membre) {
        listeMembres.add(Membre);
    }

    public Membre getMembre(int numeroMembre){
        return listeMembres.stream().filter(membre -> membre.getNumeroClient().equals(numeroMembre)).findAny().orElse(null);
    }
}