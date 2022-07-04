<template>

  <div class="container">
  <h1>Registracija</h1>
    <div>
      <label for="inputEmail4" class="form-label">Korisnicko ime</label>
      <input v-model="korisnik.username" class="form-control" />
    </div>
    <div>
      <label for="inputEmail4" class="form-label">Lozinka</label>
      <input v-model="korisnik.password" class="form-control" />
    </div>

    <div class="col-12">
      <button v-on:click="registrujSe()" class="btn btn-primary">
        Registracija
      </button>
    </div>
  </div>

</template>

<script>
import axios from "axios"
export default {
  name: "RegisterView",
  data: function() {
    return {
      korisnik: {
        username: "",
        password: ""
      }
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
          this.$router.push("/loginForm");  
          alert("Uspesno");
        })
        .catch(error => {
          console.log(error.response);
          alert("Neuspesno");
        });
    }
   }
};
</script>
