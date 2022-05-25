package vezbe.demo.dto;

public class DodelaMenadzeraRestoranu {
    private Long idRestorana;
    private Long idMenadzera;

    public DodelaMenadzeraRestoranu(){}

    public DodelaMenadzeraRestoranu(Long idRestorana, Long idMenadzera){
        this.idRestorana = idRestorana;
        this.idMenadzera = idMenadzera;
    }

    public Long getIdRestorana() {
        return idRestorana;
    }

    public void setIdRestorana(Long idRestorana) {
        this.idRestorana = idRestorana;
    }

    public Long getIdMenadzera() {
        return idMenadzera;
    }

    public void setIdMenadzera(Long idMenadzera) {
        this.idMenadzera = idMenadzera;
    }
}
