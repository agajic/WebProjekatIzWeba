<template>

    <h1>Menadzer</h1>

  <div class="container">
  <h2>Naziv restorana za koji je zaduzen</h2>
  <h3>{{ menadzer.zaduzen }}</h3>
  </div>
<br>
<h2>Porudzbine ovog restorana</h2>
<div class="container-employees">
      <table id="employees">
        <tr>
          <th>UUID</th>
          <th>Vreme Porudzbine</th>
          <th>Kupac</th>
          <th>Status</th>
          <th>Promeni status</th>
        </tr>
        <tr v-for="porudzbina in menadzer.porudzbine" :key="porudzbina.id">
          <td>{{ porudzbina.UUID }}</td>
          <td>{{ porudzbina.vremePorudzbine }}</td>
          <td>{{ porudzbina.kupac }}</td>
          <td>{{ porudzbina.status }}</td>
          <td>
            <button class="btnSeeMore btn btn-primary" v-on:click="promeniUDostavljena(porudzbina)">
              Promeni
            </button>
          </td>
        </tr>
      </table>
    </div>
  <br>

</template>

<script>
import axios from "axios"
export default {
  name: "MenadzerView",
  props: ["porudzbina"],
  data: function() {
    return {
      menadzer: {},
    };
  },
   methods: {
    promeniUDostavljena: function(porudzbina) {
      axios
        .put("http://localhost:8080/api/dostavljac/promeniStatusUDostavljena/" + porudzbina.id, {
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
            .get("http://localhost:8080/api/menadzer",{
              withCredentials: true
            }) 
            .then((res) => {
                this.menadzer = res.data
                console.log(res)
            })
            .catch((err) => {
                console.log(err)
            })
    }
};
</script>
