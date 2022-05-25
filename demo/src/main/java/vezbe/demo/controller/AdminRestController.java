package vezbe.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vezbe.demo.dto.*;
import vezbe.demo.model.*;
import vezbe.demo.repository.DostavljacRepository;
import vezbe.demo.service.DostavljacService;
import vezbe.demo.service.KorisnikService;
import vezbe.demo.service.MenadzerService;
import vezbe.demo.service.RestoranService;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
public class AdminRestController {

    @Autowired
    private KorisnikService korisnikService;

    @Autowired
    private MenadzerService menadzerService;

    @Autowired
    private DostavljacService dostavljacService;

    @Autowired
    private RestoranService restoranService;

    @GetMapping("api/admin")
    public ResponseEntity<List<KorisnikDto>> test(HttpSession session){
        //Admin a = (Admin) session.getAttribute("korisnik");
        Korisnik k = (Korisnik) session.getAttribute("korisnik");
        if(k == null){
            return new ResponseEntity("Niste ulogovani", HttpStatus.FORBIDDEN);
        }
        else if(k.getUloga() != Korisnik.Uloga.ADMIN){
            return new ResponseEntity("Nemate pristup ovoj stranici", HttpStatus.FORBIDDEN);
        }

        List<Korisnik> korisnici = korisnikService.findAll();

        List<KorisnikDto> korisniciDto = new ArrayList<>();
        for(Korisnik ko : korisnici){
            KorisnikDto kdto = new KorisnikDto(ko);
            korisniciDto.add(kdto);
        }

        return ResponseEntity.ok(korisniciDto);
    }

    @PostMapping("api/admin/addMenadzer")
    public ResponseEntity<String> addMenadzer(@RequestBody KreiranjeMenadzeraDostavljacaDto menadzer, HttpSession session){ //mora dto
        Korisnik k = (Korisnik) session.getAttribute("korisnik");
        if(k == null){
            return new ResponseEntity("Niste ulogovani", HttpStatus.FORBIDDEN);
        }
        else if(k.getUloga() != Korisnik.Uloga.ADMIN){
            return new ResponseEntity("Nemate pristup ovoj stranici", HttpStatus.FORBIDDEN);
        }

        if(menadzer.getUloga() != Korisnik.Uloga.MENADZER)
            return new ResponseEntity("Uloga je neispravna", HttpStatus.BAD_REQUEST);

        if(menadzerService.save(menadzer))
             return ResponseEntity.ok("Uspesno kreiran menadzer");
        return new ResponseEntity("Vec postoji menadzer sa slicnim podacima", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("api/admin/addDostavljac")
    public ResponseEntity<String> addDostavljac(@RequestBody KreiranjeMenadzeraDostavljacaDto dostavljac, HttpSession session){
        Korisnik k = (Korisnik) session.getAttribute("korisnik");
        if(k == null){
            return new ResponseEntity("Niste ulogovani", HttpStatus.FORBIDDEN);
        }
        else if(k.getUloga() != Korisnik.Uloga.ADMIN){
            return new ResponseEntity("Nemate pristup ovoj stranici", HttpStatus.FORBIDDEN);
        }

        if(dostavljac.getUloga() != Korisnik.Uloga.DOSTAVLJAC)
            return new ResponseEntity("Uloga je neispravna", HttpStatus.BAD_REQUEST);

        if(dostavljacService.save(dostavljac))
            return ResponseEntity.ok("Uspesno kreiran dostavljac");
        return new ResponseEntity("Vec postoji dostavljac sa slicnim podacima", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("api/admin/addRestoran")
    public ResponseEntity<String> addRestoran(@RequestBody KreiranjeRestoranaDto resDto, HttpSession session){
        Korisnik k = (Korisnik) session.getAttribute("korisnik");
        if(k == null){
            return new ResponseEntity("Niste ulogovani", HttpStatus.FORBIDDEN);
        }
        else if(k.getUloga() != Korisnik.Uloga.ADMIN){
            return new ResponseEntity("Nemate pristup ovoj stranici", HttpStatus.FORBIDDEN);
        }

        if(restoranService.saveRestoran(resDto))
            return ResponseEntity.ok("Uspesno kreiran restoran");
        return new ResponseEntity("Vec postoji restoran sa slicnim podacima", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("api/admin/dodeliMenadzera")
    public ResponseEntity<String> dodeliMenadzera(@RequestBody DodelaMenadzeraRestoranu ids, HttpSession session){
        Korisnik k = (Korisnik) session.getAttribute("korisnik");
        if(k == null){
            return new ResponseEntity("Niste ulogovani", HttpStatus.FORBIDDEN);
        }
        else if(k.getUloga() != Korisnik.Uloga.ADMIN){
            return new ResponseEntity("Nemate pristup ovoj stranici", HttpStatus.FORBIDDEN);
        }

        if(menadzerService.dodeliRestoran(ids.getIdRestorana(), ids.getIdMenadzera()))
            return ResponseEntity.ok("Uspesno dodeljen menadzer restoranu");
        return new ResponseEntity("Proverite ponovo podatke", HttpStatus.BAD_REQUEST);
    }
}
