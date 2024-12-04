<script setup lang="ts">
import { ref } from 'vue'
import axios from 'axios'
</script>

<script lang="ts">
import NunberInput from '../Atoms/NumberInput.vue'
</script>

<template>
  <p>
    <label>金額：</label>
    <NunberInput />
    <label>{{ '~' }}</label>
    <NunberInput />
  </p>
</template>

<style scoped></style>
