<template>

  <div class="container">
  <h1>Dodaj restoran</h1>


    <div>
      <label for="inputEmail4" class="form-label">Naziv</label>
      <input v-model="restoran.naziv" class="form-control" />
    </div>
    <div>
      <label for="inputEmail4" class="form-label">Tip restorana</label>
      <input v-model="restoran.tipRestorana" class="form-control" />
    </div>
    <div>
      <label for="inputEmail4" class="form-label">Adresa</label>
      <input v-model="restoran.adresa" class="form-control" />
    </div>
    <label for="">Status:</label> <br>
    <div class="form-check form-check-inline">
      <input
        class="form-check-input"
        type="radio"
        name="inlineRadioOptions"
        id="inlineRadio1"
        value=0
        v-model="restoran.status"
      />
      <label class="form-check-label" for="inlineRadio1">RADI</label>
    </div>
    <div class="form-check form-check-inline">
      <input
        class="form-check-input"
        type="radio"
        name="inlineRadioOptions"
        id="inlineRadio2"
        value=1
        v-model = "restoran.status"
      />
      <label class="form-check-label" for="inlineRadio2">NE_RADI</label>
    </div>
    <br><br>

  </div>

  <button v-on:click="sacuvajSe()" class="btn btn-primary">
        Unesi restoran
      </button>

</template>

<script>
import axios from "axios"
export default {
  name: "AddRestoranView",
  data: function() {
    return {
      restoran: {
        naziv:"",
        tipRestorana:"",
        adresa:"",
        status:null,
      },
    };
  },
   methods: {
    sacuvajSe: function() {
      axios
        .post("http://localhost:8080/api/admin/addRestoran", this.restoran, {
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
