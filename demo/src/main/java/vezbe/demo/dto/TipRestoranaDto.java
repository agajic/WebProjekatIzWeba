package vezbe.demo.dto;

public class TipRestoranaDto {

    private String tipRestorana;

    public  TipRestoranaDto(){}

    public TipRestoranaDto(String tipRestorana){
        this.tipRestorana = tipRestorana;
    }

    public String getTipRestorana() {
        return tipRestorana;
    }

    public void setTipRestorana(String tipRestorana) {
        this.tipRestorana = tipRestorana;
    }
}
