package vezbe.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vezbe.demo.dto.KreiranjeMenadzeraDostavljacaDto;
import vezbe.demo.model.Dostavljac;
import vezbe.demo.model.Kupac;
import vezbe.demo.model.Menadzer;
import vezbe.demo.repository.DostavljacRepository;

import java.util.List;

@Service
public class DostavljacService {
    @Autowired
    private DostavljacRepository dostavljacRepository;

    public boolean save(KreiranjeMenadzeraDostavljacaDto ddto){
        List<Dostavljac> dostavljaci = dostavljacRepository.findAll();

        for(Dostavljac da : dostavljaci){
            if(da.getKorisnicko_ime().equals(ddto.getKorisnicko_ime())){
                return false;
            }
        }
        Dostavljac d = new Dostavljac(ddto.getKorisnicko_ime(), ddto.getLozinka(), ddto.getIme(), ddto.getPrezime(), ddto.getPol(), ddto.getDatum(), ddto.getUloga());
        dostavljacRepository.save(d);
        return true;
    }

    public Dostavljac findDostavljacById(Long id){
        return dostavljacRepository.getById(id);
    }
}
