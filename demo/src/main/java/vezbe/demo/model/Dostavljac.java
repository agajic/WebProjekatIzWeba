package vezbe.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Dostavljac extends Korisnik implements Serializable {

    @OneToMany (mappedBy = "dostavljac", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Porudzbine> porudzbineZaDostavu = new HashSet<>();

    public Dostavljac(String korisnicko_ime, String lozinka, String ime, String prezime, Pol pol, Date datum, Uloga uloga) {
        super(korisnicko_ime, lozinka, ime, prezime, pol, datum, uloga);
        this.porudzbineZaDostavu = new HashSet<>();
    }

    public Dostavljac(String korisnicko_ime, String lozinka) {
        super(korisnicko_ime, lozinka);
    }

    public Dostavljac() {

    }

    public Set<Porudzbine> getPorudzbineZaDostavu() {
        return porudzbineZaDostavu;
    }

    public void setPorudzbineZaDostavu(Set<Porudzbine> porudzbineZaDostavu) {
        this.porudzbineZaDostavu = porudzbineZaDostavu;
    }

    @Override
    public String toString() {
        String s = new String();
        s += super.toString();

        s += "Porudzbine za dostavu: \n";
        int count = 1;
        for(Porudzbine p : porudzbineZaDostavu){
            s += "  " + count + ") " + p.getUUID() + "\n";
            count++;
        }

        return s;
    }

}
