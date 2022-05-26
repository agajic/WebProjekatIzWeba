package vezbe.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vezbe.demo.model.Porudzbine;
import vezbe.demo.repository.PorudzbineRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PorudzbineService {
    @Autowired
    private PorudzbineRepository porudzbineRepository;

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
}
