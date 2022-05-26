package vezbe.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vezbe.demo.model.Dostavljac;
import vezbe.demo.model.Porudzbine;
import vezbe.demo.model.Restoran;
import vezbe.demo.repository.DostavljacRepository;
import vezbe.demo.repository.PorudzbineRepository;

import java.util.*;

@Service
public class PorudzbineService {
    @Autowired
    private PorudzbineRepository porudzbineRepository;

    @Autowired
    private DostavljacRepository dostavljacRepository;

    public List<Porudzbine> getPorudzbineByRestoranId(Long id){
        List<Porudzbine> porudzbine = porudzbineRepository.findAll();
        List<Porudzbine> lista = new ArrayList<>();
        for(Porudzbine p : porudzbine){
            if(p.getMesto().getId() == id){
                lista.add(p);
            }
        }
        return lista;
    }

    public Set<Porudzbine> getPorudzbineUCekanju(){
        List<Porudzbine> porudzbines = porudzbineRepository.findAll();
        Set<Porudzbine> setUCekanju = new HashSet<>();
        for(Porudzbine p : porudzbines){
            if(p.getStatus() == Porudzbine.Status.CEKA_DOSTAVLJACA)
                setUCekanju.add(p);
        }
        return setUCekanju;
    }

    public Porudzbine getPorudzbinaById(Long id){
        Optional<Porudzbine> p = porudzbineRepository.findById(id);
        if(p.isPresent()){
            return p.get();
        }
        return null;
    }

    public void updateStauts(Porudzbine.Status status, Long id){
        Porudzbine p = getPorudzbinaById(id);
        p.setStatus(status);
        porudzbineRepository.save(p);
    }

    public void setDostaljacPorudzbine(Long idDostavljac, Long idPorudzbine){
        Dostavljac d = dostavljacRepository.getById(idDostavljac);
        Porudzbine p = getPorudzbinaById(idPorudzbine);
        p.setDostavljac(d);
        porudzbineRepository.save(p);
        d.getPorudzbineZaDostavu().add(p);
        dostavljacRepository.save(d);
    }
}
