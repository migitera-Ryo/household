<script setup lang="ts">
import { ref } from 'vue'
import axios from 'axios'
</script>

<script lang="ts">
import NunberInput from '../Atoms/NumberInput.vue'

export default {
  data() {
    return {
      firstAmount: '',
      lastAmount: '',
    }
  },
  methods: {
    setAmount() {
      this.$emit('execute-method', this.firstAmount, this.lastAmount)
    },
  },
}
</script>

<template>
  <p>
    <label>金額：</label>
    <NunberInput v-model="firstAmount" @execute-method="setAmount" />
    <label>{{ '~' }}</label>
    <NunberInput v-model="lastAmount" @execute-method="setAmount" />
  </p>
</template>

<style scoped></style>
