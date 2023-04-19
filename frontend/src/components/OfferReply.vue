<template>
  <v-container class="">
    <v-row>
      <v-col>
        <v-row>
          <v-col>
            <h3>Reply to your offer with ID: {{ offerId }}</h3>
          </v-col>
        </v-row>
        <v-row>
          <v-col cols="12" md="6">
            <v-card>
              <v-card-title class="my-3">
                Offer reply
              </v-card-title>
              <v-card-text>
                <v-row>
                <v-col>
                  <v-checkbox v-model="offerAccepted" label="Accept offer"></v-checkbox>
                </v-col>
              </v-row>
                <v-row>
                  <v-col>
                    <v-checkbox v-if="!offerAccepted" v-model="newOfferRequested" label="Request new offer"></v-checkbox>
                  </v-col>
                </v-row>
                <v-row>
                  <v-col>
                    <v-btn @click="replyToOffer" block color="primary">Reply</v-btn>
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
      requester: "valentin",
      email: "valentin.berger@student.unisg.ch",
      message: "Request details about the load profile",
      offerAccepted: true,
      newOfferRequested: false,
      offerId: "",
      snackbar: false
    }
  },
  created() {

  },
  mounted() {
    // get url param id
    let url = new URL(window.location.href)
    let id = url.searchParams.get("id")
    this.offerId = id
  },
  methods: {
    replyToOffer() {
      let postData = {
        offerId: this.offerId,
        requester: this.requester,
        email: this.email,
        message: this.message,
        offerAccepted: this.offerAccepted,
        newOfferRequested: this.offerAccepted ? false : this.newOfferRequested,
      }

      console.log(postData)

      axios
        .post('http://localhost:8081/api/sales/answer-offer', postData)
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
