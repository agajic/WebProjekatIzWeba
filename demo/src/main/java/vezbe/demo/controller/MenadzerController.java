package vezbe.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import vezbe.demo.dto.KreiranjeArtiklaDto;
import vezbe.demo.dto.MenadzerDto;
import vezbe.demo.dto.PorudzbineDto;
import vezbe.demo.model.Korisnik;
import vezbe.demo.model.Menadzer;
import vezbe.demo.model.Porudzbine;
import vezbe.demo.service.ArtikalService;
import vezbe.demo.service.MenadzerService;
import vezbe.demo.service.PorudzbineService;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MenadzerController {
    @Autowired
    private MenadzerService menadzerService;

    @Autowired
    private PorudzbineService porudzbineService;

    @Autowired
    private ArtikalService artikalService;

    @GetMapping("api/menadzer")
    public ResponseEntity<MenadzerDto> menadzerInfo(HttpSession session){
        Korisnik k = (Korisnik) session.getAttribute("korisnik");
        if(k == null){
            return new ResponseEntity("Niste ulogovani", HttpStatus.FORBIDDEN);
        }
        else if(k.getUloga() != Korisnik.Uloga.MENADZER){
            return new ResponseEntity("Nemate pristup ovoj stranici", HttpStatus.FORBIDDEN);
        }

        Menadzer m = menadzerService.getMenadzerById(k.getId());
        List<Porudzbine> porudzbine = porudzbineService.getPorudzbineByRestoranId(m.getZaduzen().getId());

        List<PorudzbineDto> porudzbineDto = new ArrayList<>();
        for(Porudzbine p : porudzbine){
            PorudzbineDto pdto = new PorudzbineDto(p);
            porudzbineDto.add(pdto);
        }

        MenadzerDto mdto = new MenadzerDto(m, porudzbineDto);

        return ResponseEntity.ok(mdto);
    }

    @PostMapping("api/menadzer/addArtikal")
    public ResponseEntity<String> addArtikal(@RequestBody KreiranjeArtiklaDto artikalDto, HttpSession session){
        Korisnik k = (Korisnik) session.getAttribute("korisnik");
        if(k == null){
            return new ResponseEntity("Niste ulogovani", HttpStatus.FORBIDDEN);
        }
        else if(k.getUloga() != Korisnik.Uloga.MENADZER){
            return new ResponseEntity("Nemate pristup ovoj stranici", HttpStatus.FORBIDDEN);
        }

        if(artikalService.saveArtikal(artikalDto, k.getId()))
            return ResponseEntity.ok("Uspesno dodat artikal");
        return new ResponseEntity("Proverite ponovo podatke", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("api/menadzer/updateArtikal/{id}")
    public ResponseEntity<String> updateArtikal(@PathVariable(name = "id") Long idArtikla, @RequestBody KreiranjeArtiklaDto artikal, HttpSession session){
        Korisnik k = (Korisnik) session.getAttribute("korisnik");
        if(k == null){
            return new ResponseEntity("Niste ulogovani", HttpStatus.FORBIDDEN);
        }
        else if(k.getUloga() != Korisnik.Uloga.MENADZER){
            return new ResponseEntity("Nemate pristup ovoj stranici", HttpStatus.FORBIDDEN);
        }

        if(artikalService.updateArtikal(artikal, idArtikla, k.getId()))
            return ResponseEntity.ok("Uspesno izmenjene informacije o artiklu");
        return new ResponseEntity("Proverite ponovo podatke", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("api/menadzer/deleteArtikal/{id}")
    public ResponseEntity<String> deleteArtikal(@PathVariable(name = "id") Long idArtikla, HttpSession session){
        Korisnik k = (Korisnik) session.getAttribute("korisnik");
        if(k == null){
            return new ResponseEntity("Niste ulogovani", HttpStatus.FORBIDDEN);
        }
        else if(k.getUloga() != Korisnik.Uloga.MENADZER){
            return new ResponseEntity("Nemate pristup ovoj stranici", HttpStatus.FORBIDDEN);
        }

        if(artikalService.deleteArtikal(idArtikla, k.getId()))
            return ResponseEntity.ok("Uspesno izbrisan artikal");
        return new ResponseEntity("Proverite ponovo podatke", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("api/menadzer/promeniStatusUPripremi/{idPorudzbine}")
    public ResponseEntity<String> promeniStatusUPripremi(@PathVariable(name = "idPorudzbine") Long idPorudzbine, HttpSession session){
        Korisnik k = (Korisnik) session.getAttribute("korisnik");
        if(k == null){
            return new ResponseEntity("Niste ulogovani", HttpStatus.FORBIDDEN);
        }
        else if(k.getUloga() != Korisnik.Uloga.MENADZER){
            return new ResponseEntity("Nemate pristup ovoj stranici", HttpStatus.FORBIDDEN);
        }


        Porudzbine p = porudzbineService.getPorudzbinaById(idPorudzbine);
        if(p == null)
            return new ResponseEntity("Ne postoji porudzbina", HttpStatus.BAD_REQUEST);
        if(p.getStatus() != Porudzbine.Status.OBRADA)
            return new ResponseEntity("Status porudzbine nije: OBRADA", HttpStatus.BAD_REQUEST);


        porudzbineService.updateStauts(Porudzbine.Status.U_PRIPREMI, idPorudzbine);

        return ResponseEntity.ok("Uspesno azuriran status. Status = U_PRIPREMI");
    }

    @PutMapping("api/menadzer/promeniStatusUCekaDostavljaca/{idPorudzbine}")
    public ResponseEntity<String> promeniStatusUCekaDostavljaca(@PathVariable(name = "idPorudzbine") Long idPorudzbine, HttpSession session){
        Korisnik k = (Korisnik) session.getAttribute("korisnik");
        if(k == null){
            return new ResponseEntity("Niste ulogovani", HttpStatus.FORBIDDEN);
        }
        else if(k.getUloga() != Korisnik.Uloga.MENADZER){
            return new ResponseEntity("Nemate pristup ovoj stranici", HttpStatus.FORBIDDEN);
        }


        Porudzbine p = porudzbineService.getPorudzbinaById(idPorudzbine);
        if(p == null)
            return new ResponseEntity("Ne postoji porudzbina", HttpStatus.BAD_REQUEST);
        if(p.getStatus() != Porudzbine.Status.U_PRIPREMI)
            return new ResponseEntity("Status porudzbine nije: U_PRIPREMI", HttpStatus.BAD_REQUEST);


        porudzbineService.updateStauts(Porudzbine.Status.CEKA_DOSTAVLJACA, idPorudzbine);

        return ResponseEntity.ok("Uspesno azuriran status. Status = CEKA_DOSTAVLJACA");
    }

}
