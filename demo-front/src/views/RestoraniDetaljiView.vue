<template>
<div>
    <h1 style="text-align:center">Restoran Detalji</h1>
    <div class="card">
        <h2> {{ restoran.naziv }} </h2>
        <h2> {{ restoran.tip }} </h2>
        <h2> {{ restoran.lokacija }} </h2>
        <h2> {{ restoran.status }} </h2>
        <br>
    </div>

    <br>
    <h2>Lista artikala u restoranu</h2>
    <div class="container-employees">
    <table id="employees">
        <tr>
          <th>Naziv Artikla</th>
          <th>Cena</th>
        </tr>
        <tr v-for="artikal in restoran.listaArtikala" :key="artikal.id">
          <td>{{ artikal.naziv }}</td>
          <td>{{ artikal.cena }}</td>
        </tr>
      </table>
    </div>

</div>
</template>
<script>
import axios from "axios";
export default {
    name: 'RestoraniDetaljiView',
    props: ["artikal"],
    data: function() {
        return {
            restoran: {},
        }
    },
    mounted: function() {
        axios
            .get("http://localhost:8080/api/restorani/" + this.$route.query.id)
            .then((res) => {
                this.restoran = res.data
                console.log(res)
            })
            .catch((err) => {
                console.log(this.$route.query.id)
                console.log(err)
            })

        /*fetch('http://localhost:8081/api/employees/' + this.$route.query.id)
            .then(response => response.json())
            .then(data => {console.log("Success:", data); this.employee = data})
            .catch((error) => {
                console.error("Error:", error);
            });*/
    }
}
</script>
