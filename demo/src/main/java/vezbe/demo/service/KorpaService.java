package vezbe.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vezbe.demo.model.*;
import vezbe.demo.repository.KorpaRepository;
import vezbe.demo.repository.KupacRepository;
import vezbe.demo.repository.PorudzbineRepository;
import vezbe.demo.repository.RestoranRepository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class KorpaService {
    @Autowired
    private KorpaRepository korpaRepository;

    @Autowired
    private KupacRepository kupacRepository;

    @Autowired
    private RestoranRepository restoranRepository;

    @Autowired
    private PorudzbineRepository porudzbineRepository;

    public boolean addArtikalUKorpu(Artikal artikal, Long idKupac, int kolicina, Long idRestoran){
        Kupac kupac = kupacRepository.getById(idKupac);
        if(kolicina < 1)
            return false;
        Restoran r = restoranRepository.getById(idRestoran);

        //Provera da li je poruceno iz nekog drugog restorana
        List<Korpa> aktivnaKopa = getAktivnaKorpa(idKupac);
        for(Korpa k : aktivnaKopa){
            if(k.getRestoran().getId() != idRestoran)
                return false;
        }

        Korpa k = new Korpa(artikal, kolicina, kolicina*artikal.getCena(), kupac, r);
        korpaRepository.save(k);
        return true;
    }

    public boolean deleteArtikalIzKorpe(Long idKupac, Long idKorpa){
        Optional<Korpa> findK = korpaRepository.findById(idKorpa);
        if(!findK.isPresent())
            return false;
        Korpa korpa = korpaRepository.getById(idKorpa);
        if(korpa.getKupac().getId() != idKupac)
            return false;
        korpaRepository.delete(korpa);
        return true;
    }

    public Korpa getKorpaById(Long id){
        Optional<Korpa> findK = korpaRepository.findById(id);
        if(!findK.isPresent())
            return null;
        Korpa korpa = korpaRepository.getById(id);
        return korpa;
    }

    public boolean smanjiKolicinu(Long idKorpa){
        Korpa korpa = korpaRepository.getById(idKorpa);
        if(korpa.getKolicina() == 1)
            return false;
        korpa.setKolicina(korpa.getKolicina()-1);
        korpaRepository.save(korpa);
        return true;
    }

    public List<Korpa> getAktivnaKorpa(Long idKupac){
        List<Korpa> korpa = korpaRepository.findAll();
        List<Korpa> aktivnaKorpa = new ArrayList<>();
        for(Korpa k : korpa){
            if(k.getKupac().getId() == idKupac && k.getPorudzbine() == null)
                aktivnaKorpa.add(k);
        }
        return aktivnaKorpa;
    }

    public boolean poruci(Long idKupac){
        Kupac kupac = kupacRepository.getById(idKupac);
        List<Korpa> aktivnaKorpa = getAktivnaKorpa(idKupac);
        if(aktivnaKorpa.isEmpty())
            return false;
        Korpa korpa = new Korpa();
        int ukupnaCena = 0;
        for(Korpa k : aktivnaKorpa){
            ukupnaCena += k.getCenaArtikla();
            korpa = k;
        }

        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

        Porudzbine p = new Porudzbine("4356345346", korpa.getRestoran(), sqlDate, ukupnaCena, kupac, Porudzbine.Status.OBRADA);
        for(Korpa k : aktivnaKorpa){
            p.getPoruceniArtikli().add(k.getArtikal());
        }
        porudzbineRepository.save(p);

        Restoran r = korpa.getRestoran();
        r.getPorudzbine().add(p);
        restoranRepository.save(r);

        korpaUpdate(p, idKupac);
        return true;
    }

    public void korpaUpdate(Porudzbine p, Long idKupac){
        List<Korpa> aktivnaKorpa = getAktivnaKorpa(idKupac);
        for(Korpa k : aktivnaKorpa){
            k.setPorudzbine(p);
            korpaRepository.save(k);
        }
    }
}
