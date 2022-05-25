package vezbe.demo.dto;

import vezbe.demo.model.Artikal;
import vezbe.demo.model.Restoran;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class RestoranPrikazDto {

    private String naziv;

    private String tip;

    private String lokacija;

    private Restoran.StatusRestorana status;

    private Set<ArtikalDto> listaArtikala;

    public RestoranPrikazDto(Restoran r, Set<ArtikalDto> artikli){
        this.naziv = r.getNaziv();
        this.tip = r.getTipRestorana();
        this.lokacija = r.getLokacija().getAdresa();
        this.status = r.getStatus();
        listaArtikala = artikli;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getLokacija() {
        return lokacija;
    }

    public void setLokacija(String lokacija) {
        this.lokacija = lokacija;
    }

    public Set<ArtikalDto> getListaArtikala() {
        return listaArtikala;
    }

    public void setListaArtikala(Set<ArtikalDto> listaArtikala) {
        this.listaArtikala = listaArtikala;
    }

    public Restoran.StatusRestorana getStatus() {
        return status;
    }

    public void setStatus(Restoran.StatusRestorana status) {
        this.status = status;
    }
}
