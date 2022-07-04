<template>

  <div class="container">
    <form>
      <h1>Login</h1>
  <div class="form-group">
    <label for="exampleInputEmail1">Korisnicko ime</label>
    <input v-model="korisnik.username" class="form-control" id="exampleInputEmail1" placeholder="Enter username">
  </div>
  <div class="form-group">
    <label for="exampleInputPassword1">Lozinka</label>
    <input v-model="korisnik.password" type="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
  </div><br>
  <a v-on:click="prijaviSe()" type="button" class="btn btn-primary">Login</a>
</form>
  </div>

</template>

<script>
import axios from "axios"
export default {
  name: "LoginView",
  data: function() {
    return {
      korisnik: {
        username: "",
        password: ""
      }
    };
  },
   methods: {
    prijaviSe: function() {
      axios
        .post("http://localhost:8080/api/login", this.korisnik, {
          withCredentials: true
        })
        .then(res => {
          console.log(res);

          this.$router.push("/userInfo");  
          alert("Uspesno");
          this.$router.push("/userInfo");  
        })
        .catch(error => {
          console.log(error.response);
          alert("Neuspesno");
        });
    }
   }
};
</script>
