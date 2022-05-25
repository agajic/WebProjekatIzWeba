package vezbe.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class TipKupca implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String ime;

    @Column
    private double popust;

    @Column
    private int trazeniBodovi;

    @OneToMany (mappedBy = "tipKupca", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Kupac> kupci = new HashSet<>();

    public TipKupca() {

    }

    public TipKupca(String ime, double popust) {
        this.ime = ime;
        this.popust = popust;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public double getPopust() {
        return popust;
    }

    public void setPopust(double popust) {
        this.popust = popust;
    }

    public int getTrazeniBodovi() {
        return trazeniBodovi;
    }

    public void setTrazeniBodovi(int trazeniBodovi) {
        this.trazeniBodovi = trazeniBodovi;
    }

    public Set<Kupac> getKupci() {
        return kupci;
    }

    public void setKupci(Set<Kupac> kupci) {
        this.kupci = kupci;
    }

    @Override
    public String toString() {
        String s = new String();

        s += "Ime: " + ime + "\n";
        s += "Popust: " + popust + "%\n";
        s += "Trazeni bodovi: " + trazeniBodovi + "\n";

        return s;
    }

}
