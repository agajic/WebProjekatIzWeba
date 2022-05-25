package vezbe.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vezbe.demo.dto.KreiranjeMenadzeraDostavljacaDto;
import vezbe.demo.model.Menadzer;
import vezbe.demo.model.Restoran;
import vezbe.demo.repository.MenadzerRepository;
import vezbe.demo.repository.RestoranRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MenadzerService {

    @Autowired
    private MenadzerRepository menadzerRepository;

    @Autowired
    private RestoranRepository restoranRepository;

    public Menadzer getMenadzerById(Long id){
        Menadzer m = menadzerRepository.getById(id);
        return m;
    }

    public boolean save(KreiranjeMenadzeraDostavljacaDto mdto){
        List<Menadzer> menadzeri = menadzerRepository.findAll();

        for(Menadzer ma : menadzeri){
            if(ma.getKorisnicko_ime().equals(mdto.getKorisnicko_ime())){
                return false;
            }
        }
        Menadzer m = new Menadzer(mdto.getKorisnicko_ime(), mdto.getLozinka(), mdto.getIme(), mdto.getPrezime(), mdto.getPol(), mdto.getDatum(), mdto.getUloga());
        menadzerRepository.save(m);
        return true;
    }

    public boolean dodeliRestoran(Long idRestorana, Long idMenadzera){
        Optional<Menadzer> findM = menadzerRepository.findById(idMenadzera);

        if(!findM.isPresent())
            return false;

        Optional<Restoran> findR = restoranRepository.findById(idRestorana);
        if(!findR.isPresent())
            return  false;

        Menadzer m = menadzerRepository.getById(idMenadzera);
        Restoran r = restoranRepository.getById(idRestorana);

        if(m.getZaduzen() != null)
            return false;

        m.setZaduzen(r);
        menadzerRepository.save(m);
        return true;
    }

}
