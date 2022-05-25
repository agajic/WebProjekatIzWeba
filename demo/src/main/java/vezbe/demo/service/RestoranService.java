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

    public List<Restoran> findAll(){
        return restoranRepository.findAll();
    }

    public List<Restoran> findByNaziv(String naziv){
        List<Restoran> lista = restoranRepository.findAll();
        List<Restoran> listaByNaziv = new ArrayList<>();
        for(Restoran r : lista){
            if(r.getNaziv().equals(naziv)){
                listaByNaziv.add(r);
            }
        }
        return listaByNaziv;
    }

    public List<Restoran> findByTip(String tip){
        List<Restoran> lista = restoranRepository.findAll();
        List<Restoran> listaByTip = new ArrayList<>();
        for(Restoran r : lista){
            if(r.getTipRestorana().equals(tip)){
                listaByTip.add(r);
            }
        }
        return listaByTip;
    }

    public List<Restoran> findByAdresa(String adresa){
        List<Restoran> lista = restoranRepository.findAll();
        List<Restoran> listaByAdresa = new ArrayList<>();
        for(Restoran r : lista){
            if(r.getLokacija().getAdresa().equals(adresa)){
                listaByAdresa.add(r);
            }
        }
        return listaByAdresa;
    }

    public Restoran findById(Long id){
        Optional<Restoran> r = restoranRepository.findById(id);
        if(r.isPresent()){
            return r.get();
        }
        return null;
    }

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
