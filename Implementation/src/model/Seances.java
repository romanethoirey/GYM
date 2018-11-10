package model;

import java.util.ArrayList;
import java.util.List;

public class Seances {

    private List<Seance> listeSeances;

    public Seances() {
        this.listeSeances = new ArrayList<Seance>();
    }

    public List<Seance> getListeSeances() {
        return listeSeances;
    }

    public void addListeSeances(Seance Seance) {
        listeSeances.add(Seance);
    }

    public Seance getSceance(int numeroSeance){
        return this.listeSeances
                .stream()
                .filter(sceance -> sceance.getCode().equals(numeroSeance))
                .findAny()
                .orElse(null);
    }
}