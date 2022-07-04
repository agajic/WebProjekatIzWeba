package vezbe.demo.dto;

import vezbe.demo.model.Artikal;

public class ArtikalDto {

    private String naziv;

    private double cena;

    public ArtikalDto(Artikal a){
        this.naziv = a.getNaziv();
        this.cena = a.getCena();
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }
}
