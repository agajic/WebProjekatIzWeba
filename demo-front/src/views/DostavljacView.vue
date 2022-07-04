<template>

    <h1>Dostavljac</h1>

  <div class="container">
  <h2>Lista porudzbina za dostavu</h2>
  </div>

<div class="container-employees">
      <table id="employees">
        <tr>
          <th>UUID</th>
          <th>Vreme Porudzbine</th>
          <th>Kupac</th>
          <th>Status</th>
          <th>Promeni status</th>
        </tr>
        <tr v-for="porudzbina in porudzbine" :key="porudzbina.id">
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
  name: "DostavljacView",
  props: ["porudzbina"],
  data: function() {
    return {
      korisnici: [],
    };
  },
  data: function() {
    return {
      porudzbine: [],
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
            .get("http://localhost:8080/api/dostavljac",{
              withCredentials: true
            }) 
            .then((res) => {
                this.porudzbine = res.data
                console.log(res)
            })
            .catch((err) => {
                console.log(err)
            })
    }
};
</script>
