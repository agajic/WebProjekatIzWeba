<template>

  <div class="container">
  <h1>Informacije o korisniku</h1>
    <div class="card">
        <h1> {{ korisnik.korisnicko_ime }} </h1>
        <h1> {{ korisnik.lozinka }} </h1>
        <h1> {{ korisnik.prezime }} </h1>
        <h1> {{ korisnik.pol }} </h1>
        <h1> {{ korisnik.datum }} </h1>
        <h1> {{ korisnik.uloga }} </h1>
        <br>
    </div>
  </div>

  <a type="button" href="/changeUserInfo" class="btn btn-primary">Izmeni informacije</a><br><br>
  <button class="btnSeeMore btn btn-primary" v-on:click="logout()">Logout</button>

</template>

<script>
import axios from "axios"
export default {
  name: "UserInfo",
  data: function() {
    return {
      korisnik: {
        /*korisnicko_ime:"",
        lozinka:"",
        ime:"",
        prezime:"",
        pol:"",
        datum:"",
        uloga:"",*/
      },
    };
  },
   methods: {
    logout: function() {
      axios
        .post("http://localhost:8080/api/logout", {
          withCredentials: true
        })
        .then(res => {
          console.log(res); 
          alert("Uspesno");
          this.$router.push("/loginForm"); 
        })
        .catch(error => {
          console.log(error.response);
          alert("Neuspesno");
        });
    }
   },
   mounted: function() {
        axios
            .get("http://localhost:8080/api/userInfo",{
              withCredentials: true
            }) 
            .then((res) => {
                this.korisnik = res.data
                console.log(res)
            })
            .catch((err) => {
                console.log(err)
            })

        //fetch('http://localhost:8080/api/userInfo/' + this.$route.query.id)
        /*fetch('http://localhost:8080/api/userInfo')
            .then(response => response.json())
            .then(data => {console.log("Success:", data); this.korisnik = data})
            .catch((error) => {
                console.error("Error:", error);
            });*/
    }
};
</script>
