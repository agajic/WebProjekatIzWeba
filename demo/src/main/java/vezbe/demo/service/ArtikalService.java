package vezbe.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vezbe.demo.dto.KreiranjeArtiklaDto;
import vezbe.demo.model.Artikal;
import vezbe.demo.model.Menadzer;
import vezbe.demo.model.Restoran;
import vezbe.demo.repository.ArtikalRepository;
import vezbe.demo.repository.MenadzerRepository;
import vezbe.demo.repository.RestoranRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ArtikalService {
    @Autowired
    private ArtikalRepository artikalRepository;

    @Autowired
    private RestoranRepository restoranRepository;

    public Artikal getArtikalByNaziv(String naziv, Long idRestorana){
        Restoran r = restoranRepository.getById(idRestorana);
        Set<Artikal> artikals = r.getArtikliUPonudi();
        for(Artikal a : artikals){
            if(a.getNaziv().equals(naziv))
                return a;
        }
        return null;
    }
}
