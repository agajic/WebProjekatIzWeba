package vezbe.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import vezbe.demo.dto.*;
import vezbe.demo.model.Artikal;
import vezbe.demo.model.Korisnik;
import vezbe.demo.model.Restoran;
import vezbe.demo.service.RestoranService;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class RestoranController {
    @Autowired
    private RestoranService restoranService;

    @GetMapping("api/restorani")
    public ResponseEntity<List<RestoranPocetnaDto>> pocetna(HttpSession session){

        /*Korisnik k = (Korisnik) session.getAttribute("korisnik");
        if(k == null){
            return new ResponseEntity("Niste ulogovani", HttpStatus.FORBIDDEN);
        }*/

        List<Restoran> restorani = restoranService.findAll();
        if(restorani.isEmpty()){
            System.out.println("Nema");
            return new ResponseEntity("Nije pronadjen ni jedan resotran..", HttpStatus.BAD_REQUEST);
        }

        List<RestoranPocetnaDto> restoraniDto = new ArrayList<>();
        for(Restoran r : restorani){
            RestoranPocetnaDto rdto = new RestoranPocetnaDto(r);
            restoraniDto.add(rdto);
        }
        return ResponseEntity.ok(restoraniDto);
    }

    @PostMapping("api/pretragaNaziv")
    public ResponseEntity<List<RestoranPocetnaDto>> pretragaNaziv(@RequestBody NazivRestoranaDto naziv){

        /*Korisnik k = (Korisnik) session.getAttribute("korisnik");
        if(k == null){
            return new ResponseEntity("Niste ulogovani", HttpStatus.FORBIDDEN);
        }*/

        List<Restoran> restorani = restoranService.findByNaziv(naziv.getNaziv());
        if(restorani.isEmpty()){
            return new ResponseEntity("Nije pronadjen ni jedan resotran..", HttpStatus.BAD_REQUEST);
        }

        List<RestoranPocetnaDto> restoraniDto = new ArrayList<>();
        for(Restoran r : restorani){
            RestoranPocetnaDto rdto = new RestoranPocetnaDto(r);
            restoraniDto.add(rdto);
        }

        return ResponseEntity.ok(restoraniDto);
    }

    @PostMapping("api/pretragaTip")
    public ResponseEntity<List<RestoranPocetnaDto>> pretragaTip(@RequestBody TipRestoranaDto tip, HttpSession session){

        /*Korisnik k = (Korisnik) session.getAttribute("korisnik");
        if(k == null){
            return new ResponseEntity("Niste ulogovani", HttpStatus.FORBIDDEN);
        }*/

        List<Restoran> restorani = restoranService.findByTip(tip.getTipRestorana());
        if(restorani.isEmpty()){
            return new ResponseEntity("Nije pronadjen ni jedan resotran..", HttpStatus.BAD_REQUEST);
        }

        List<RestoranPocetnaDto> restoraniDto = new ArrayList<>();
        for(Restoran r : restorani){
            RestoranPocetnaDto rdto = new RestoranPocetnaDto(r);
            restoraniDto.add(rdto);
        }

        return ResponseEntity.ok(restoraniDto);
    }

    @PostMapping("api/pretragaLokacija")
    public ResponseEntity<List<RestoranPocetnaDto>> pretragaAdresa(@RequestBody AdresaRestoranaDto adresa, HttpSession session){

        /*Korisnik k = (Korisnik) session.getAttribute("korisnik");
        if(k == null){
            return new ResponseEntity("Niste ulogovani", HttpStatus.FORBIDDEN);
        }*/

        List<Restoran> restorani = restoranService.findByAdresa(adresa.getAdresa());
        if(restorani.isEmpty()){
            return new ResponseEntity("Nije pronadjen ni jedan resotran..", HttpStatus.BAD_REQUEST);
        }

        List<RestoranPocetnaDto> restoraniDto = new ArrayList<>();
        for(Restoran r : restorani){
            RestoranPocetnaDto rdto = new RestoranPocetnaDto(r);
            restoraniDto.add(rdto);
        }

        return ResponseEntity.ok(restoraniDto);
    }

    @GetMapping("api/restorani/{id}")
    public ResponseEntity<RestoranPrikazDto> restoran(@PathVariable(name = "id") Long id, HttpSession session){
        Restoran r = restoranService.findById(id);
        if(r == null){
            return new ResponseEntity("Ne postoji restoran sa takvim id", HttpStatus.BAD_REQUEST);
        }

        Set<Artikal> artikals = r.getArtikliUPonudi();
        Set<ArtikalDto> artikalsDto = new HashSet<>();

        for(Artikal a : artikals){
            ArtikalDto adto = new ArtikalDto(a);
            artikalsDto.add(adto);
        }

        RestoranPrikazDto rdto = new RestoranPrikazDto(r, artikalsDto);

        return ResponseEntity.ok(rdto);
    }
}
