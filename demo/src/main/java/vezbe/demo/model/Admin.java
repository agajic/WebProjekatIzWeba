package vezbe.demo.model;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class Admin extends Korisnik implements Serializable {
    public Admin(){

    }

    public Admin(String korisnicko_ime, String lozinka){
        super(korisnicko_ime, lozinka);
    }
}
