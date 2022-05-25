package vezbe.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vezbe.demo.dto.KreiranjeRestoranaDto;
import vezbe.demo.model.Lokacija;
import vezbe.demo.model.Menadzer;
import vezbe.demo.model.Restoran;
import vezbe.demo.repository.LokacijaRepository;
import vezbe.demo.repository.RestoranRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RestoranService {
    @Autowired
    private RestoranRepository restoranRepository;

    @Autowired
    private LokacijaRepository lokacijaRepository;

    public boolean saveRestoran(KreiranjeRestoranaDto res){
        List<Restoran> lista = restoranRepository.findAll();
        for(Restoran r : lista){
            if(r.getNaziv().equals(res.getNaziv()))
                return false;
            if(r.getLokacija().getAdresa().equals(res.getAdresa()))
                return false;
        }
        Lokacija l = new Lokacija(res.getAdresa());
        lokacijaRepository.save(l);

        Restoran r = new Restoran(res.getNaziv(), res.getTipRestorana(), res.getStatus());
        r.setLokacija(l);

        restoranRepository.save(r);
        return true;
    }
}
