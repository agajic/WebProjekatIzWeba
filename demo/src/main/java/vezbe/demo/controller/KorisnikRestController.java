package vezbe.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vezbe.demo.dto.KorisnikDto;
import vezbe.demo.dto.KorisnikUpdateDto;
import vezbe.demo.dto.LogRegDto;
import vezbe.demo.model.Admin;
import vezbe.demo.model.Korisnik;
import vezbe.demo.service.KorisnikService;

import javax.servlet.http.HttpSession;

@RestController
public class KorisnikRestController {



    @Autowired
    private KorisnikService korisnikService;

    @GetMapping("/api/")
    public String welcome(){
        return "Pozdrav!";
    }

    @PostMapping("/api/register")
    public String register(@RequestBody LogRegDto regDto){

        if(regDto.getUsername().isEmpty() || regDto.getPassword().isEmpty()){
            return "Morate popuniti sva polja!";
        }

        if(korisnikService.register(regDto.getUsername(), regDto.getPassword())){
            return "Uspesno ste se registrovali!";
        }
        return "Neuspesna registracija!";
    }

    @PostMapping("api/login")
    public ResponseEntity<String> login(@RequestBody LogRegDto logDto, HttpSession session){
        if(logDto.getUsername().isEmpty() || logDto.getPassword().isEmpty())
            return new ResponseEntity("Morate popuniti sva polja..", HttpStatus.BAD_REQUEST);

        Korisnik k = korisnikService.login(logDto.getUsername(), logDto.getPassword());
        if(k == null)
            return new ResponseEntity("Nije pronadjen korisnik..", HttpStatus.FORBIDDEN);

        session.setAttribute("korisnik", k);
        return ResponseEntity.ok("Uspesno logovanje!");
    }

    @GetMapping("api/userInfo") //PITATI KAKO UPDATE
    public  ResponseEntity<KorisnikDto> userInfo(HttpSession session){
        Korisnik k = (Korisnik) session.getAttribute("korisnik");
        if(k==null){
            return new ResponseEntity("Niste ulogovani", HttpStatus.FORBIDDEN);
        }
        KorisnikDto kdto = new KorisnikDto(k);
        return ResponseEntity.ok(kdto);
    }

    @PutMapping("api/changeUserInfo/{id}")
    public ResponseEntity<String> changeUserInfo(@PathVariable(name = "id") Long id, @RequestBody KorisnikUpdateDto kdto, HttpSession session){
        Korisnik k = (Korisnik) session.getAttribute("korisnik");
        if(k==null){
            return new ResponseEntity("Niste ulogovani", HttpStatus.FORBIDDEN);
        }
        if(korisnikService.update(k.getId(), kdto))
            return ResponseEntity.ok("Uspesno izmenjene informacije");
        return new ResponseEntity("Nije pronadjen korisnik sa takvim id", HttpStatus.BAD_REQUEST);
    }
}
