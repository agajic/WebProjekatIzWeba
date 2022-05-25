package vezbe.demo.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

@Entity
public class Korisnik implements Serializable {

    public enum Pol{
        MUSKI,
        ZENSKI
    }

    public enum Uloga{
        ADMIN,
        MENADZER,
        DOSTAVLJAC,
        KUPAC
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (unique = true)
    private String korisnicko_ime;

    @Column
    private String lozinka;

    @Column
    private String ime;

    @Column
    private String prezime;

    @Column
    private Pol pol;

    @Column
    private Date datum;

    @Column
    private Uloga uloga;

    private static ArrayList<String> listaKorisnickihImena = new ArrayList<>();

    public boolean ProveraKorisnickogImena(String k){
        for(int i=0; i<listaKorisnickihImena.size(); i++){
            if(listaKorisnickihImena.get(i).equals(k)){
                return false;
            }
        }
        return true;
    }

    public Korisnik() {

    }

    public Korisnik(String korisnicko_ime, String lozinka){
        this.korisnicko_ime = korisnicko_ime;
        this.lozinka = lozinka;
    }

    public Korisnik(String korisnicko_ime, String lozinka, String ime, String prezime, Pol pol, Date datum, Uloga uloga) {
        if(ProveraKorisnickogImena(korisnicko_ime)){
            this.korisnicko_ime = korisnicko_ime;
            this.lozinka = lozinka;
            this.ime = ime;
            this.prezime = prezime;
            this.pol = pol;
            this.datum = datum;
            this.uloga = uloga;
            listaKorisnickihImena.add(korisnicko_ime);
        }
        else{
            System.out.println("Korisnicko ime je zauzeto!");
        }
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

    public Pol getPol() {
        return pol;
    }

    public void setPol(Pol pol) {
        this.pol = pol;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public Uloga getUloga() {
        return uloga;
    }

    public void setUloga(Uloga uloga) {
        this.uloga = uloga;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        String s = new String();
        s+="Korisnicko ime: " + korisnicko_ime + "\n";
        s+="Ime: " + ime + "\n";
        s+="Prezime: " + prezime + "\n";
        s+="Lozinka: " + lozinka + "\n";

        if(pol == Pol.MUSKI){
            s+="Pol: MUSKI" + "\n";
        }
        else{
            s+="POL: ZENSKI" + "\n";
        }

        if(uloga == Uloga.ADMIN){
            s+="Uloga: ADMIN" + "\n";
        }
        else if(uloga == Uloga.MENADZER){
            s+="Uloga: MENADZER" + "\n";
        }
        else if(uloga == Uloga.DOSTAVLJAC){
            s+="Uloga: DOSTAVLJAC" + "\n";
        }
        else{
            s+="Uloga: KUPAC" + "\n";
        }

        return s;
    }

}
