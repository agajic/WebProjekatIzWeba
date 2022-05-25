package vezbe.demo.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Menadzer extends Korisnik implements Serializable {

    @OneToOne
    @JoinColumn (name = "restoran_id")
    private Restoran zaduzen;

    public Menadzer(String korisnicko_ime, String lozinka, String ime, String prezime, Pol pol, Date datum, Uloga uloga, Restoran zaduzen) {
        super(korisnicko_ime, lozinka, ime, prezime, pol, datum, uloga);
        this.zaduzen = zaduzen;
    }

    public Menadzer(String korisnicko_ime, String lozinka, String ime, String prezime, Pol pol, Date datum, Uloga uloga) {
        super(korisnicko_ime, lozinka, ime, prezime, pol, datum, uloga);
    }

    public Menadzer(String korisnicko_ime, String lozinka, Restoran zaduzen) {
        super(korisnicko_ime, lozinka);
        this.zaduzen = zaduzen;
    }

    public Menadzer(String korisnicko_ime, String lozinka) {
        super(korisnicko_ime, lozinka);
    }

    public Menadzer() {

    }

    public Restoran getZaduzen() {
        return zaduzen;
    }

    public void setZaduzen(Restoran zaduzen) {
        this.zaduzen = zaduzen;
    }



    @Override
    public String toString() {
        String s = new String();
        s += super.toString();
        s += zaduzen.toString() + "\n";
        return s;
    }

}
