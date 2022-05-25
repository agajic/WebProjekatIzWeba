package vezbe.demo.dto;

import vezbe.demo.model.Korisnik;

import java.util.Date;

public class KorisnikUpdateDto {
    private String korisnicko_ime;

    private String lozinka;

    private String ime;

    private String prezime;

    private Korisnik.Pol pol;

    private Date datum;

    public KorisnikUpdateDto(){}

    public KorisnikUpdateDto(Korisnik k){
        this.korisnicko_ime = k.getKorisnicko_ime();
        this.lozinka = k.getLozinka();
        this.ime = k.getIme();
        this.prezime = k.getPrezime();
        this.pol = k.getPol();
        this.datum = k.getDatum();
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
}
