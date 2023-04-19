<template>
  <v-container>

    <h1 class="mb-12">
      Energy Storage order process
    </h1>

    <v-row>
      <v-col cols="4">
        <v-card>
          <router-link to="/sales">
            <v-btn block>Sales</v-btn>
          </router-link>
        </v-card>
      </v-col>
      <v-col cols="4">
        <v-card>
          <router-link to="/inventory">
            <v-btn block>Inventory</v-btn>
          </router-link>
        </v-card>
      </v-col>
      <v-col cols="4">
        <v-card>
          <router-link to="/payment">
            <v-btn block>Payment</v-btn>
          </router-link>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script setup>
import Payment from "@/components/Payment.vue";
</script>
