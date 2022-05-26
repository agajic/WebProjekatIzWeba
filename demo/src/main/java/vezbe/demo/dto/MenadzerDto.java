package vezbe.demo.dto;

import vezbe.demo.model.Menadzer;
import vezbe.demo.model.Porudzbine;
import vezbe.demo.model.Restoran;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MenadzerDto {

    private Restoran zaduzen;

    private List<PorudzbineDto> porudzbine;

    public MenadzerDto(Menadzer m, List<PorudzbineDto> porudzbine){
        this.zaduzen = m.getZaduzen();
        this.porudzbine = porudzbine;
    }

    public Restoran getZaduzen() {
        return zaduzen;
    }

    public void setZaduzen(Restoran zaduzen) {
        this.zaduzen = zaduzen;
    }

    public List<PorudzbineDto> getPorudzbine() {
        return porudzbine;
    }

    public void setPorudzbine(List<PorudzbineDto> porudzbine) {
        this.porudzbine = porudzbine;
    }
}
