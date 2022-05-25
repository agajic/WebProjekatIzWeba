package vezbe.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vezbe.demo.dto.KorisnikDto;
import vezbe.demo.dto.KorisnikUpdateDto;
import vezbe.demo.model.Korisnik;
import vezbe.demo.model.Kupac;
import vezbe.demo.repository.KorsnikRepository;
import vezbe.demo.repository.KupacRepository;

import java.util.List;
import java.util.Optional;

@Service
public class KorisnikService {

    @Autowired
    private KorsnikRepository korisnikRepository;

    @Autowired
    private KupacRepository kupacRepository;

    public List<Korisnik> findAll(){
        return korisnikRepository.findAll();
    }

    public boolean register(String username, String password){
        List<Korisnik> korisnici = korisnikRepository.findAll();

        for(Korisnik k : korisnici){
            if(k.getKorisnicko_ime().equals(username)){
                return false;
            }
        }

        //Korisnik k = new Korisnik(username, password);
        Kupac k = new Kupac(username, password);
        k.setUloga(Korisnik.Uloga.KUPAC);
        //korisnikRepository.save(k);
        kupacRepository.save(k);

        return  true;
    }

    public Korisnik login(String username, String password){
        List<Korisnik> korisnici = korisnikRepository.findAll();

        for(Korisnik k : korisnici){
            if(k.getKorisnicko_ime().equals(username) && k.getLozinka().equals(password)){
                return k;
            }
        }
        return null;
    }

    public boolean update(Long id, KorisnikUpdateDto kdto){
        Optional<Korisnik> findK = korisnikRepository.findById(id);
        if(!findK.isPresent())
            return false;
        Korisnik k =  korisnikRepository.getById(id);
        k.setKorisnicko_ime(kdto.getKorisnicko_ime());
        k.setLozinka(kdto.getLozinka());
        k.setIme(kdto.getIme());
        k.setPrezime(kdto.getPrezime());
        k.setPol(kdto.getPol());
        k.setDatum(kdto.getDatum());
        korisnikRepository.save(k);
        return true;
    }

}
