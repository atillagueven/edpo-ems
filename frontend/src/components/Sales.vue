<template>
  <v-container class="">
    <v-row>
      <v-col>
        <v-row>
          <v-col>
            <h3>Sales</h3>
          </v-col>
        </v-row>
        <v-row>
          <v-col cols="12" md="6">
            <v-card>
              <v-card-title class="my-3">
                Request an Offer
              </v-card-title>
              <v-card-text>
                <v-row>
                  <v-col>
                    <v-text-field v-model="customerName" label="Requester"></v-text-field>
                  </v-col>
                </v-row>
                <v-row>
                  <v-col>
                    <v-text-field v-model="customerEmail" label="Email"></v-text-field>
                  </v-col>
                </v-row>
                <v-row>
                  <v-col>
                    <v-text-field v-model="loadProfileConsumption" type="number" label="Average energy consumption in KWH"></v-text-field>
                  </v-col>
                </v-row>
                <v-row>
                  <v-col>
                    <v-text-field v-model="loadProfileProduction" type="number" label="Average energy production in KWH"></v-text-field>
                  </v-col>
                </v-row>
                <v-row>
                  <v-col>
                    <v-btn @click="requestSalesOffer" block color="primary">Request Offer</v-btn>
                  </v-col>
                </v-row>
              </v-card-text>
            </v-card>
          </v-col>
        </v-row>
      </v-col>
    </v-row>
    <v-snackbar
      color="success"
      v-model="snackbar"
      timeout="3000"
    >
      Request complete
    </v-snackbar>
  </v-container>
</template>

<script>
import axios from 'axios'

export default {
  name: "Sales",
  data() {
    return {
      date: null,
      customerName: "valentin",
      customerEmail: "valentin.berger@student.unisg.ch",
      loadProfileConsumption: 0,
      loadProfileProduction: 0,
      snackbar: false
    }
  },
  created() {

  },
  mounted() {
  },
  methods: {
    requestSalesOffer() {
      let postData = {
        customerName: this.customerName,
        customerEmail: this.customerEmail,
        loadProfileConsumption: this.loadProfileConsumption,
        loadProfileProduction: this.loadProfileProduction,
      }

      console.log(postData)

      axios
        .post('http://localhost:8081/api/sales/request-offer', postData)
        .then((response) => {
          console.log(response)
          this.snackbar = true
        })
        .catch((err) => {
          console.log(err)
        })
    }
  }
}
</script>
