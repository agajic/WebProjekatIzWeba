package vezbe.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Kupac extends Korisnik implements Serializable {

    @OneToMany (mappedBy = "kupac", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Porudzbine> listaPorudzbina = new HashSet<>();

    @Column
    private int bodovi;

    @ManyToOne
    @JoinColumn (name = "tipKupca_id")
    private TipKupca tipKupca;

    @OneToMany (mappedBy = "kupac", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Komentar> komentari = new HashSet<>();

    public Kupac(String korisnicko_ime, String lozinka, String ime, String prezime, Pol pol, Date datum, Uloga uloga, int bodovi, TipKupca tipKupca) {
        super(korisnicko_ime, lozinka, ime, prezime, pol, datum, uloga);
        this.bodovi = bodovi;
        this.tipKupca = tipKupca;
        this.listaPorudzbina = new HashSet<>();
        this.komentari = new HashSet<>();
    }

    public Kupac(String korisnicko_ime, String lozinka) {
        super(korisnicko_ime, lozinka);
    }

    public Kupac() {

    }

    public int getBodovi() {
        return bodovi;
    }

    public void setBodovi(int bodovi) {
        this.bodovi = bodovi;
    }

    public TipKupca getTipKupca() {
        return tipKupca;
    }

    public void setTipKupca(TipKupca tipKupca) {
        this.tipKupca = tipKupca;
    }

    public Set<Komentar> getKomentari() {
        return komentari;
    }

    public void setKomentari(Set<Komentar> komentari) {
        this.komentari = komentari;
    }

    public Set<Porudzbine> getListaPorudzbina() {
        return listaPorudzbina;
    }

    public void setListaPorudzbina(Set<Porudzbine> listaPorudzbina) {
        this.listaPorudzbina = listaPorudzbina;
    }

    @Override
    public String toString() {
        String s = new String();
        s += super.toString();

        s += "Bodovi: " + bodovi + "\n";
        s += "Tip kupca: \n" + tipKupca.toString();

        s += "Lista porudzbina: \n";
        int count = 1;
        for(Porudzbine p : listaPorudzbina){
            s += "  " + count + ") " + p.getUUID() + "\n";
            count++;
        }

        return s;
    }

}
