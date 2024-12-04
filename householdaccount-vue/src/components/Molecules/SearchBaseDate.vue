<script setup lang="ts">
import { ref } from 'vue'
import axios from 'axios'
</script>

<script lang="ts">
import DateInput from '../Atoms/DateInput.vue'
</script>

<template>
  <p>
    <label>収支日付：</label>
    <DateInput />
    <label>{{ '~' }}</label>
    <DateInput />
  </p>
</template>

<style scoped></style>
