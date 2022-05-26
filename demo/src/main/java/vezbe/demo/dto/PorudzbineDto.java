package vezbe.demo.dto;

import vezbe.demo.model.Porudzbine;

import java.sql.Date;

public class PorudzbineDto { //Ispis Porudzbina od strane menadzera
    private String UUID;

    private Date vremePorudzbine;

    private ImePrzDto kupac;

    private ImePrzDto dostavljac;

    public PorudzbineDto(Porudzbine p){
        this.UUID = p.getUUID();
        this.vremePorudzbine = p.getVremePorudzbine();
        this.kupac = new ImePrzDto(p.getKupac().getIme(), p.getKupac().getPrezime());
        this.dostavljac = new ImePrzDto(p.getDostavljac().getIme(), p.getDostavljac().getPrezime());
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public Date getVremePorudzbine() {
        return vremePorudzbine;
    }

    public void setVremePorudzbine(Date vremePorudzbine) {
        this.vremePorudzbine = vremePorudzbine;
    }

    public ImePrzDto getKupac() {
        return kupac;
    }

    public void setKupac(ImePrzDto kupac) {
        this.kupac = kupac;
    }

    public ImePrzDto getDostavljac() {
        return dostavljac;
    }

    public void setDostavljac(ImePrzDto dostavljac) {
        this.dostavljac = dostavljac;
    }
}
