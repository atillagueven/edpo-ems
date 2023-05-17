<template>
    <v-app-bar>
      <v-app-bar-title>
        <v-icon icon="mdi-circle-slice-4" />
        <a href="/">
        EMSai
        </a>
      </v-app-bar-title>
      <v-spacer></v-spacer>

      <v-btn icon @click="deleteCustomerId">
        <v-icon>mdi-logout</v-icon>
      </v-btn>
    </v-app-bar>
</template>

<script>
export default {
  methods: {
    deleteCustomerId () {
      localStorage.removeItem("customerid");
      this.$router.push('/');
    }
  },
}
</script>
