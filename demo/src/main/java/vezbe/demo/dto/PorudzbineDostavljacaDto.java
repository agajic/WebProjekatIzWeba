package vezbe.demo.dto;

import vezbe.demo.model.Porudzbine;

import java.sql.Date;

public class PorudzbineDostavljacaDto {
    private String UUID;

    private Date vremePorudzbine;

    private ImePrzDto kupac;

    private Porudzbine.Status status;

    public PorudzbineDostavljacaDto(Porudzbine p){
        this.UUID = p.getUUID();
        this.vremePorudzbine = p.getVremePorudzbine();
        this.kupac = new ImePrzDto(p.getKupac().getIme(), p.getKupac().getPrezime());
        this.status = p.getStatus();
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

    public Porudzbine.Status getStatus() {
        return status;
    }

    public void setStatus(Porudzbine.Status status) {
        this.status = status;
    }
}
