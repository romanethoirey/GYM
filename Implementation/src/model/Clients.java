package model;

import java.util.ArrayList;
import java.util.List;

public class Clients {

    private List<Client> listeClients;
    private Membres membres;
    private Professionnels professionnels;

    public Clients(Membres membres, Professionnels professionnels) {
        this.membres = membres;
        this.professionnels = professionnels;
        this.listeClients = new ArrayList<Client>();

    }

    public List<Client> getListeClients() {
        this.listeClients.addAll(this.membres.getListeMembres());
        this.listeClients.addAll(this.professionnels.getListeProfessionnels());
        return listeClients;
    }

    public Client getClient(Long numeroClient){
        return this.listeClients
                .stream()
                .filter(client -> client.getNumeroClient().equals(numeroClient))
                .findAny()
                .orElse(null);
    }
}
