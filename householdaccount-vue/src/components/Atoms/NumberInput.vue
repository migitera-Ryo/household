<script setup lang="ts">
import { ref } from 'vue'
import axios from 'axios'
</script>

<script lang="ts">
export default {
  data() {
    return {
      amount: '',
    }
  },
  methods: {
    setAmount() {
      this.$emit('execute-method')
    },
  },
}
</script>

<template>
  <input type="text" v-model="amount" placeholder="Type here" @blur="setAmount" />
</template>

<style scoped></style>
