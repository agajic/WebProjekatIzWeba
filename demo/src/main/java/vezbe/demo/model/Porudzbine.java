package vezbe.demo.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Porudzbine implements Serializable {

    public enum Status{
        OBRADA,
        U_PRIPREMI,
        CEKA_DOSTAVLJACA,
        U_TRANSPORTU,
        DOSTAVLJENA,
        OTKAZANA
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String UUID;

    @ManyToMany
    @JoinTable(name = "PorudzbineArtikli",
            joinColumns = @JoinColumn(name = "porudzbine_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "artikal_id", referencedColumnName = "id"))
    private Set<Artikal> poruceniArtikli = new HashSet<>();

    @ManyToOne
    @JoinColumn (name = "restoran_id")
    private Restoran mesto;

    @Column
    private Date vremePorudzbine;

    @Column
    private double cena;

    @ManyToOne
    @JoinColumn (name = "kupac_id")
    private Kupac kupac;

    @Column
    private Status status;

    @ManyToOne
    @JoinColumn (name = "dostavljac_id")
    private Dostavljac dostavljac;

    private static ArrayList<String> listaUUID = new ArrayList<>();

    public boolean ProveraUUID(String u){
        for(int i=0; i<listaUUID.size(); i++){
            if(listaUUID.get(i).equals(u)){
                return false;
            }
        }
        return true;
    }

    public Porudzbine(String UUID, Restoran mesto, Date vremePorudzbine, double cena, Kupac kupac, Status status, Dostavljac dostavljac) {
        if(ProveraUUID(UUID)){
            this.UUID = UUID;
            this.mesto = mesto;
            this.vremePorudzbine = vremePorudzbine;
            this.cena = cena;
            this.kupac = kupac;
            this.status = status;
            this.dostavljac = dostavljac;

            this.poruceniArtikli = new HashSet<>();
        }
        else{
            System.out.println("Takav UUID vec postoji!");
        }
    }

    public Porudzbine(String UUID, Restoran mesto, Date vremePorudzbine, double cena, Kupac kupac, Status status) {
            this.UUID = UUID;
            this.mesto = mesto;
            this.vremePorudzbine = vremePorudzbine;
            this.cena = cena;
            this.kupac = kupac;
            this.status = status;
            this.poruceniArtikli = new HashSet<>();
    }

    public Porudzbine(String UUID, Restoran mesto, Kupac kupac, Dostavljac dostavljac) {
        this.UUID = UUID;
        this.mesto = mesto;
        this.kupac = kupac;
        this.dostavljac = dostavljac;
        this.poruceniArtikli = new HashSet<>();
    }

    public Porudzbine() {

    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public Restoran getMesto() {
        return mesto;
    }

    public void setMesto(Restoran mesto) {
        this.mesto = mesto;
    }

    public Date getVremePorudzbine() {
        return vremePorudzbine;
    }

    public void setVremePorudzbine(Date vremePorudzbine) {
        this.vremePorudzbine = vremePorudzbine;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public Kupac getKupac() {
        return kupac;
    }

    public void setKupac(Kupac kupac) {
        this.kupac = kupac;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Dostavljac getDostavljac() {
        return dostavljac;
    }

    public void setDostavljac(Dostavljac dostavljac) {
        this.dostavljac = dostavljac;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Artikal> getPoruceniArtikli() {
        return poruceniArtikli;
    }

    public void setPoruceniArtikli(Set<Artikal> poruceniArtikli) {
        this.poruceniArtikli = poruceniArtikli;
    }

    @Override
    public String toString() {
        String s = new String();
        s += "PORUDZBINA \n";
        s += "UUID: " + UUID + "\n";

        s += "Poruceni artikli: \n";
        int count = 1;
        for(Artikal a : poruceniArtikli){
            s += "  " + count + ") " + a.getNaziv() + "\n";
            count++;
        }

        s += "Restoran iz kog je poruceno: " + mesto.getNaziv() + "\n";
        s += "Vreme porudzbine: " + vremePorudzbine + "\n";
        s += "Cena: " + cena + "\n";
        s += "Kupac: " + kupac.getIme() + " " + kupac.getPrezime() + "\n";

        if(status == Status.OBRADA){
            s += "Status: OBRADA \n";
        }
        else if(status == Status.U_PRIPREMI){
            s += "Status: U PRIPREMI \n";
        }
        else if(status == Status.CEKA_DOSTAVLJACA){
            s += "Status: CEKA DOSTAVLJACA \n";
        }
        else if(status == Status.U_TRANSPORTU){
            s += "Status: U TRANSPORTU \n";
        }
        else if(status == Status.DOSTAVLJENA){
            s += "Status: DOSTAVLJENA \n";
        }
        else{
            s += "Status: OTKAZANA \n";
        }

        return s;
    }

}
