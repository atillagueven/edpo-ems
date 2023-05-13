<template>
  <v-container>
    <h1>
      Energy Production of producer: Solar Panel
    </h1>
    <h3 class="pb-5">monitor your energy production</h3>
    <Line v-if="loaded" :data="chartData" class="pa-2"/>
  </v-container>
  <v-card>
    <v-card-title>
      Energy Consumption of consumer: Charging Station
    </v-card-title>
    <v-card-subtitle class="pb-10">
      monitor your energy consumption
    </v-card-subtitle>
    <div class="container">
      <Line v-if="loaded" :data="chartData" class="pa-2"/>
    </div>
  </v-card>
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
  components: { Line },
  data: () => ({
    loaded: false,
    chartData: null
  }),
  async mounted () {
    this.loaded = false

    try {
      //const { registry } = await fetch('http://127.0.0.1:52201/')
      this.chartdata = registry

      this.loaded = true
    } catch (e) {
      //console.error(e)
    }

    this.chartData = {
      labels: [ '0000', '0100', '0300', '0400', '0500', '0600', '0700', '0800', '0900', '1000', '1100', '1200', '1300', '1400', '1500', '1600', '1700', '1800', '1900', '2000', '2100', '2200', '2300' ],
      datasets: [
        {
          label: 'Energy in kWh',
          backgroundColor: '#2E74C6',
          data: [23, 20, 12, 23, 25, 13, 13, 13, 15, 15, 16, 20, 21, 20, 12, 23, 30, 13, 13, 13, 15, 15, 16, 20, 23, 20, 12, 23, 25, 13, 13, 13, 15, 15, 16, 20, 21, 20, 12, 23, 30, 13, 13, 13, 15, 15, 16, 20, ]
        }
      ]
    }
    this.loaded = true

    setTimeout(() => {
      console.log("works with manual data")
      this.chartData =  {
        labels: [ '0000', '0100', '0300', '0400', '0500', '0600', '0700', '0800', '0900', '1000', '1100', '1200', '1300', '1400', '1500', '1600', '1700', '1800', '1900', '2000', '2100', '2200', '2300' ],
        datasets: [
            {
              label: 'Energy in kWh',
              backgroundColor: '#2E74C6',
              data: [21, 20, 12, 23, 24, 13, 16, 20, 25, 20, 12, 23, 23, 13, 13, 15, 15, 13, 13, 13, 15, 15, 16, 20, ]
            }
        ]
      }
    }, 3000)
  }

}
</script>
