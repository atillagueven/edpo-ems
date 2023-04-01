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
                    <v-text-field v-model="requester" label="Requester"></v-text-field>
                  </v-col>
                </v-row>
                <v-row>
                  <v-col>
                    <v-text-field v-model="email" label="Email"></v-text-field>
                  </v-col>
                </v-row>
                <v-row>
                <v-col>
                  <v-textarea v-model="message" label="Load Profile"></v-textarea>
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
  </v-container>
</template>

<script>
import axios from 'axios'

export default {
  name: "Sales",
  data() {
    return {
      requester: "valentin",
      email: "valentin.berger@student.unisg.ch",
      message: "Request details about the load profile",
    }
  },
  created() {

  },
  mounted() {
  },
  methods: {
    requestSalesOffer() {
      let postData = {
        requester: this.requester,
        email: this.email,
        message: this.message,
      }

      console.log(postData)

      axios
        .post('http://localhost:8081/api/request-offer', postData)
        .then((response) => {
          console.log(response)
        })
        .catch((err) => {
          console.log(err)
        })
    }
  }
}
</script>
