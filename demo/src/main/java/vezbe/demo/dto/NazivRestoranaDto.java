package vezbe.demo.dto;

public class NazivRestoranaDto {
    private String naziv;

    public NazivRestoranaDto(String naziv){
        this.naziv = naziv;
    }

    public NazivRestoranaDto(){}


    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
}
