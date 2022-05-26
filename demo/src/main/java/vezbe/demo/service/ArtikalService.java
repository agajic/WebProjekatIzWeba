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
    private MenadzerRepository menadzerRepository;

    @Autowired
    private RestoranRepository restoranRepository;

    public boolean saveArtikal(KreiranjeArtiklaDto adto, Long idMenadzera){
        List<Artikal> artikals = artikalRepository.findAll();
        for(Artikal a : artikals){
            if(a.getNaziv().equals(adto.getNaziv()))
                return false;
        }
        Artikal a = new Artikal(adto.getNaziv(), adto.getCena(), adto.getTipArtikla());

        Menadzer m = menadzerRepository.getById(idMenadzera);
        a.getRestorani().add(m.getZaduzen());
        artikalRepository.save(a);
        m.getZaduzen().getArtikliUPonudi().add(a);

        restoranRepository.save(m.getZaduzen());

        return true;
    }

    public boolean updateArtikal(KreiranjeArtiklaDto adto, Long idArtikla, Long idMenadzera){
        Optional<Artikal> findA = artikalRepository.findById(idArtikla);
        if(!findA.isPresent())
            return false;
        Artikal a = artikalRepository.getById(idArtikla);
        Menadzer m = menadzerRepository.getById(idMenadzera);
        Set<Artikal> listaArtikalaURestorana = m.getZaduzen().getArtikliUPonudi();
        for(Artikal artikal : listaArtikalaURestorana){
            if(artikal.getId() == idArtikla){
                a.setNaziv(adto.getNaziv());
                a.setCena(adto.getCena());
                a.setTipArtikla(adto.getTipArtikla());
                artikalRepository.save(a);
                return true;
            }
        }
        return false;
    }

    public boolean deleteArtikal(Long idArtikla, Long idMenadzera){
        Optional<Artikal> findA = artikalRepository.findById(idArtikla);
        if(!findA.isPresent())
            return false;
        Artikal a = artikalRepository.getById(idArtikla);
        Menadzer m = menadzerRepository.getById(idMenadzera);
        Set<Artikal> listaArtikalaURestorana = m.getZaduzen().getArtikliUPonudi();
        for(Artikal artikal : listaArtikalaURestorana){
            if(artikal.getId() == idArtikla) {
                m.getZaduzen().getArtikliUPonudi().remove(artikal);
                //IZBRISATI IZ PORUDZBINA ARTIKAL
                artikalRepository.deleteById(idArtikla);
                return true;
            }
        }
        return false;
    }

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
