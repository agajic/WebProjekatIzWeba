package vezbe.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import vezbe.demo.dto.LogRegDto;
import vezbe.demo.dto.LoginDto;
import vezbe.demo.model.Korisnik;
import vezbe.demo.service.KorisnikService;

import javax.servlet.http.HttpSession;

@Controller
public class KorisnikBasicController {
    @Autowired
    private KorisnikService korisnikService;

    @GetMapping("/login-form")
    public String login(Model model) {
        LogRegDto logregDto = new LogRegDto();
        model.addAttribute("login", logregDto);
        return "login.html";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LogRegDto logDto, HttpSession session){
        if(logDto.getUsername().isEmpty() || logDto.getPassword().isEmpty())
            return "redirect:/login-form";

        Korisnik k = korisnikService.login(logDto.getUsername(), logDto.getPassword());
        if(k == null)
            return "redirect:/login-form";

        session.setAttribute("korisnik", k);
        return "index.html";
    }
}
