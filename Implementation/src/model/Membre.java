package model;

public class Membre extends Client {

    public String status;

    public Membre(Integer numeroClient, String status) {
        super(numeroClient);
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
