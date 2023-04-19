<template>
  <v-container class="">
    <v-row>
      <v-col>
        <v-row>
          <v-col>
            <h3>Inventory</h3>
          </v-col>
        </v-row>
        <v-row>
          <v-col cols="12" md="6">
            <v-card>
              <v-card-title class="my-3">
                date you wish to install the EMS
              </v-card-title>
              <v-card-text>
                <v-row>
                  <v-col>
                    <v-text-field v-model="appointmentDate" label="Appointment"></v-text-field>
                  </v-col>
                </v-row>
                <v-row>
                  <v-col>
                    <v-btn @click="makeAppointment" block color="primary">Fix Appointment</v-btn>
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
  name: "Inventory",
  data() {
    return {
      appointmentDate: null,
      snackbar: false
    }
  },
  created() {

  },
  mounted() {
  },
  methods: {
    makeAppointment() {
      let postData = {
        appointmentDate: this.appointmentDate
      }

      console.log(postData)

      axios
        .post('http://localhost:8081/api/inventory/appointment-reply', postData)
        .then((response) => {
          console.log(response)
          this.snackbar = false
        })
        .catch((err) => {
          console.log(err)
        })
    }
  }
}
</script>
