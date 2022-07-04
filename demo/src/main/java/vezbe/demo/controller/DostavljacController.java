package vezbe.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import vezbe.demo.dto.PorudzbineDostavljacaDto;
import vezbe.demo.model.Dostavljac;
import vezbe.demo.model.Korisnik;
import vezbe.demo.model.Porudzbine;
import vezbe.demo.service.DostavljacService;
import vezbe.demo.service.KupacService;
import vezbe.demo.service.PorudzbineService;

import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Set;

@Controller
public class DostavljacController {
    @Autowired
    private DostavljacService dostavljacService;

    @Autowired
    private PorudzbineService porudzbineService;

    @Autowired
    private KupacService kupacService;

    @GetMapping("api/dostavljac")
    public ResponseEntity<Set<PorudzbineDostavljacaDto>> listaPorudzbina(HttpSession session){
        Korisnik k = (Korisnik) session.getAttribute("korisnik");
        if(k == null){
            return new ResponseEntity("Niste ulogovani", HttpStatus.FORBIDDEN);
        }
        else if(k.getUloga() != Korisnik.Uloga.DOSTAVLJAC){
            return new ResponseEntity("Nemate pristup ovoj stranici", HttpStatus.FORBIDDEN);
        }

        Dostavljac d = dostavljacService.findDostavljacById(k.getId());
        Set<Porudzbine> porudzbines = d.getPorudzbineZaDostavu();      //LISTA KOJE DOSTAVLJAC TREBA DA DOSTAVI
        Set<PorudzbineDostavljacaDto> porudzbineDto = new HashSet<>(); //GLAVNA VELIKA LISTA
        for(Porudzbine p : porudzbines){
            PorudzbineDostavljacaDto pdto = new PorudzbineDostavljacaDto(p);
            porudzbineDto.add(pdto);
        }
        Set<Porudzbine> porudzbineUCekanju = porudzbineService.getPorudzbineUCekanju();  //LISTA KOJE DOSTAVLJAC MOZE DA PREUZME
        for(Porudzbine p : porudzbineUCekanju){
            PorudzbineDostavljacaDto pdto = new PorudzbineDostavljacaDto(p);
            porudzbineDto.add(pdto);
        }

        if(porudzbineDto.isEmpty())
            return new ResponseEntity("Nema trenutno porudzbina", HttpStatus.BAD_REQUEST);
        return ResponseEntity.ok(porudzbineDto);
    }

    @PutMapping("api/dostavljac/promeniStatusUTransportu/{idPorudzbine}")
    public ResponseEntity<String> promeniStatusUTransportu(@PathVariable(name = "idPorudzbine") Long idPorudzbine, HttpSession session){
        Korisnik k = (Korisnik) session.getAttribute("korisnik");
        if(k == null){
            return new ResponseEntity("Niste ulogovani", HttpStatus.FORBIDDEN);
        }
        else if(k.getUloga() != Korisnik.Uloga.DOSTAVLJAC){
            return new ResponseEntity("Nemate pristup ovoj stranici", HttpStatus.FORBIDDEN);
        }


        Porudzbine p = porudzbineService.getPorudzbinaById(idPorudzbine);
        if(p == null)
            return new ResponseEntity("Ne postoji porudzbina", HttpStatus.BAD_REQUEST);
        if(p.getStatus() != Porudzbine.Status.CEKA_DOSTAVLJACA)
            return new ResponseEntity("Status porudzbine nije: CEKA_DOSTAVLJACA", HttpStatus.BAD_REQUEST);


        porudzbineService.updateStauts(Porudzbine.Status.U_TRANSPORTU, idPorudzbine);
        porudzbineService.setDostaljacPorudzbine(k.getId(), idPorudzbine);

        return ResponseEntity.ok("Uspesno azuriran status. Status = U_TRANSPORTU");
    }

    @PutMapping("api/dostavljac/promeniStatusUDostavljena/{idPorudzbine}")
    public ResponseEntity<String> promeniStatusUDostavljena(@PathVariable(name = "idPorudzbine") Long idPorudzbine, HttpSession session){
        Korisnik k = (Korisnik) session.getAttribute("korisnik");
        /*if(k == null){
            return new ResponseEntity("Niste ulogovani", HttpStatus.FORBIDDEN);
        }
        if(k.getUloga() != Korisnik.Uloga.DOSTAVLJAC){
            return new ResponseEntity("Nemate pristup ovoj stranici", HttpStatus.FORBIDDEN);
        }*/


        Porudzbine p = porudzbineService.getPorudzbinaById(idPorudzbine);
        if(p == null)
            return new ResponseEntity("Ne postoji porudzbina", HttpStatus.BAD_REQUEST);
        if(p.getStatus() != Porudzbine.Status.U_TRANSPORTU)
            return new ResponseEntity("Status porudzbine nije: U_TRANSPORTU", HttpStatus.BAD_REQUEST);


        porudzbineService.updateStauts(Porudzbine.Status.DOSTAVLJENA, idPorudzbine);
        kupacService.updateKupacBodovi(p);

        return ResponseEntity.ok("Uspesno azuriran status. Status = DOSTAVLJENA");
    }

}
