package vezbe.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vezbe.demo.model.Kupac;
import vezbe.demo.model.Porudzbine;
import vezbe.demo.repository.KupacRepository;

import java.util.List;

@Service
public class KupacService {

    @Autowired
    private KupacRepository kupacRepository;

    public List<Kupac> findAll(){
        return kupacRepository.findAll();
    }

    public Kupac findKupacById(Long id){
        return kupacRepository.getById(id);
    }

    public void updateKupacBodovi(Porudzbine p){
        Kupac k = kupacRepository.getById(p.getKupac().getId());
        int brojBodova = (int)(p.getCena()/1000 * 133);
        k.setBodovi(k.getBodovi() + brojBodova);
        kupacRepository.save(k);
    }
}
