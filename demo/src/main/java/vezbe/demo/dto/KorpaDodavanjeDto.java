package vezbe.demo.dto;

public class KorpaDodavanjeDto {
    private String nazivArtikla;
    private int kolicina;

    public KorpaDodavanjeDto(){}

    public KorpaDodavanjeDto(String nazivArtikla, int kolicina){
        this.nazivArtikla = nazivArtikla;
        this.kolicina = kolicina;
    }

    public String getNazivArtikla() {
        return nazivArtikla;
    }

    public void setNazivArtikla(String nazivArtikla) {
        this.nazivArtikla = nazivArtikla;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }
}
