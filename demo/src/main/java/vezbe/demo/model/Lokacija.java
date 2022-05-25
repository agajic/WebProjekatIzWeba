package vezbe.demo.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Lokacija implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private double geoDuzina;

    @Column
    private double geoSirina;

    @Column
    private String adresa;

    public Lokacija(double geoDuzina, double geoSirina, String adresa) {
        this.geoDuzina = geoDuzina;
        this.geoSirina = geoSirina;
        this.adresa = adresa;
    }

    public Lokacija(String adresa) {
        this.adresa = adresa;
    }

    public Lokacija() {

    }

    public double getGeoDuzina() {
        return geoDuzina;
    }

    public void setGeoDuzina(double geoDuzina) {
        this.geoDuzina = geoDuzina;
    }

    public double getGeoSirina() {
        return geoSirina;
    }

    public void setGeoSirina(double geoSirina) {
        this.geoSirina = geoSirina;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    @Override
    public String toString() {
        String s = new String();
        s += "Geografska duzina: " + geoDuzina + "\n";
        s += "Geografska sirina: " + geoSirina + "\n";
        s += "Adresa: " + adresa + "\n";
        return s;
    }

}
