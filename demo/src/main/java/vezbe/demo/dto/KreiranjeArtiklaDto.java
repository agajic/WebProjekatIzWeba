package vezbe.demo.dto;

import vezbe.demo.model.Artikal;

public class KreiranjeArtiklaDto {
    private String naziv;
    private double cena;
    private Artikal.TipArtikla tipArtikla;

    public KreiranjeArtiklaDto(){}

    public KreiranjeArtiklaDto(String naziv, double cena, Artikal.TipArtikla tipArtikla){
        this.naziv = naziv;
        this.cena = cena;
        this.tipArtikla = tipArtikla;
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

    public Artikal.TipArtikla getTipArtikla() {
        return tipArtikla;
    }

    public void setTipArtikla(Artikal.TipArtikla tipArtikla) {
        this.tipArtikla = tipArtikla;
    }
}
