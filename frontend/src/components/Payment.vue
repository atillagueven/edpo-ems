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
                Start payment process
              </v-card-title>
              <v-card-text>
                <v-row>
                  <v-col>
                    <v-text-field v-model="correlationId" label="Correlation Id"></v-text-field>
                  </v-col>
                </v-row>
                <v-row>
                  <v-col>
                    <v-btn @click="startPaymentProcess" block color="primary">Start process</v-btn>
                  </v-col>
                </v-row>
              </v-card-text>
            </v-card>
          </v-col>
          <v-col cols="12" md="6">
            <v-card>
              <v-card-title class="my-3">
                Set payment confirmed
              </v-card-title>
              <v-card-text>
                <v-row>
                  <v-col>
                    <v-text-field v-model="correlationId" label="Correlation Id"></v-text-field>
                  </v-col>
                </v-row>
                <v-row>
                  <v-col>
                    <v-btn @click="markPaymentReceived" block color="primary">Start process</v-btn>
                  </v-col>
                </v-row>
              </v-card-text>
            </v-card>
          </v-col>
        </v-row>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import axios from 'axios'
import {v4 as uuidv4} from 'uuid'

export default {
  name: "Payment",
  data() {
    return {
      correlationId: uuidv4()
    }
  },
  created() {

  },
  mounted() {
  },
  methods: {
    startPaymentProcess() {
      let postData = {
        requester: "fabio",
        correalationId: this.correlationId
      }

      console.log(postData)

      axios
        .post('http://localhost:8081/api/start-payment-process', postData)
        .then((response) => {
          console.log(response)
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
