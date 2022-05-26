package vezbe.demo.dto;

import vezbe.demo.model.Porudzbine;

import java.sql.Date;

public class PorudzbineKupcaDto {
    private String UUID;

    private Date vremePorudzbine;

    private ImePrzDto dostavljac;

    private String nazivRestorana;

    private Porudzbine.Status status;

    public PorudzbineKupcaDto(Porudzbine p){
        this.UUID = p.getUUID();
        this.vremePorudzbine = p.getVremePorudzbine();
        this.dostavljac = new ImePrzDto(p.getDostavljac().getIme(), p.getDostavljac().getPrezime());
        this.nazivRestorana = p.getMesto().getNaziv();
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

    public ImePrzDto getDostavljac() {
        return dostavljac;
    }

    public void setDostavljac(ImePrzDto dostavljac) {
        this.dostavljac = dostavljac;
    }

    public String getNazivRestorana() {
        return nazivRestorana;
    }

    public void setNazivRestorana(String nazivRestorana) {
        this.nazivRestorana = nazivRestorana;
    }

    public Porudzbine.Status getStatus() {
        return status;
    }

    public void setStatus(Porudzbine.Status status) {
        this.status = status;
    }
}
