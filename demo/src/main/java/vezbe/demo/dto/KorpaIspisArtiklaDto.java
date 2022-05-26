package vezbe.demo.dto;

import vezbe.demo.model.Korpa;

public class KorpaIspisArtiklaDto {
    private String nazivArtikla;
    private String nazivRestorana;
    private int kolicina;
    private double cena;

    public KorpaIspisArtiklaDto(){}

    public KorpaIspisArtiklaDto(Korpa korpa){
        this.nazivArtikla = korpa.getArtikal().getNaziv();
        this.kolicina = korpa.getKolicina();
        this.cena = korpa.getCenaArtikla();
        this.nazivArtikla = korpa.getRestoran().getNaziv();
    }

    public String getNazivArtikla() {
        return nazivArtikla;
    }

    public void setNazivArtikla(String nazivArtikla) {
        this.nazivArtikla = nazivArtikla;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public String getNazivRestorana() {
        return nazivRestorana;
    }

    public void setNazivRestorana(String nazivRestorana) {
        this.nazivRestorana = nazivRestorana;
    }
}
