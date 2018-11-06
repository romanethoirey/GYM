package model;

import java.util.ArrayList;
import java.util.List;

public class Professionnels {
    private List<Professionnel> listeProfessionnels;

    public Professionnels() {
        this.listeProfessionnels = new ArrayList<Professionnel>();
    }

    public List<Professionnel> getListeProfessionnels() {
        return listeProfessionnels;
    }

    public void addListeProfessionnels(Professionnel professionnel) {
        listeProfessionnels.add(professionnel);
    }

    public Professionnel getProfessionnel(int numeroProfessionnel){
        return listeProfessionnels.stream().filter(professionnel -> professionnel.getNumeroClient().equals(numeroProfessionnel)).findAny().orElse(null);
    }
}