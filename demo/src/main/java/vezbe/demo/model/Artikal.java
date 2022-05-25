package vezbe.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Artikal implements Serializable {

    public enum TipArtikla{
        JELO,
        PICE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String naziv;

    @Column
    private double cena;

    @Column
    private TipArtikla tipArtikla;

    @Column
    private double kolicina;

    @Column
    private String opis;

    @ManyToMany (mappedBy = "artikliUPonudi")
    @JsonIgnore
    private Set<Restoran> restorani = new HashSet<>();

    @ManyToMany (mappedBy = "poruceniArtikli")
    @JsonIgnore
    private Set<Porudzbine> porudzbine = new HashSet<>();

    public Artikal(String naziv, double cena, TipArtikla tipArtikla, double kolicina, String opis) {
        this.naziv = naziv;
        this.cena = cena;
        this.tipArtikla = tipArtikla;
        this.kolicina = kolicina;
        this.opis = opis;

        this.restorani = new HashSet<>();
        this.porudzbine = new HashSet<>();
    }

    public Artikal(String naziv, double cena, TipArtikla tipArtikla) {
        this.naziv = naziv;
        this.cena = cena;
        this.tipArtikla = tipArtikla;
    }

    public Artikal(String naziv, double cena) {
        this.naziv = naziv;
        this.cena = cena;
    }

    public Artikal() {

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

    public TipArtikla getTipArtikla() {
        return tipArtikla;
    }

    public void setTipArtikla(TipArtikla tipArtikla) {
        this.tipArtikla = tipArtikla;
    }

    public double getKolicina() {
        return kolicina;
    }

    public void setKolicina(double kolicina) {
        this.kolicina = kolicina;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Set<Restoran> getRestorani() {
        return restorani;
    }

    public void setRestorani(Set<Restoran> restorani) {
        this.restorani = restorani;
    }

    public Set<Porudzbine> getPorudzbine() {
        return porudzbine;
    }

    public void setPorudzbine(Set<Porudzbine> porudzbine) {
        this.porudzbine = porudzbine;
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

        s += "ARTIKAL \n";
        s += "Naziv: " + naziv + "\n";
        s += "Cena: " + cena + "\n";

        if(tipArtikla == TipArtikla.JELO) {
            s += "Tip artikla: Jelo \n";
        } else {
            s += "Tip artikla: Pice \n";
        }

        s += "Kolicina: " + kolicina + "\n";
        s += "Opis: " + opis + "\n";

        return s;
    }

}
