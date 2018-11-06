package model;

public abstract class Client {

    private Integer numeroClient;// unique a chaque Client

    public Client(Integer numeroClient) {
        this.numeroClient = numeroClient;
    }

    public Integer getNumeroClient() {
        return numeroClient;
    }
}
