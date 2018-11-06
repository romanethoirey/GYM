package model;

import exception.MauvaisFormatClientException;

public class Professionnel extends Client {
    public Professionnel(String numeroClient) throws MauvaisFormatClientException {
        super(numeroClient);
    }
}
