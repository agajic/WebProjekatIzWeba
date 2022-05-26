package vezbe.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import vezbe.demo.dto.KorpaDodavanjeDto;
import vezbe.demo.dto.KorpaIspisArtiklaDto;
import vezbe.demo.dto.KorpaPregledDto;
import vezbe.demo.dto.PorudzbineKupcaDto;
import vezbe.demo.model.*;
import vezbe.demo.service.ArtikalService;
import vezbe.demo.service.KorpaService;
import vezbe.demo.service.KupacService;
import vezbe.demo.service.RestoranService;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class KupacController {
    @Autowired
    private KupacService kupacService;

    @Autowired
    private RestoranService restoranService;

    @Autowired
    private ArtikalService artikalService;

    @Autowired
    private KorpaService korpaService;

    @GetMapping("api/kupac")
    public ResponseEntity<Set<PorudzbineKupcaDto>> listaPorudzbina(HttpSession session){
        Korisnik k = (Korisnik) session.getAttribute("korisnik");
        if(k == null){
            return new ResponseEntity("Niste ulogovani", HttpStatus.FORBIDDEN);
        }
        else if(k.getUloga() != Korisnik.Uloga.KUPAC){
            return new ResponseEntity("Nemate pristup ovoj stranici", HttpStatus.FORBIDDEN);
        }

        Kupac kupac = kupacService.findKupacById(k.getId());
        Set<Porudzbine> porudzbines = kupac.getListaPorudzbina();

        if(porudzbines.isEmpty())
            return new ResponseEntity("Nije pronadjena nijedna porudzbina", HttpStatus.BAD_REQUEST);

        Set<PorudzbineKupcaDto> listaDto = new HashSet<>();
        for(Porudzbine p : porudzbines){
            PorudzbineKupcaDto pdto = new PorudzbineKupcaDto(p);
            listaDto.add(pdto);
        }
        return ResponseEntity.ok(listaDto);
    }

    @PostMapping("api/restoran/{idRestorana}/korpa/dodajUKorpu")
    public ResponseEntity<String> dodajUKorpu(@PathVariable(name = "idRestorana") Long idRestorana, @RequestBody KorpaDodavanjeDto artikalUKorpu, HttpSession session){
        Korisnik k = (Korisnik) session.getAttribute("korisnik");
        if(k == null){
            return new ResponseEntity("Niste ulogovani", HttpStatus.FORBIDDEN);
        }
        else if(k.getUloga() != Korisnik.Uloga.KUPAC){
            return new ResponseEntity("Nemate pristup ovoj stranici", HttpStatus.FORBIDDEN);
        }

        Restoran r = restoranService.findById(idRestorana);
        if(r.getStatus() == Restoran.StatusRestorana.NE_RADI)
            return new ResponseEntity("Restoran ne radi..", HttpStatus.BAD_REQUEST);

        Artikal a = artikalService.getArtikalByNaziv(artikalUKorpu.getNazivArtikla(), idRestorana);
        if(a == null)
            return new ResponseEntity("Artikal nije pronadjen..", HttpStatus.BAD_REQUEST);

        if(korpaService.addArtikalUKorpu(a, k.getId(), artikalUKorpu.getKolicina(), idRestorana))
            return ResponseEntity.ok("Uspesno ubaceno u korpu");
        return new ResponseEntity("Neispravna kolicina ili je poruceno iz drugog restorana", HttpStatus.BAD_REQUEST);

    }

    @DeleteMapping("api/restoran/{idRestorana}/korpa/izbaciIzKorpe/{idKorpa}")
    public ResponseEntity<String> izbaciIzKorpe(@PathVariable(name = "idRestorana") Long idRestorana, @PathVariable(name = "idKorpa") Long idKorpa, HttpSession session){
        Korisnik k = (Korisnik) session.getAttribute("korisnik");
        if(k == null){
            return new ResponseEntity("Niste ulogovani", HttpStatus.FORBIDDEN);
        }
        else if(k.getUloga() != Korisnik.Uloga.KUPAC){
            return new ResponseEntity("Nemate pristup ovoj stranici", HttpStatus.FORBIDDEN);
        }

        if(korpaService.deleteArtikalIzKorpe(k.getId(), idKorpa))
            return ResponseEntity.ok("Upseno izbaceno iz korpe");
        return new ResponseEntity("Doslo je do greske..", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("api/restoran/{idRestorana}/korpa/smanjiKolicinu/{idKorpa}")
    public ResponseEntity<String> smanjiKolicinu(@PathVariable(name = "idRestorana") Long idRestorana, @PathVariable(name = "idKorpa") Long idKorpa, HttpSession session){
        Korisnik k = (Korisnik) session.getAttribute("korisnik");
        if(k == null){
            return new ResponseEntity("Niste ulogovani", HttpStatus.FORBIDDEN);
        }
        else if(k.getUloga() != Korisnik.Uloga.KUPAC){
            return new ResponseEntity("Nemate pristup ovoj stranici", HttpStatus.FORBIDDEN);
        }

        Korpa korpa = korpaService.getKorpaById(idKorpa);
        if(korpa == null)
            return new ResponseEntity("Doslo je do greske..", HttpStatus.BAD_REQUEST);
        if(korpaService.smanjiKolicinu(idKorpa))
            return ResponseEntity.ok("Uspesno smanjena je kolicina za jedan");
        return new ResponseEntity("Kolicina ne sme biti manja od jedan", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("api/restoran/{idRestorana}/korpa/pregledKorpe")
    public ResponseEntity<KorpaPregledDto> pregledKorpe(@PathVariable(name = "idRestorana") Long idRestorana, HttpSession session){
        Korisnik k = (Korisnik) session.getAttribute("korisnik");
        if(k == null){
            return new ResponseEntity("Niste ulogovani", HttpStatus.FORBIDDEN);
        }
        else if(k.getUloga() != Korisnik.Uloga.KUPAC){
            return new ResponseEntity("Nemate pristup ovoj stranici", HttpStatus.FORBIDDEN);
        }

        List<Korpa> aktivnaKorpa = korpaService.getAktivnaKorpa(k.getId());
        if(aktivnaKorpa.isEmpty())
            return new ResponseEntity("Korpa je prazna", HttpStatus.BAD_REQUEST);

        List<KorpaIspisArtiklaDto> aktivnaKorpaDto = new ArrayList<>();
        int ukupnaCena = 0;
        for(Korpa ko : aktivnaKorpa){
            KorpaIspisArtiklaDto kodto = new KorpaIspisArtiklaDto(ko);
            aktivnaKorpaDto.add(kodto);
            ukupnaCena += ko.getCenaArtikla();
        }

        KorpaPregledDto korpaPregledDto = new KorpaPregledDto(aktivnaKorpaDto, ukupnaCena);
        return ResponseEntity.ok(korpaPregledDto);
    }

    @PutMapping("api/restoran/{idRestorana}/korpa/poruci")
    public ResponseEntity<String> poruci(@PathVariable(name = "idRestorana") Long idRestorana, HttpSession session){
        Korisnik k = (Korisnik) session.getAttribute("korisnik");
        if(k == null){
            return new ResponseEntity("Niste ulogovani", HttpStatus.FORBIDDEN);
        }
        else if(k.getUloga() != Korisnik.Uloga.KUPAC){
            return new ResponseEntity("Nemate pristup ovoj stranici", HttpStatus.FORBIDDEN);
        }

        if(korpaService.poruci(k.getId()))
            return ResponseEntity.ok("Uspesno ste porucili");
        return new ResponseEntity("Korpa je prazna", HttpStatus.BAD_REQUEST);
    }
}
