package vezbe.demo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vezbe.demo.model.*;
import vezbe.demo.repository.*;

import java.util.*;

@Configuration
public class DatabaseConfiguration {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private ArtikalRepository artikalRepository;

    @Autowired
    private DostavljacRepository dostavljacRepository;

    @Autowired
    private KomentarRepository komentarRepository;

    @Autowired
    private KorsnikRepository korsnikRepository;

    @Autowired
    private KupacRepository kupacRepository;

    @Autowired
    private LokacijaRepository lokacijaRepository;

    @Autowired
    private MenadzerRepository menadzerRepository;

    @Autowired
    private PorudzbineRepository porudzbineRepository;

    @Autowired
    private RestoranRepository restoranRepository;

    @Autowired
    private TipKupcaRepository tipKupcaRepository;

    @Bean
    public boolean instantiate(){

        Artikal artikal1 = new Artikal("Mleko", 120);
        Artikal artikal2 = new Artikal("Hleb", 60);
        artikalRepository.saveAll(List.of(artikal1, artikal2));

        TipKupca tipKupca1 = new TipKupca("Gold", 20);
        tipKupcaRepository.saveAll(List.of(tipKupca1));

        Lokacija lokacija1 = new Lokacija(100.2,200.1,"Futoska 20");
        Lokacija lokacija2 = new Lokacija(200.2,400.1,"Bulevar Oslobodjenja 45");
        lokacijaRepository.saveAll(List.of(lokacija1, lokacija2));

        Restoran restoran1 = new Restoran("Caribic","Italijanski", Restoran.StatusRestorana.RADI);
        restoran1.setLokacija(lokacija1);
        restoran1.getArtikliUPonudi().add(artikal1);
        artikal1.getRestorani().add(restoran1);
        restoranRepository.saveAll(List.of(restoran1));

        Menadzer menadzer1 = new Menadzer("pperic","123");
        menadzer1.setUloga(Korisnik.Uloga.MENADZER);
        menadzer1.setZaduzen(restoran1);
        Menadzer menadzer2 = new Menadzer("ltubin","123");
        menadzer1.setUloga(Korisnik.Uloga.MENADZER);
        menadzerRepository.saveAll(List.of(menadzer1, menadzer2));

        Kupac kupac1 = new Kupac("mmikic", "123"); //KAKO DATUM
        kupac1.setUloga(Korisnik.Uloga.KUPAC);
        kupac1.setIme("Miki");
        kupac1.setPrezime("Mikic");
        kupacRepository.saveAll(List.of(kupac1));

        Korisnik korisnik1 = new Korisnik("nkrstin", "123");
        korsnikRepository.saveAll(List.of(korisnik1));

        Komentar komentar1 = new Komentar(kupac1, restoran1, "Solidno", 2);
        komentarRepository.saveAll(List.of(komentar1));

        Dostavljac dostavljac1 = new Dostavljac("smijat", "123");
        dostavljac1.setUloga(Korisnik.Uloga.DOSTAVLJAC);
        dostavljacRepository.saveAll(List.of(dostavljac1));

        Porudzbine porudzbine1 = new Porudzbine("112233", restoran1, kupac1, dostavljac1);
        porudzbine1.setStatus(Porudzbine.Status.U_TRANSPORTU);
        porudzbineRepository.saveAll(List.of(porudzbine1));
        restoran1.getPorudzbine().add(porudzbine1);

        Admin admin1 = new Admin("agajic", "123");
        admin1.setUloga(Korisnik.Uloga.ADMIN);
        adminRepository.saveAll(List.of(admin1));

        /*ENDE*/

        Company company = new Company("Company 1", "Some address 123");

        companyRepository.save(company);

        Department department1 = new Department("first department");
        Department department2 = new Department("second department");

        department1.setCompany(company);
        department2.setCompany(company);
        departmentRepository.saveAll(
                List.of(department1, department2)
        );

        Employee pera = new Employee(
                "Pera", "Peric", "Rukovodilac", department1
        );
        Employee mika = new Employee(
                "Mika", "Mikic", "Menadzer", department1
        );
        Employee zika = new Employee(
                "Zika", "Zikic", "Radnik", department2
        );

        employeeRepository.saveAll(
                List.of(pera, mika, zika)
        );

        Project project1 = new Project(
                "Projekat 1", new Date(125, Calendar.JULY, 4)
        );

        Project project2 = new Project(
                "Projekat 2", new Date(129, Calendar.DECEMBER, 3)
        );

        projectRepository.saveAll(
                List.of(project1, project2)
        );

        mika.getProjects().add(project1);
        mika.getProjects().add(project2);

        zika.getProjects().add(project2);

        employeeRepository.save(mika);
        employeeRepository.save(zika);

        return true;
    }
}