<template>

  <div class="container">
  <h1>Dodeli menadzera restoranu</h1>

    <div>
      <label for="inputEmail4" class="form-label">ID Restorana</label>
      <input v-model="ids.idRestorana" class="form-control" />
    </div>
    <div>
      <label for="inputEmail4" class="form-label">ID Menadzera</label>
      <input v-model="ids.idMenadzera" class="form-control" />
    </div><br><br>

  </div>

  <button v-on:click="sacuvajSe()" class="btn btn-primary">
        Dodeli menadzera restoranu
      </button>

</template>

<script>
import axios from "axios"
export default {
  name: "AddDodeliMenadzeraView",
  data: function() {
    return {
      ids: {
        idRestorana:"",
        idMenadzera:"",
      },
    };
  },
   methods: {
    sacuvajSe: function() {
      axios
        .put("http://localhost:8080/api/admin/dodeliMenadzera", this.ids, {
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
