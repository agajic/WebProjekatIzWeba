package vezbe.demo.dto;

public class AdresaRestoranaDto {

    private String adresa;

    public AdresaRestoranaDto(){}

    public AdresaRestoranaDto(String adresa){
        this.adresa = adresa;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }
}
