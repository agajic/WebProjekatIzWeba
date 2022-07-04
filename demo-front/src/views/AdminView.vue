<template>

  <div class="container">
  <h1>Lista korisnika</h1>
  </div>

<div class="container-employees">
      <table id="employees">
        <tr>
          <th>Ime</th>
          <th>Prezime</th>
          <th>Korisnicko ime</th>
          <th>Lozinka</th>
          <th>Uloga</th>
        </tr>
        <tr v-for="korisnik in korisnici" :key="korisnik.id">
          <td>{{ korisnik.ime }}</td>
          <td>{{ korisnik.prezime }}</td>
          <td>{{ korisnik.korisnicko_ime }}</td>
          <td>{{ korisnik.lozinka }}</td>
          <td>{{ korisnik.uloga }}</td>
        </tr>
      </table>
    </div>
  <br>
  <a type="button" href="/admin/addMenadzer" class="btn btn-primary">Dodaj Menadzera</a><br>
  <a type="button" href="/admin/addDostavljac" class="btn btn-primary">Dodaj Dostavljaca</a><br>
  <a type="button" href="/admin/addRestoran" class="btn btn-primary">Dodaj Restoran</a><br>
  <a type="button" href="/admin/dodeliMenadzera" class="btn btn-primary">Dodeli Menadzera Restoranu</a>

</template>

<script>
import axios from "axios"
export default {
  name: "AdminView",
  props: ["korisnik"],
  data: function() {
    return {
      korisnici: [],
    };
  },
  data: function() {
    return {
      korisnici: [],
    };
  },
   methods: {
    registrujSe: function() {
      axios
        .post("http://localhost:8080/api/register", this.korisnik, {
          withCredentials: true
        })
        .then(res => {
          console.log(res); 
          alert("Uspesno");
        })
        .catch(error => {
          console.log(error.response);
          alert("Neuspesno");
        });
    }
   },
   mounted: function() {
        axios
            .get("http://localhost:8080/api/admin",{
              withCredentials: true
            }) 
            .then((res) => {
                this.korisnici = res.data
                console.log(res)
            })
            .catch((err) => {
                console.log(err)
            })
    }
};
</script>
