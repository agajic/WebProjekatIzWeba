package vezbe.demo.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Komentar implements Serializable {

    /*enum Ocena{
        JEDAN,
        DVA,
        TRI,
        CETIRI,
        PET
    }*/

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn (name = "kupac_id")
    private Kupac kupac;

    @ManyToOne
    @JoinColumn (name = "restoran_id")
    private Restoran restoran;

    @Column
    private String tekst;

    @Column
    private int ocena;

    public Komentar(Kupac kupac, Restoran restoran, String tekst, int ocena) {
        this.kupac = kupac;
        this.restoran = restoran;
        this.tekst = tekst;
        this.ocena = ocena;
    }

    public Komentar() {
    }

    public Kupac getKupac() {
        return kupac;
    }

    public void setKupac(Kupac kupac) {
        this.kupac = kupac;
    }

    public Restoran getRestoran() {
        return restoran;
    }

    public void setRestoran(Restoran restoran) {
        this.restoran = restoran;
    }

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    public int getOcena() {
        return ocena;
    }

    public void setOcena(int ocena) {
        this.ocena = ocena;
    }

    @Override
    public String toString() {
        String s = new String();
        s += "KOMENTAR \n";
        s += "Kupac: " + kupac.toString();
        s += "Restoran: " + restoran.toString();
        s += "Tekst komentara: " + tekst + "\n";
       /* if(ocena == Ocena.JEDAN){
            s += "Ocena: JEDAN \n";
        }
        else if(ocena == Ocena.DVA){
            s += "Ocena: DVA \n";
        }
        else if(ocena == Ocena.TRI){
            s += "Ocena: TRI \n";
        }
        else if(ocena == Ocena.CETIRI){
            s += "Ocena: CETIRI \n";
        }
        else{
            s += "Ocena: PET \n";
        }*/
        return s;
    }

}
