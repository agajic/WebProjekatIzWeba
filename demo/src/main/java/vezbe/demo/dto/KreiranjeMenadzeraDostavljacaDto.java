package vezbe.demo.dto;

import vezbe.demo.model.Korisnik;

import java.util.Date;

public class KreiranjeMenadzeraDostavljacaDto {
    private String korisnicko_ime;
    private String lozinka;
    private String ime;
    private String prezime;
    private Korisnik.Pol pol;
    private Date datum;
    private Korisnik.Uloga uloga;

    public KreiranjeMenadzeraDostavljacaDto(){}

    public KreiranjeMenadzeraDostavljacaDto(String korisnicko_ime, String lozinka, String ime, String prezime, Korisnik.Pol pol, Date datum, Korisnik.Uloga uloga){
        this.korisnicko_ime = korisnicko_ime;
        this.lozinka = lozinka;
        this.ime = ime;
        this.prezime = prezime;
        this.pol = pol;
        this.datum = datum;
        this.uloga = uloga;
    }

    public String getKorisnicko_ime() {
        return korisnicko_ime;
    }

    public void setKorisnicko_ime(String korisnicko_ime) {
        this.korisnicko_ime = korisnicko_ime;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public Korisnik.Pol getPol() {
        return pol;
    }

    public void setPol(Korisnik.Pol pol) {
        this.pol = pol;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public Korisnik.Uloga getUloga() {
        return uloga;
    }

    public void setUloga(Korisnik.Uloga uloga) {
        this.uloga = uloga;
    }
}
