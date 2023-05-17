<template>
  <v-container>
    <v-row>
      <v-col cols="12">
        <v-row>
          <v-col cols="12">
            <h1 class="font-weight-black pb-10">
              Energy Management of customer ID: {{ customerid }}
            </h1>
          </v-col>
          <v-col cols="6">
            <v-card>
              <v-card-title>
                Consumer: Solar Panel
              </v-card-title>
              <v-card-subtitle>
                monitor your energy production
              </v-card-subtitle>
              <div class="container">
                <Line v-if="loaded" :data="chartDataProd" class="pa-2"/>
              </div>
            </v-card>
          </v-col>
          <v-col cols="6">
            <v-card>
              <v-card-title>
                Producer: Charging Station
              </v-card-title>
              <v-card-subtitle>
                monitor your energy consumption
              </v-card-subtitle>
              <div class="container">
                <Line v-if="loaded" :data="chartDataCons" class="pa-2"/>
              </div>
            </v-card>
          </v-col>
        </v-row>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  Title,
  Tooltip,
  Legend
} from 'chart.js'
import { Line } from 'vue-chartjs'
import {id} from "vuetify/locale";

//https://vue-chartjs.org/guide/#chart-with-dynamic-styles
//https://spring.io/guides/gs/messaging-stomp-websocket/
//https://vuetifyjs.com/en/components/all/
ChartJS.register(
    CategoryScale,
    LinearScale,
    PointElement,
    LineElement,
    Title,
    Tooltip,
    Legend
)

export default {
  name: 'LineChart',
  computed: {
    id() {
      return id
    }
  },
  methods: {
    getCustomerId () {
      this.customerid = localStorage.getItem( "customerid" );
    }
  },
  components: { Line },
  data: () => ({
    customerid: '',
    loaded: false,
    chartDataProd: null,
    chartDataCons: null,
  }),
  async mounted () {
    this.getCustomerId()
    this.loaded = false

    try {
      //const { registry } = await fetch('http://127.0.0.1:52201/')
      this.chartDataProd = registry
      this.chartDataCons = registry

      this.loaded = true
    } catch (e) {
      //console.error(e)
    }
    this.chartDataProd = {
      labels: [ '0000', '0100', '0300', '0400', '0500', '0600', '0700', '0800', '0900', '1000', '1100', '1200', '1300', '1400', '1500', '1600', '1700', '1800', '1900', '2000', '2100', '2200', '2300' ],
      datasets: [
        {
          label: 'Energy in kWh',
          backgroundColor: '#176806',
          data: [20, 12, 23, 25,  16, 20, 21, 20, 23, 13, 13, 13, 15, 15, 12, 23, 30, 13, 13, 13, 15, 15, 16, 20, 23, 20, 12, 23, 25, 13, 13, 13, 15, 15, 16, 20, 21, 20, 12, 23, 30, 13, 13, 13, 15, 15, 16, 20, ]
        }
      ]
    }
    this.chartDataCons = {
      labels: [ '0000', '0100', '0300', '0400', '0500', '0600', '0700', '0800', '0900', '1000', '1100', '1200', '1300', '1400', '1500', '1600', '1700', '1800', '1900', '2000', '2100', '2200', '2300' ],
      datasets: [
        {
          label: 'Energy in kWh',
          backgroundColor: '#7c0808',
          data: [23, 30, 13, 23, 20, 12, 23, 25, 13, 13, 13, 15, 15, 16, 13, 13, 15, 15, 16, 20, 21, 20, 12, 20, 23, 20, 12, 23, 25, 13, 13, 13, 15, 15, 16, 20, 21, 20, 12, 23, 30, 13, 13, 13, 15, 15, 16, 20, ]
        }
      ]
    }

    this.loaded = true

    setTimeout(() => {
      console.log("works with Production data")
      this.chartDataProd =  {
        labels: [ '0000', '0100', '0300', '0400', '0500', '0600', '0700', '0800', '0900', '1000', '1100', '1200', '1300', '1400', '1500', '1600', '1700', '1800', '1900', '2000', '2100', '2200', '2300' ],
        datasets: [
            {
              label: 'Energy in kWh',
              backgroundColor: '#176806',
              data: [21, 20, 12, 23, 24, 13, 16, 20, 25, 20, 12, 23, 23, 13, 13, 15, 15, 13, 13, 13, 15, 15, 16, 20, ]
            }
        ]
      }
    }, 3000)

    setTimeout(() => {
      console.log("works with Consumption data")
      this.chartDataCons =  {
        labels: [ '0000', '0100', '0300', '0400', '0500', '0600', '0700', '0800', '0900', '1000', '1100', '1200', '1300', '1400', '1500', '1600', '1700', '1800', '1900', '2000', '2100', '2200', '2300' ],
        datasets: [
          {
            label: 'Energy in kWh',
            backgroundColor: '#7c0808',
            data: [21, 16, 20, 25, 20, 12, 20, 12, 23, 24, 13, 23, 23, 13, 13, 15, 15, 13, 13, 13, 15, 15, 16, 20, ]
          }
        ]
      }
    }, 4000)

  }

}
</script>
