<script setup lang="ts">
import { ref } from 'vue'
import axios from 'axios'

interface Props {
  formSelectName: string
  Types: [{ value: string; text: string }]
}
defineProps<Props>()
</script>

<script lang="ts"></script>

<template>
  <p>
    <label>{{ formSelectName }}</label>
    <select v-for="income_type in Types" :value="income_type.value" :key="income_type.value">
      <option>{{ income_type.text }}</option>
    </select>
  </p>
</template>

<style scoped></style>
