package vezbe.demo.dto;

import vezbe.demo.model.Korpa;

import java.util.List;

public class KorpaPregledDto {
    private List<KorpaIspisArtiklaDto> artikli;
    private double ukupnaCena;

    public KorpaPregledDto(){}
    public KorpaPregledDto(List<KorpaIspisArtiklaDto> artikli, double ukupnaCena){
        this.artikli = artikli;
        this.ukupnaCena = ukupnaCena;
    }

    public List<KorpaIspisArtiklaDto> getArtikli() {
        return artikli;
    }

    public void setArtikli(List<KorpaIspisArtiklaDto> artikli) {
        this.artikli = artikli;
    }

    public double getUkupnaCena() {
        return ukupnaCena;
    }

    public void setUkupnaCena(double ukupnaCena) {
        this.ukupnaCena = ukupnaCena;
    }
}
