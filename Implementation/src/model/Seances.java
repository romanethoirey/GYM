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

    public void addListeSeances(Seance seance) {
        listeSeances.add(seance);
    }

    public Seance getSceance(long numeroSeance){
        return this.listeSeances
                .stream()
                .filter(sceance -> sceance.getCode().equals(numeroSeance))
                .findAny()
                .orElse(null);
    }
}