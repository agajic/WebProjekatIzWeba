package vezbe.demo.dto;

import vezbe.demo.model.Lokacija;
import vezbe.demo.model.Restoran;

public class RestoranPocetnaDto {

    private String naziv;

    private String tipRestorana;

    private Lokacija lokacija;

    private Restoran.StatusRestorana status;

    public RestoranPocetnaDto(Restoran r){
        this.naziv = r.getNaziv();
        this.tipRestorana = r.getTipRestorana();
        this.lokacija = r.getLokacija();
        this.status = r.getStatus();
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

    public Restoran.StatusRestorana getStatus() {
        return status;
    }

    public void setStatus(Restoran.StatusRestorana status) {
        this.status = status;
    }
}
