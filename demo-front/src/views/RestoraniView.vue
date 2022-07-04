<template>

  <div class="container">
  <h1>Lista restorana</h1>
  </div>

<div class="container-employees">
      <table id="employees">
        <tr>
          <th>Naziv</th>
          <th>Tip restorana</th>
          <th>Lokacija</th>
          <th>Status</th>
          <th>Detaljnije</th>
        </tr>
        <tr v-for="restoran in restorani" :key="restoran.id">
          <td>{{ restoran.naziv }}</td>
          <td>{{ restoran.tipRestorana }}</td>
          <td>{{ restoran.lokacija.adresa }}</td>
          <td>{{ restoran.status }}</td>
          <td>
            <button class="btnSeeMore btn btn-primary" v-on:click="seeMore(restoran)">
              Detaljnije
            </button>
          </td>
        </tr>
      </table>
    </div>
  <br><br><br>

    <h2>Pretraga</h2>
    <div class="container">
        <div class="row">
            <input v-model="pretragaN.naziv" class="col-8" />
            <button v-on:click="pretragaNaziv()" class="btn btn-primary col-4">Pretraga naziv</button>
        </div><br>
        <div class="row">
            <input v-model="pretragaT.tipRestorana" class="col-8" />
            <button v-on:click="pretragaTip()" class="btn btn-primary col-4">Pretraga tip</button>
        </div><br>
        <div class="row">
            <input v-model="pretragaL.adresa" class="col-8" />
            <button v-on:click="pretragaLokacija()" class="btn btn-primary col-4">Pretraga adresa</button>
        </div>
    </div><br>

    <h2>Rezultat pretrage</h2>
    <div class="container-employees">
      <table id="employees">
        <tr>
          <th>Naziv</th>
          <th>Tip restorana</th>
          <th>Lokacija</th>
          <th>Status</th>
          <th>Detaljnije</th>
        </tr>
        <tr v-for="restoran in restorani2" :key="restoran.id">
          <td>{{ restoran.naziv }}</td>
          <td>{{ restoran.tipRestorana }}</td>
          <td>{{ restoran.lokacija.adresa }}</td>
          <td>{{ restoran.status }}</td>
          <td>
            <button class="btnSeeMore btn btn-primary" v-on:click="seeMore(restoran)">
              Detaljnije
            </button>
          </td>
        </tr>
      </table>
    </div>


</template>

<script>
import axios from "axios"
export default {
  name: "RestoranView",
  props: ["restoran"],
  data: function() {
    return {
      restorani: [],
      pretragaN: {
        naziv:"",
      },
      pretragaT: {
        tipRestorana:"",
      },
      pretragaL: {
        adresa:"",
      },
      restorani2: [],
    };
  },
   methods: {
    seeMore: function (restoran) {
      this.$router.push("/restorani/detalji?id=" + restoran.id);
      console.log(restoran.id);
    },
    pretragaNaziv: function() {
      axios
        .post("http://localhost:8080/api/pretragaNaziv", this.pretragaN, {
          withCredentials: true
        })
        .then(res => {
          this.restorani2 = null
          this.restorani2 = res.data
          console.log(res); 
        })
        .catch(error => {
          this.restorani2 = null
          console.log(error.response);
        });
    },
    pretragaTip: function() {
      axios
        .post("http://localhost:8080/api/pretragaTip", this.pretragaT, {
          withCredentials: true
        })
        .then(res => {
          this.restorani2 = null
          this.restorani2 = res.data
          console.log(res); 
        })
        .catch(error => {
          this.restorani2 = null
          console.log(error.response);
        });
    },
    pretragaLokacija: function() {
      axios
        .post("http://localhost:8080/api/pretragaLokacija", this.pretragaL, {
          withCredentials: true
        })
        .then(res => {
          this.restorani2 = null
          this.restorani2 = res.data
          console.log(res); 
        })
        .catch(error => {
          this.restorani2 = null
          console.log(error.response);
        });
    },
   },
   mounted: function() {
        axios
            .get("http://localhost:8080/api/restorani",{
              withCredentials: true
            }) 
            .then((res) => {
                this.restorani = res.data
                console.log(res)
            })
            .catch((err) => {
                console.log(err)
            })
    }
};
</script>
