package vezbe.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Restoran implements Serializable {

    public enum StatusRestorana{
        RADI,
        NE_RADI
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String naziv;

    @Column
    private String tipRestorana;

    @Column
    private StatusRestorana status;

    @ManyToMany
    @JoinTable(name = "RestoranArtikli",
            joinColumns = @JoinColumn(name = "restoran_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "artikal_id", referencedColumnName = "id"))
    private Set<Artikal> artikliUPonudi = new HashSet<>();

    @OneToOne
    @JoinColumn (name = "lokacija_id")
    private Lokacija lokacija;

    @OneToMany (mappedBy = "mesto", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Porudzbine> porudzbine = new HashSet<>();

    @OneToMany (mappedBy = "restoran", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Komentar> komentari = new HashSet<>();

    public Restoran(String naziv, String tipRestorana, Lokacija lokacija, StatusRestorana status) {
        this.naziv = naziv;
        this.tipRestorana = tipRestorana;
        this.lokacija = lokacija;
        this.status = status;
        this.artikliUPonudi = new HashSet<>();
        this.porudzbine = new HashSet<>();
        this.komentari = new HashSet<>();
    }

    public Restoran(String naziv, String tipRestorana, StatusRestorana status) {
        this.naziv = naziv;
        this.tipRestorana = tipRestorana;
        this.status = status;
        this.artikliUPonudi = new HashSet<>();
        this.porudzbine = new HashSet<>();
        this.komentari = new HashSet<>();
    }

    public Restoran() {

    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getTipRestorana() {
        return tipRestorana;
    }

    public void setTipRestorana(String tipRestorana) {
        this.tipRestorana = tipRestorana;
    }

    public Lokacija getLokacija() {
        return lokacija;
    }

    public void setLokacija(Lokacija lokacija) {
        this.lokacija = lokacija;
    }

    public Set<Komentar> getKomentari() {
        return komentari;
    }

    public void setKomentari(Set<Komentar> komentari) {
        this.komentari = komentari;
    }

    public Set<Porudzbine> getPorudzbine() {
        return porudzbine;
    }

    public void setPorudzbine(Set<Porudzbine> porudzbine) {
        this.porudzbine = porudzbine;
    }

    public Set<Artikal> getArtikliUPonudi() {
        return artikliUPonudi;
    }

    public void setArtikliUPonudi(Set<Artikal> artikliUPonudi) {
        this.artikliUPonudi = artikliUPonudi;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StatusRestorana getStatus() {
        return status;
    }

    public void setStatus(StatusRestorana status) {
        this.status = status;
    }

    @Override
    public String toString() {
        String s = new String();


        s += "RESTORAN \n";
        s += "Naziv: " + naziv + "\n";

        s += "Tip restorana: " + tipRestorana + "\n";

        s+= "Lista artikala: \n";
        int count = 1;
        for (Artikal a : artikliUPonudi){
            s += "  " + count + ") " + a.getNaziv() + "\n";
            count++;
        }

        s += "Lokacija: " + lokacija.getAdresa() + "\n";

        return s;
    }

}
