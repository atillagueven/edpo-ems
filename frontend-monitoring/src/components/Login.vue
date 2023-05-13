<template>
  <v-container>


      <v-sheet width="500" class="mx-auto">
        <v-form fast-fail @submit.prevent="login">
          <h1>Login to emsAI</h1>
          <v-text-field v-model="id" label="Customer ID"></v-text-field>

          <v-btn type="submit" color="primary" block class="mt-2">Sign in</v-btn>

        </v-form>
      </v-sheet>

  </v-container>
</template>

<style>

</style>

<script>
export default {
  data() {
    return {
      id: '',
    };
  },
  methods: {
    login() {
      this.$router.push('/monitor')
    },
  },
}
</script>

<style scoped>

</style>
