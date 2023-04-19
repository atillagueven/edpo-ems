<template>
  <v-container class="">
    <v-row>
      <v-col>
        <v-row>
          <v-col>
            <h3>Payment</h3>
          </v-col>
        </v-row>
        <v-row>
          <v-col cols="12" md="6">
            <v-card>
              <v-card-title class="my-3">
                Mark payment as complete for invoice
              </v-card-title>
              <v-card-text>
                <v-row>
                  <v-col>
                    <v-text-field v-model="correlationId" label="Invoice Id"></v-text-field>
                  </v-col>
                </v-row>
                <v-row>
                  <v-col>
                    <v-btn @click="startPaymentProcess" block color="primary">Confirm payment</v-btn>
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
import {v4 as uuidv4} from 'uuid'

export default {
  name: "Payment",
  data() {
    return {
      correlationId: '',
      snackbar: false
    }
  },
  created() {

  },
  mounted() {
  },
  methods: {
    startPaymentProcess() {
      let postData = {
        invoiceId: this.correlationId
      }

      console.log(postData)

      axios
        .post('http://localhost:8081/api/payment/payment-received', postData)
        .then((response) => {
          console.log(response)
          this.snackbar = true
        })
        .catch((err) => {
          console.log(err)
        })
        .finally(() => {

        })
    },
    markPaymentReceived() {
      let postData = {
        correalationId: this.correlationId
      }

      console.log(postData)

      axios
        .post('http://localhost:8081/api/payment-received', postData)
        .then((response) => {
          console.log(response)
        })
        .catch((err) => {
          console.log(err)
        })
        .finally(() => {

        })
    }
  }
}


</script>
