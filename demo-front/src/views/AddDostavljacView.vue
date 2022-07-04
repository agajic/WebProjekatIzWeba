<template>

  <div class="container">
  <h1>Dodaj dostavljaca</h1>


    <div>
      <label for="inputEmail4" class="form-label">Korisnicko ime</label>
      <input v-model="korisnik.korisnicko_ime" class="form-control" />
    </div>
    <div>
      <label for="inputEmail4" class="form-label">Lozinka</label>
      <input v-model="korisnik.lozinka" class="form-control" />
    </div>
    <div>
      <label for="inputEmail4" class="form-label">Ime</label>
      <input v-model="korisnik.ime" class="form-control" />
    </div>
    <div>
      <label for="inputEmail4" class="form-label">Prezime</label>
      <input v-model="korisnik.prezime" class="form-control" />
    </div>
    <label for="">Pol:</label> <br>
    <div class="form-check form-check-inline">
      <input
        class="form-check-input"
        type="radio"
        name="inlineRadioOptions"
        id="inlineRadio1"
        value=0
        v-model="korisnik.pol"
      />
      <label class="form-check-label" for="inlineRadio1">MUSKI</label>
    </div>
    <div class="form-check form-check-inline">
      <input
        class="form-check-input"
        type="radio"
        name="inlineRadioOptions"
        id="inlineRadio2"
        value=1
        v-model = "korisnik.pol"
      />
      <label class="form-check-label" for="inlineRadio2">ZENSKI</label>
    </div>
    <br><br>
    <div>
      <label for="inputEmail4" class="form-label">Datum</label>
      <input  v-model="korisnik.datum" type="date" />
    </div>
    <br><br>

    <label for="">Uloga:</label> <br>
    <div class="form-check form-check-inline">
      <input
        class="form-check-input"
        type="radio"
        name="inlineRadioOptionss"
        id="inlineRadio2"
        value=2
        v-model="korisnik.uloga"
      />
      <label class="form-check-label" for="inlineRadio2">DOSTAVLJAC</label>
    </div><br><br>

  </div>

  <button v-on:click="sacuvajSe()" class="btn btn-primary">
        Unesi dostavljaca
      </button>

</template>

<script>
import axios from "axios"
export default {
  name: "AddDostavljacView",
  data: function() {
    return {
      korisnik: {
        korisnicko_ime:"",
        lozinka:"",
        ime:"",
        prezime:"",
        pol:null,
        datum:"",
        uloga:null,
      },
    };
  },
   methods: {
    sacuvajSe: function() {
      axios
        .post("http://localhost:8080/api/admin/addDostavljac", this.korisnik, {
          withCredentials: true
        })
        .then(res => {
          console.log(res); 
          this.$router.push("/admin");  
          alert("Uspesno");
        })
        .catch(error => {
          console.log(error.response);
          alert("Neuspesno");
        });
    }
   },
};
</script>
