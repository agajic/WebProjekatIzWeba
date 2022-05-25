package vezbe.demo.dto;

import vezbe.demo.model.Restoran;

public class KreiranjeRestoranaDto {

    private String naziv;
    private String tipRestorana;
    private String adresa;
    private Restoran.StatusRestorana status;

    public KreiranjeRestoranaDto(){}

    public KreiranjeRestoranaDto(String naziv, String tipRestorana, String adresa, Restoran.StatusRestorana status){
        this.naziv = naziv;
        this.tipRestorana = tipRestorana;
        this.adresa = adresa;
        this.status = status;
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

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public Restoran.StatusRestorana getStatus() {
        return status;
    }

    public void setStatus(Restoran.StatusRestorana status) {
        this.status = status;
    }
}
