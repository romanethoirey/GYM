package model;

import java.util.ArrayList;
import java.util.List;

public class Clients {

    private List<Client> listeClients;

    public Clients(Membres membres, Professionnels professionnels) {
        this.listeClients = new ArrayList<Client>();
        this.listeClients.addAll(membres.getListeMembres());
        this.listeClients.addAll(professionnels.getListeProfessionnels());
    }

    public List<Client> getListeClients() {
        return listeClients;
    }
}
