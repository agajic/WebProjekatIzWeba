package vezbe.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import vezbe.demo.dto.PorudzbineDostavljacaDto;
import vezbe.demo.model.Dostavljac;
import vezbe.demo.model.Korisnik;
import vezbe.demo.model.Porudzbine;
import vezbe.demo.service.DostavljacService;
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
        Set<Porudzbine> porudzbines = d.getPorudzbineZaDostavu();
        Set<PorudzbineDostavljacaDto> porudzbineDto = new HashSet<>();
        for(Porudzbine p : porudzbines){
            PorudzbineDostavljacaDto pdto = new PorudzbineDostavljacaDto(p);
            porudzbineDto.add(pdto);
        }
        Set<Porudzbine> porudzbineUCekanju = porudzbineService.getPorudzbineUCekanju();
        for(Porudzbine p : porudzbineUCekanju){
            PorudzbineDostavljacaDto pdto = new PorudzbineDostavljacaDto(p);
            porudzbineDto.add(pdto);
        }

        if(porudzbineDto.isEmpty())
            return new ResponseEntity("Nema trenutno porudzbina", HttpStatus.BAD_REQUEST);
        return ResponseEntity.ok(porudzbineDto);
    }
}
