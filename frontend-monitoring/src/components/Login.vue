<template>
  <v-container>
    <v-sheet width="50%" class="mx-auto pt-16">
      <v-form fast-fail @submit.prevent="login">
        <h1>Login to emsAI</h1>
        <v-text-field v-model="customerid" label="Customer ID"></v-text-field>
        <v-btn type="submit" @click="setCustomerId" color="primary" block class="mt-2">Sign in</v-btn>
      </v-form>
    </v-sheet>
  </v-container>
</template>

<script>

export default {
  data() {
    return {
      customerid: '',
    };
  },
  methods: {
    login() {
      this.$router.push('monitor')
    },
    setCustomerId() {
      localStorage.setItem("customerid", this.customerid);
    },
  },
  async mounted() {
    this.customerid = localStorage.getItem( "customerid" );
    if (this.customerid != null)  {
      this.login()
    }
  }
}
</script>

<style scoped>

</style>
